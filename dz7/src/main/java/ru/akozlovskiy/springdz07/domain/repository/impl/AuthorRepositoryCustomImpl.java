package ru.akozlovskiy.springdz07.domain.repository.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz07.domain.repository.AuthorRepositoryCustom;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Repository
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom {

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	@Autowired
	AuthorRepository authorRepository;

	@PersistenceContext
	private EntityManager em;

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
