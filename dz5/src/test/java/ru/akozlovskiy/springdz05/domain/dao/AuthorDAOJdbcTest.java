package ru.akozlovskiy.springdz05.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.exception.DaoException;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({ AuthorDAOJdbc.class })
@DisplayName("DAO севрис по работе с авторами")
public class AuthorDAOJdbcTest {

	private static final String AUTHOR_BIRTH_DATE = "1891-05-15";

	private static final String TEST_AUTHOR_NAME = "Булгаков";

	@Autowired
	private AuthorDAOJdbc authorDAOJdbc;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	@DisplayName("Успешность добавления в случае корректных")
	public void testAddAngGetById() throws DaoException {
		long authorID = authorDAOJdbc.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		assertNotEquals(0, authorID);
		
		Author author = authorDAOJdbc.getById(authorID);
		assertEquals(TEST_AUTHOR_NAME, author.getName());
		assertEquals(AUTHOR_BIRTH_DATE, dateFormatter.format(author.getBirthDate()));
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с кривой датой рождения")
	public void testAddWithErrorBirthDate() throws DaoException {
		assertThrows(DaoException.class, () -> {
			authorDAOJdbc.add("name", "errorFormatBirthDate");
		});
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testGetByName() throws DaoException {
		long authorId = authorDAOJdbc.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		Author authorByName = authorDAOJdbc.getByName(TEST_AUTHOR_NAME);
		Author authorById = authorDAOJdbc.getById(authorId);
		assertThat(authorByName).isEqualToComparingFieldByField(authorById);
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testGetAll() throws DaoException {
		authorDAOJdbc.add(TEST_AUTHOR_NAME, AUTHOR_BIRTH_DATE);
		List<Author> all = authorDAOJdbc.getAll();
		assertEquals(2, all.size());
	}
}