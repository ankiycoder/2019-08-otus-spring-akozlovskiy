package ru.akozlovskiy.springdz07.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.exception.DaoException;

public interface BookDAO {

	//long add(String bookname, String authorName, String description) throws DaoException;

	Book getById(long id) throws DaoException;

	Book findByName(String Name);

	List<Book> findAllByAuthor(String author) throws DaoException;
	
	List<Book> getAll() throws DaoException;

	long add(Book book) throws DaoException;
}
