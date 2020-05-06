package ru.akozlovskiy.springdz14.mongo.domain.service;

import java.util.List;
import java.util.Optional;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Book;

public interface BookService {

	String add(String bookName, String authorName, String genreDescription) throws DaoException;

	List<Book> getAll() throws DaoException;

	List<Book> findAllByAuthor(String author) throws DaoException;

	Optional<Book> findByTitle(String bookname);
	
	void removeByTitle(String title);
}