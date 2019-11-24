package ru.akozlovskiy.springdz05.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz05.domain.Genre;
import ru.akozlovskiy.springdz05.exception.DaoException;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({ GenreDAOJdbc.class })
@DisplayName("DAO сервис по работе с жанрами")
public class GenreDAOJdbcTest {

	private static final String GENRE_DESCRIPTION = "Фантастика";

	@Autowired
	private GenreDAOJdbc genreDAOJdbc;

	@Test
	@DisplayName("Успешность добавления  и поиска по ID")
	public void testAddAndGetById() throws DaoException {

		String description = GENRE_DESCRIPTION;

		long id = genreDAOJdbc.add(description);
		Genre genre = genreDAOJdbc.getById(id);
		assertEquals(description, genre.getDescription());
		assertEquals(id, genre.getId());
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testGetByName() throws DaoException {
		long genreId = genreDAOJdbc.add(GENRE_DESCRIPTION);
		Genre genreById = genreDAOJdbc.getById(genreId);
		Genre genreByDescriptopn = genreDAOJdbc.getByDescription(GENRE_DESCRIPTION);
		assertThat(genreById).isEqualToComparingFieldByField(genreByDescriptopn);
	}

	@Test
	@DisplayName("Поиск всех жанров в БД")
	public void testGetAll() throws DaoException {
		genreDAOJdbc.add("test1");
		genreDAOJdbc.add("test2");
		genreDAOJdbc.add("test3");
		List<Genre> genreList = genreDAOJdbc.getAll();
		assertEquals(4, genreList.size());
	}
}