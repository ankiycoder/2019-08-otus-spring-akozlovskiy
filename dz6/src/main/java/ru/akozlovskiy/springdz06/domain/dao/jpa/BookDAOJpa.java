package ru.akozlovskiy.springdz06.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.exception.DaoException;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookDAOJpa implements BookDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long add(Book book) throws DaoException {
		em.persist(book);
		return book.getId();
	}

	@Override
	public Book getById(long id) throws DaoException {
		return em.find(Book.class, id);
	}

	@Override
	public Book findByName(String name) {
		TypedQuery<Book> query = em.createQuery(
				"select b from Book b join fetch b.genre g join fetch b.author a where b.bookName=:bookName",
				Book.class);
		query.setParameter("bookName", name);
		return query.getSingleResult();
	}

	@Override
	public List<Book> findAllByAuthor(String authorName) throws DaoException {
		TypedQuery<Book> query = em.createQuery(
				"select b from Book b join fetch b.author ath join fetch b.genre g where ath.name = :authorName",
				Book.class);

		query.setParameter("authorName", authorName);
		return query.getResultList();
	}

	@Override
	public List<Book> getAll() throws DaoException {
		TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
		return query.getResultList();
	}
}