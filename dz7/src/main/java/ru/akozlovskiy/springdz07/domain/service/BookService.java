package ru.akozlovskiy.springdz07.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.exception.DaoException;

public interface BookService {

	long add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Book findByName(String bookname);

}
