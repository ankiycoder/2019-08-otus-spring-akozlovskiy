package ru.akozlovskiy.springdz10.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.akozlovskiy.springdz10.domain.Genre;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.exception.DaoException;

@DataJpaTest
public class GenreRepositoryTest {

	private static final String GENRE_DESCRIPTION = "Драма";

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Добавление")
	public void testAdd() throws DaoException {

		Genre genre = new Genre();
		genre.setDescription(GENRE_DESCRIPTION);
		genreRepository.save(genre);

		long id = genre.getId();
		Genre genreFind = em.find(Genre.class, id);
		assertEquals(genreFind.getDescription(), GENRE_DESCRIPTION);
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

		Genre genre = new Genre();
		genre.setDescription("test1");
		genreRepository.save(genre);

		Genre genre2 = new Genre();
		genre2.setDescription("test2");
		genreRepository.save(genre2);

		Genre genre3 = new Genre();
		genre3.setDescription("test3");
		genreRepository.save(genre3);

		List<Genre> genreList = genreRepository.findAll();
		assertEquals(4, genreList.size());
	}
}