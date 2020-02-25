package ru.akozlovskiy.springdz09.domain.service;

import java.util.List;
import java.util.Optional;

import ru.akozlovskiy.springdz09.domain.Book;
import ru.akozlovskiy.springdz09.exception.DaoException;

public interface BookService {

	long add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Optional<Book> findByName(String bookname);

	Optional<Book> findById(long id);

	void save(Book book);

	void update(long bookId, String bookName, long authorId, long genreId) throws DaoException;
}