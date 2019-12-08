package ru.akozlovskiy.springdz07.domain.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz07.domain.repository.BookRepository;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz07.domain.service.BookService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Service
public class BookServiceImpl implements BookService {

	private AuthorRepository authorRepository;

	private GenreRepository genreRepository;

	private BookRepository bookRepository;

	public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository,
			GenreRepository genreRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.genreRepository = genreRepository;
	}

	@Override
	public long add(String bookName, String authorName, String genreDescription) throws DaoException {

		Genre genre;
		try {
			genre = genreRepository.findByDescription(genreDescription);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + genreDescription);
		}

		Author author;
		try {
			author = authorRepository.findByName(authorName);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}

		Book book = new Book();
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setGenre(genre);

		return bookRepository.save(book).getId();
	}

	@Override
	public List<Book> getAll() throws DaoException {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findAllByAuthor(String author) throws DaoException {
		return bookRepository.findAllByAuthor(author);
	}

	@Override
	public Book findByName(String bookname) {
		return bookRepository.findByBookName(bookname);
	}

}
