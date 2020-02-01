package ru.akozlovskiy.springdz10.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.akozlovskiy.springdz10.domain.Author;
import ru.akozlovskiy.springdz10.domain.Book;
import ru.akozlovskiy.springdz10.domain.Genre;
import ru.akozlovskiy.springdz10.domain.repository.BookRepository;
import ru.akozlovskiy.springdz10.exception.DaoException;

@DataJpaTest
@DisplayName("Репозиторий по работе с книгами")
public class BookRepositoryTest {

	private static final String BOOK_NAME_BD = "BOOK_NAME1";
	private static final String AUTHOR_NAME_IN_BD = "AUTHOR_NAME";
	private static final String GENRE_IN_BD = "GENRE_TEST";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Поиск по ID")
	public void testGetById() throws DaoException {
		Optional<Book> bookgetById = bookRepository.findById(1l);
		Book bookFind = em.find(Book.class, 1l);
		assertThat(bookgetById.get()).isEqualToComparingFieldByField(bookFind);
	}

	@Test
	@DisplayName("Поиск по описанию")
	public void testFindByBookName() throws DaoException {

		Optional<Book> bookOp = bookRepository.findByTitle(BOOK_NAME_BD);
		Book book = bookOp.get();
		assertEquals(book.getTitle(), BOOK_NAME_BD);
		assertEquals(book.getAuthor().getName(), AUTHOR_NAME_IN_BD);
		assertEquals(book.getGenre().getDescription(), GENRE_IN_BD);
	}

	@Test
	@DisplayName("Поиск по автору")
	public void testFindByAuthor() throws DaoException {

		List<Book> bookList = bookRepository.findAllByAuthorName(AUTHOR_NAME_IN_BD);
		assertThat(bookList).isNotEmpty();

		Book book = bookList.get(0);
		assertEquals(book.getTitle(), BOOK_NAME_BD);
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
		book.setTitle(testBookName);
		book.setAuthor(author);
		book.setGenre(genre);

		long bookId = bookRepository.save(book).getId();
		assertNotEquals(0, bookId);

		Book findBook = em.find(Book.class, bookId);

		assertThat(findBook).isEqualToComparingFieldByField(book);
	}

	@Test
	@DisplayName("Поиск всех книг")
	public void testGetAll() throws DaoException {

		List<Book> bookList = bookRepository.findAll();
		assertThat(bookList).hasSize(2);
	}
}