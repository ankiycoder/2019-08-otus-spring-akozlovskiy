package ru.akozlovskiy.springdz06.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.domain.dao.GenreDAO;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreDAOJpa implements GenreDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long add(String description) {
		Genre genre = new Genre();
		genre.setDescription(description);
		em.persist(genre);
		return genre.getId();
	}

	@Override
	public Genre getById(long id) {
		return em.find(Genre.class, id);
	}

	@Override
	public List<Genre> getAll() {
		TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
		return query.getResultList();
	}

	@Override
	public Genre getByDescription(String description) {
		TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.description = :description",
				Genre.class);
		query.setParameter("description", description);
		return query.getSingleResult();
	}
}
