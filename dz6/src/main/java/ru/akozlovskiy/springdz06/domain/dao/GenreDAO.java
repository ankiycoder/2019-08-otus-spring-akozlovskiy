package ru.akozlovskiy.springdz06.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz06.domain.Genre;

public interface GenreDAO {

	long add(String description);

	Genre getById(long id);

	List<Genre> getAll();

	Genre getByDescription(String description);
}