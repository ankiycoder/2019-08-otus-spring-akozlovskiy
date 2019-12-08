package ru.akozlovskiy.springdz07.domain.repository;

import ru.akozlovskiy.springdz07.exception.DaoException;

public interface AuthorRepositoryCustom {
	public long add(String name, String birthDate) throws DaoException;
}
