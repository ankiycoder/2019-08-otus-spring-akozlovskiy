package ru.akozlovskiy.springdz05.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

	@Autowired
	GenreDAOJdbc genreDAOJdbc;

	@Test
	@DisplayName("Успешность добавления  и посика")
	public void testAddAndGetById() throws DaoException {

		long id = genreDAOJdbc.add("Фантастика");
		assertNotEquals(0, id);

		Genre genre = genreDAOJdbc.getById(id);
		assertEquals("Фантастика", genre.getDescription());
		assertEquals(id, genre.getId());
	}

	@Test
	@DisplayName("Поиск всех жнров в БД")
	public void testGetAll() throws DaoException {

		genreDAOJdbc.add("test1");
		genreDAOJdbc.add("test2");
		genreDAOJdbc.add("test3");
		List<Genre> genreList = genreDAOJdbc.getAll();
		assertEquals(4, genreList.size());
	}
}