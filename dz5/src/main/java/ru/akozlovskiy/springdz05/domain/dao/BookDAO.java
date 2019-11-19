package ru.akozlovskiy.springdz05.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz05.domain.Book;

public interface BookDAO {

	void insert(Book book);

	Book getById(int id);

	List<Book> getAll();

}
