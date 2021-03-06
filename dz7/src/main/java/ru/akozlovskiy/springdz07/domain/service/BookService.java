package ru.akozlovskiy.springdz07.domain.service;

import java.util.List;
import java.util.Optional;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.exception.DaoException;

public interface BookService {

	long add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Optional<Book> findByName(String bookname);
}