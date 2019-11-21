package ru.akozlovskiy.springdz05.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz05.domain.Book;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({ BookDAOJdbc.class })
public class BookDAOJdbcTest {

	@Autowired
	BookDAOJdbc bookDAOJdbc;

	@Test
	public void testDao() {

		Book book = new Book(1l, "title", 2l, null);
		bookDAOJdbc.add(book);

		Book book2 = new Book(2l, "title2", 2l, null);
		bookDAOJdbc.add(book2);

		List<Book> getByIdResult = bookDAOJdbc.getAll();

		assertEquals(getByIdResult.size(), 2);

	}
}
