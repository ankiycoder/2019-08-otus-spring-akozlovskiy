package ru.akozlovskiy.springdz06.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.exception.DaoException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({ BookDAOJpa.class, AuthorDAOJpa.class, GenreDAOJpa.class })
@DisplayName("DAO сервис по работе с книгами")
public class BookDAOJpaTest {

	private static final String BOOK_NAME_BD = "BOOK_NAME1";
	private static final String AUTHOR_NAME_IN_BD = "AUTHOR_NAME";
	private static final String GENRE_IN_BD = "GENRE_TEST";

	@Autowired
	private BookDAOJpa bookDAO;

	@Test
	@DisplayName("Поиск по ID")
	public void testGetById() throws DaoException {

		Book book = bookDAO.getById(1l);

		assertEquals(book.getBookName(), BOOK_NAME_BD);
		assertEquals(book.getAuthor().getName(), AUTHOR_NAME_IN_BD);
		assertEquals(book.getGenre().getDescription(), GENRE_IN_BD);
	}

	@Test
	@DisplayName("Поиск по названию")
	public void testFindByName() throws DaoException {

		List<Book> bookList = bookDAO.findByName(BOOK_NAME_BD);
		assertThat(bookList).isNotEmpty();
		
		Book book = bookList.get(0);
		assertEquals(book.getBookName(), BOOK_NAME_BD);
		assertEquals(book.getAuthor().getName(), AUTHOR_NAME_IN_BD);
		assertEquals(book.getGenre().getDescription(), GENRE_IN_BD);
	}
	
	@Test
	@DisplayName("Поиск по автору")
	public void testFindByAuthor() throws DaoException {

		List<Book> bookList = bookDAO.findAllByAuthor(AUTHOR_NAME_IN_BD);
		assertThat(bookList).isNotEmpty();
		
		Book book = bookList.get(0);
		assertEquals(book.getBookName(), BOOK_NAME_BD);
		assertEquals(book.getAuthor().getName(), AUTHOR_NAME_IN_BD);
		assertEquals(book.getGenre().getDescription(), GENRE_IN_BD);
	}
}
