package ru.akozlovskiy.springdz08.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.repository.AbstractRepositoryTest;
import ru.akozlovskiy.springdz08.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz08.domain.service.BookService;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DisplayName("Сервис по работе с книгами")
public class BookServiceImplTest extends AbstractRepositoryTest {

	private static final String GENRE_COMEDY = "Комедия";
	private static final String AUTHOR_NAME_FOR_TEST = "AuthorNameTest";
	private static final String BOOK_NAME_TEST = "bookNameTest";
	private static final String BOOK_NAME_TEST2 = "bookNameTest2";

	@Autowired
	private BookService bookService;

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	@DisplayName("Добавление книги и автора")
	public void testAdd() throws DaoException {

		bookService.add(BOOK_NAME_TEST, AUTHOR_NAME_FOR_TEST, GENRE_COMEDY);

		Optional<Book> findedBookOp = bookService.findByTitle(BOOK_NAME_TEST);
		Book book = findedBookOp.get();
		assertEquals(BOOK_NAME_TEST, book.getTitle());
		assertEquals(AUTHOR_NAME_FOR_TEST, book.getAuthor().getName());
		assertEquals(GENRE_COMEDY, book.getGenre().getDescription());

		Optional<Author> author = authorRepository.findByName(AUTHOR_NAME_FOR_TEST);
		assertThat(author.get()).isEqualToComparingFieldByField(book.getAuthor());
	}

	@Test
	@DisplayName("Удаление")
	public void testRemove() throws DaoException {

		bookService.add(BOOK_NAME_TEST2, AUTHOR_NAME_FOR_TEST, GENRE_COMEDY);

		Optional<Book> findedBookOp = bookService.findByTitle(BOOK_NAME_TEST2);
		assertThat(findedBookOp).isPresent();

		bookService.removeByTitle(BOOK_NAME_TEST2);
		Optional<Book> findedBookOpAfterDel = bookService.findByTitle(BOOK_NAME_TEST2);
		assertThat(findedBookOpAfterDel).isNotPresent();
	}
}