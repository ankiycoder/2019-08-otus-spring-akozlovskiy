package ru.akozlovskiy.springdz07.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.exception.DaoException;

@DataJpaTest
@Import({ BookDAOJpa.class, AuthorDAOJpa.class, GenreDAOJpa.class })
@DisplayName("DAO по работе с книгами")
public class BookDAOJpaTest {

	private static final String BOOK_NAME_BD = "BOOK_NAME1";
	private static final String AUTHOR_NAME_IN_BD = "AUTHOR_NAME";
	private static final String GENRE_IN_BD = "GENRE_TEST";

	@Autowired
	private BookDAOJpa bookDAO;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Поиск по ID")
	public void testGetById() throws DaoException {
		Book bookgetById = bookDAO.getById(1l);
		Book bookFind = em.find(Book.class, 1l);
		assertThat(bookgetById).isEqualToComparingFieldByField(bookFind);
	}

	@Test
	@DisplayName("Поиск по названию")
	public void testFindByName() throws DaoException {
		Book book = bookDAO.findByName(BOOK_NAME_BD);
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

	@Test
	@DisplayName("Добавление")
	public void testAddAndGetById() throws DaoException {

		Author author = em.find(Author.class, 1l);
		Genre genre = em.find(Genre.class, 1l);

		String testBookName = "booknametest";

		Book book = new Book();
		book.setBookName(testBookName);
		book.setAuthor(author);
		book.setGenre(genre);

		long bookId = bookDAO.add(book);
		assertNotEquals(0, bookId);

		Book findBook = em.find(Book.class, bookId);

		assertThat(findBook).isEqualToComparingFieldByField(book);
	}

	@Test
	@DisplayName("Поиск всех книг")
	public void testGetAll() throws DaoException {

		List<Book> bookList = bookDAO.getAll();
		assertThat(bookList).hasSize(2);
	}
}