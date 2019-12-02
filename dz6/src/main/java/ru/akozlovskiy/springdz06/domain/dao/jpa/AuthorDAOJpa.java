package ru.akozlovskiy.springdz06.domain.dao.jpa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.exception.DaoException;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorDAOJpa implements AuthorDAO {

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	@PersistenceContext
	private EntityManager em;

	@Override
	public Author getById(long id) {
		return em.find(Author.class, id);
	}

	@Override
	public List<Author> getAll() {
		TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
		return query.getResultList();
	}

	@Override
	public Author getByName(String name) {
		TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}

	@Override
	public long add(String name, String birthDate) throws DaoException {
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(birthDate, dateFormatter);
		} catch (Exception ex) {
			throw new DaoException("Не корректный формат даты рождения, должен быть: " + YYYY_MM_DD);
		}
		Author author = new Author();
		author.setBirthDate(localDate);
		author.setName(name);
		em.persist(author);
		return author.getId();
	}
}