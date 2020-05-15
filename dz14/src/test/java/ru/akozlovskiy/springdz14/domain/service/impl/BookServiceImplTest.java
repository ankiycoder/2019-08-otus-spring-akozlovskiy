package ru.akozlovskiy.springdz14.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.Genre;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.BookRepository;
import ru.akozlovskiy.springdz14.mongo.domain.service.impl.BookServiceImpl;

@DisplayName("Сервис по работе с книгами")
@ExtendWith(SpringExtension.class)
@Import(BookServiceImpl.class)
public class BookServiceImplTest {

	private static final String ID = "id";
	private static final String GENRE_COMEDY = "Комедия";
	private static final String AUTHOR_NAME_FOR_TEST = "AuthorNameTest";
	private static final String BOOK_NAME_TEST = "bookNameTest";

	@MockBean
	private AuthorRepository authorRepository;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private BookServiceImpl bookService;

	private Book bookForTest;

	@BeforeEach
	public void initMock() {
		bookForTest = new Book();
		bookForTest.setId(ID);
		bookForTest.setTitle(BOOK_NAME_TEST);
		bookForTest.setGenre(new Genre(GENRE_COMEDY));
		Author author = new Author(AUTHOR_NAME_FOR_TEST);
		bookForTest.setAuthor(author);

		when(bookRepository.save(any())).thenReturn(bookForTest);
		when(bookRepository.findAll()).thenReturn(Collections.singletonList(bookForTest));
		when(bookRepository.findAllByAuthorId(any())).thenReturn(Collections.singletonList(bookForTest));
		when(bookRepository.findByTitle(any())).thenReturn(Optional.of(bookForTest));

		when(authorRepository.save(any())).thenReturn(author);
		when(authorRepository.findByName(any())).thenReturn(Optional.of(author));
	}

	@Test
	@DisplayName("Добавление книги")
	public void testAdd() throws DaoException {
		String result = bookService.add(BOOK_NAME_TEST, AUTHOR_NAME_FOR_TEST, GENRE_COMEDY);
		assertEquals(ID, result);
	}

	@Test
	@DisplayName("Поиск всех книг")
	public void testGetAll() throws DaoException {

		List<Book> result = bookService.getAll();
		assertThat(result).contains(bookForTest);
	}

	@Test
	@DisplayName("Поиск по автору")
	public void testFindAllByAuthor() throws DaoException {
		List<Book> result = bookService.findAllByAuthor("author");
		assertThat(result).contains(bookForTest);
	}

	@Test
	@DisplayName("Поиск по названию")
	public void testFindByTitle() throws DaoException {
		List<Book> result = bookService.findAllByAuthor("author");
		assertThat(result).contains(bookForTest);
	}
}