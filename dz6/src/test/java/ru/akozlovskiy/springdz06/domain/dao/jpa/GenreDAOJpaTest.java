package ru.akozlovskiy.springdz06.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.domain.dao.jpa.GenreDAOJpa;
import ru.akozlovskiy.springdz06.exception.DaoException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({ GenreDAOJpa.class })
@DisplayName("DAO сервис по работе с жанрами")
@Sql(scripts = "classpath:db/testdata.sql")
public class GenreDAOJpaTest {

	private static final String GENRE_DESCRIPTION = "Фантастика";

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