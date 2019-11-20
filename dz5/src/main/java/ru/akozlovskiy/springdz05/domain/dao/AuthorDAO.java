package ru.akozlovskiy.springdz05.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz05.domain.Author;

public interface AuthorDAO {

	void insert(Author author);

	Author getById(long id);

	List<Author> getAll();
}
