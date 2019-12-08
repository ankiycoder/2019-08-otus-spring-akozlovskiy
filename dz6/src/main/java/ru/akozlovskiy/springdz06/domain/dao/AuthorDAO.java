package ru.akozlovskiy.springdz06.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.exception.DaoException;

public interface AuthorDAO {

	Author getById(long id);

	List<Author> getAll();

	Author getByName(String name);

	long add(String name, String birthDate) throws DaoException;
}
