package ru.akozlovskiy.springdz07.domain.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz07.domain.dao.BookDAO;
import ru.akozlovskiy.springdz07.domain.dao.GenreDAO;
import ru.akozlovskiy.springdz07.domain.service.BookService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Service
public class BookServiceImpl implements BookService {

	private AuthorDAO authorDAO;

	private GenreDAO genreDAO;

	private BookDAO bookDAO;

	public BookServiceImpl(AuthorDAO authorDAO, GenreDAO genreDAO, BookDAO bookDAO) {
		this.authorDAO = authorDAO;
		this.bookDAO = bookDAO;
		this.genreDAO = genreDAO;
	}

	@Override
	public long add(String bookName, String authorName, String genreDescription) throws DaoException {

		Genre genre;
		try {
			genre = genreDAO.getByDescription(genreDescription);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + genreDescription);
		}

		Author author;
		try {
			author = authorDAO.getByName(authorName);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}

		Book book = new Book();
		book.setAuthor(author);
		book.setBookName(bookName);
		book.setGenre(genre);

		return bookDAO.add(book);
	}

	@Override
	public List<Book> getAll() throws DaoException {
		return bookDAO.getAll();
	}

	@Override
	public List<Book> findAllByAuthor(String author) throws DaoException {
		return bookDAO.findAllByAuthor(author);
	}

	@Override
	public Book findByName(String bookname) {
		return bookDAO.findByName(bookname);
	}

}
