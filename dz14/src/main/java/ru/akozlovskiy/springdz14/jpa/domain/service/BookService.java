package ru.akozlovskiy.springdz14.jpa.domain.service;

import java.util.List;
import java.util.Optional;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.jpa.domain.Book;

public interface BookService {

	long add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Optional<Book> findByName(String bookname);
}