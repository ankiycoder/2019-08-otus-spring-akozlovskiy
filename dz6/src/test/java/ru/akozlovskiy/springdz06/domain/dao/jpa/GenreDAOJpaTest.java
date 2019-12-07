package ru.akozlovskiy.springdz06.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.exception.DaoException;

@DataJpaTest
@Import({ GenreDAOJpa.class })
@DisplayName("DAO по работе с жанрами")
public class GenreDAOJpaTest {

	private static final String GENRE_DESCRIPTION = "Драма";

	@Autowired
	private GenreDAOJpa genreDAO;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Добавление")
	public void testAdd() throws DaoException {
		long id = genreDAO.add(GENRE_DESCRIPTION);
		Genre genre = em.find(Genre.class, id);
		assertEquals(genre.getDescription(), GENRE_DESCRIPTION);
	}

	@Test
	@DisplayName("Поиск по ID")
	public void testPersistAndGetById() throws DaoException {
		Genre genre = new Genre();
		genre.setDescription(GENRE_DESCRIPTION);
		em.persistAndFlush(genre);

		Genre getByIdGenre = genreDAO.getById(genre.getId());
		assertThat(genre).isEqualToComparingFieldByField(getByIdGenre);
	}

	@Test
	@DisplayName("Поиск по описанию")
	public void testGetByName() throws DaoException {
		Genre genre = new Genre();
		genre.setDescription(GENRE_DESCRIPTION);
		em.persistAndFlush(genre);

		Genre genreByDescriptopn = genreDAO.getByDescription(GENRE_DESCRIPTION);
		assertThat(genre).isEqualToComparingFieldByField(genreByDescriptopn);
	}

	@Test
	@DisplayName("Поиск всех жанров")
	public void testGetAll() throws DaoException {
		genreDAO.add("test1");
		genreDAO.add("test2");
		genreDAO.add("test3");
		List<Genre> genreList = genreDAO.getAll();
		assertEquals(4, genreList.size());
	}
}