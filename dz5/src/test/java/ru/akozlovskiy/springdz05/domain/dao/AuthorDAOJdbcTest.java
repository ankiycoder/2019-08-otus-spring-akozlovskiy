package ru.akozlovskiy.springdz05.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.format.DateTimeFormatter;

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

	@Autowired
	AuthorDAOJdbc authorDAOJdbc;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Test
	@DisplayName("Успешность добавления в случае корректных данных")
	public void testAddAngGetById() throws DaoException {

		long authorID = authorDAOJdbc.add("Булгаков", "1891-05-15");
		assertNotEquals(0, authorID);

		Author author = authorDAOJdbc.getById(authorID);
		assertEquals("Булгаков", author.getName());
		assertEquals("1891-05-15", dateFormatter.format(author.getBirthDate()));

	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с кривой датой рождения")
	public void testAddWithErrorBirthDate() throws DaoException {
		assertThrows(DaoException.class, () -> {
			authorDAOJdbc.add("name", "errorFormatBirthDate");
		});
	}
}
