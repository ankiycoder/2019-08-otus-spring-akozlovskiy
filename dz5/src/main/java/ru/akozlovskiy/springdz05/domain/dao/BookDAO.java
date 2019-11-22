package ru.akozlovskiy.springdz05.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz05.domain.Book;
import ru.akozlovskiy.springdz05.exception.DaoException;

public interface BookDAO {

	List<Book> findAllByAuthor(String author) throws DaoException;

	Book getById(long id) throws DaoException;

	long add(String bookname, String authorName, String description) throws DaoException;
}
