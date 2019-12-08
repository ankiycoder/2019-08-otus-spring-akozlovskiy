package ru.akozlovskiy.springdz07.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.exception.DaoException;

public interface AuthorDAO {

	Author getById(long id);

	List<Author> getAll();

	Author getByName(String name);

	long add(String name, String birthDate) throws DaoException;
}
