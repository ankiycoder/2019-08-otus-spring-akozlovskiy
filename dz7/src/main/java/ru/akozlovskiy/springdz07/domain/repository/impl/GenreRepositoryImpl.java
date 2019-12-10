package ru.akozlovskiy.springdz07.domain.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepositoryCustom;

@Repository
public class GenreRepositoryImpl implements GenreRepositoryCustom {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long add(String description) {
		Genre genre = new Genre();
		genre.setDescription(description);
		em.persist(genre);
		return genre.getId();
	}
}
