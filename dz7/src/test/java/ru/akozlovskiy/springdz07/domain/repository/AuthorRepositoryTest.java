package ru.akozlovskiy.springdz07.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.dao.jpa.AuthorDAOJpa;
import ru.akozlovskiy.springdz07.exception.DaoException;

@DataJpaTest
@Import({ AuthorDAOJpa.class })
public class AuthorRepositoryTest {
	
	private static final String AUTHOR_BIRTH_DATE = "1891-05-15";

	private static final String TEST_AUTHOR_NAME = "Булгаков";

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private TestEntityManager em;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	@DisplayName("Успешность добавления в случае корректных")
	public void testAdd() throws DaoException {
		long authorID = authorRepository.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		assertNotEquals(0, authorID);

		Author author = em.find(Author.class, authorID);
		assertEquals(TEST_AUTHOR_NAME, author.getName());
		assertEquals(AUTHOR_BIRTH_DATE, dateFormatter.format(author.getBirthDate()));
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с кривой датой рождения")
	public void testAddWithErrorBirthDate() throws DaoException {
		assertThrows(DaoException.class, () -> {
			authorRepository.add("name", "errorFormatBirthDate");
		});
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testFindByName() throws DaoException {
		Author author = new Author();
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));
		author.setName(TEST_AUTHOR_NAME);
		em.persistAndFlush(author);
				
		Author authorByName = authorRepository.findByName(TEST_AUTHOR_NAME);
		assertThat(author).isEqualToComparingFieldByField(authorByName);
	}

	@Test
	@DisplayName("Поиск всех")
	public void testFindAll() throws DaoException {
		authorRepository.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		List<Author> all = authorRepository.findAll();
		assertEquals(2, all.size());
	}
}