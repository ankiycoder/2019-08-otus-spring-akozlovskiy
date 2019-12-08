package ru.akozlovskiy.springdz07.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.dao.jpa.AuthorDAOJpa;
import ru.akozlovskiy.springdz07.exception.DaoException;

@DataJpaTest
@Import({ AuthorDAOJpa.class })
public class GenreRepositoryTest {

	private static final String GENRE_DESCRIPTION = "Драма";

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Добавление")
	public void testAdd() throws DaoException {

		long id = genreRepository.add(GENRE_DESCRIPTION);
		Genre genre = em.find(Genre.class, id);
		assertEquals(genre.getDescription(), GENRE_DESCRIPTION);
	}

	@Test
	@DisplayName("Поиск по ID")
	public void testPersistAndGetById() throws DaoException {
		Genre genre = new Genre();
		genre.setDescription(GENRE_DESCRIPTION);
		em.persist(genre);

		Optional<Genre> getByIdGenre = genreRepository.findById(genre.getId());
		assertThat(genre).isEqualToComparingFieldByField(getByIdGenre.get());
	}

	@Test
	@DisplayName("Поиск по описанию")
	public void testGetByName() throws DaoException {
		Genre genre = new Genre();
		genre.setDescription(GENRE_DESCRIPTION);
		em.persist(genre);

		Genre genreByDescriptopn = genreRepository.findByDescription(GENRE_DESCRIPTION);
		assertThat(genre).isEqualToComparingFieldByField(genreByDescriptopn);
	}

	@Test
	@DisplayName("Поиск всех жанров")
	public void testGetAll() throws DaoException {
		genreRepository.add("test1");
		genreRepository.add("test2");
		genreRepository.add("test3");
		List<Genre> genreList = genreRepository.findAll();
		assertEquals(4, genreList.size());
	}

}
