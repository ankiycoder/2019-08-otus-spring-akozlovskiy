package ru.akozlovskiy.springdz11.domain.service;

import java.util.List;
import java.util.Optional;

import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.exception.DaoException;

public interface BookService {

	String add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Optional<Book> findByTitle(String bookname);
	
	void removeByTitle(String title);
}