package ru.akozlovskiy.springdz18.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.repository.AuthorRepository;

@DataJpaTest
@DisplayName("Репозиторий по работе с авторами")
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
	public void testAdd() {
		Author author = new Author();
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));
		author.setName(TEST_AUTHOR_NAME);
		em.persistAndFlush(author);

		Author authorFind = em.find(Author.class, author.getId());
		assertEquals(TEST_AUTHOR_NAME, authorFind.getName());
		assertEquals(AUTHOR_BIRTH_DATE, dateFormatter.format(authorFind.getBirthDate()));
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testFindByName() {
		Author author = new Author();
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));
		author.setName(TEST_AUTHOR_NAME);
		em.persistAndFlush(author);

		Optional<Author> authorByName = authorRepository.findByName(TEST_AUTHOR_NAME);
		assertThat(author).isEqualToComparingFieldByField(authorByName.get());
	}

	@Test
	@DisplayName("Поиск всех")
	public void testFindAll()  {
		Author author = new Author();
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));
		author.setName(TEST_AUTHOR_NAME);
		em.persistAndFlush(author);

		List<Author> all = authorRepository.findAll();
		assertEquals(3, all.size());
	}
}