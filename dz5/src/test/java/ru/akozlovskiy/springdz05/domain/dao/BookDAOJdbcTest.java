package ru.akozlovskiy.springdz05.domain.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz05.domain.Book;
import ru.akozlovskiy.springdz05.exception.DaoException;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({ BookDAOJdbc.class, AuthorDAOJdbc.class, GenreDAOJdbc.class })
@DisplayName("DAO сервис по работе с книгами")
public class BookDAOJdbcTest {

	private static final String AUTHOR_NAME_IN_BD = "AUTHOR_NAME";
	private static final String GENRE_IN_BD = "GENRE_TEST";

	@Autowired
	BookDAOJdbc bookDAOJdbc;

	@Test
	@DisplayName("Успешность добавления в случае корректных данных")
	public void testAddAndGetById() throws DaoException {
		String testBookName = "bookname";
		long bookId = bookDAOJdbc.add(testBookName, AUTHOR_NAME_IN_BD, GENRE_IN_BD);
		assertNotEquals(0, bookId);

		Book book = bookDAOJdbc.getById(bookId);

		assertEquals(book.getBookName(), testBookName);
		assertEquals(book.getAuthor().getName(), AUTHOR_NAME_IN_BD);
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с не заведенным автором")
	public void tesAddDaoWithWrongAuthor() throws DaoException {
		assertThrows(DaoException.class, () -> {
			bookDAOJdbc.add("bookname", "WRONG_AUTHOR_NAME", GENRE_IN_BD);
		});
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с не заведенным жанром")
	public void tesAddDaoWithWrongGenre() throws DaoException {
		assertThrows(DaoException.class, () -> {
			bookDAOJdbc.add("bookname", AUTHOR_NAME_IN_BD, "WRONG_GENRE");
		});
	}

	@Test
	@DisplayName("Поиск всех книг по автору, когда он есть")
	public void testFindAllByAuthorWhenSuccess() {
		List<Book> bookList = bookDAOJdbc.findAllByAuthor(AUTHOR_NAME_IN_BD);
		assertEquals(bookList.size(), 2);

	}

	@Test
	@DisplayName("Поиск по автору, когда его нет")
	public void testFindAllByAuthorWhenNotFound() {
		List<Book> bookList = bookDAOJdbc.findAllByAuthor("WRONG_AUTHOR_NAME");
		assertEquals(0, bookList.size());
	}
}
