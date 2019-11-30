package ru.akozlovskiy.springdz06.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.exception.DaoException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({ AuthorDAOJpa.class })
@DisplayName("DAO севрис по работе с авторами")
@Sql(scripts = "classpath:db/testdata.sql")
public class AuthorDAOJpaTest {

	private static final String AUTHOR_BIRTH_DATE = "1891-05-15";

	private static final String TEST_AUTHOR_NAME = "Булгаков";

	@Autowired
	private AuthorDAOJpa authorDAO;
	
	@Autowired
	private TestEntityManager em;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	@DisplayName("Успешность добавления в случае корректных")
	public void testAdd() throws DaoException {
		long authorID = authorDAO.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		assertNotEquals(0, authorID);

		Author author = em.find(Author.class, authorID);
		assertEquals(TEST_AUTHOR_NAME, author.getName());
		assertEquals(AUTHOR_BIRTH_DATE, dateFormatter.format(author.getBirthDate()));
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с кривой датой рождения")
	public void testAddWithErrorBirthDate() throws DaoException {
		assertThrows(DaoException.class, () -> {
			authorDAO.add("name", "errorFormatBirthDate");
		});
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testGetByName() throws DaoException {
		Author author = new Author();
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));
		author.setName(TEST_AUTHOR_NAME);
		em.persistAndFlush(author);
				
		Author authorByName = authorDAO.getByName(TEST_AUTHOR_NAME);
		assertThat(author).isEqualToComparingFieldByField(authorByName);
	}

	@Test
	@DisplayName("Поиск всех")
	public void testGetAll() throws DaoException {
		authorDAO.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		List<Author> all = authorDAO.getAll();
		assertEquals(2, all.size());
	}
}