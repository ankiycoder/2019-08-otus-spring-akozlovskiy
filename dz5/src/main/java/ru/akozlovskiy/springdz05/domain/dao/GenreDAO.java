package ru.akozlovskiy.springdz05.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Genre;

public interface GenreDAO {
	
	void insert(Genre genre);

	Genre getById(int id);

	List<Author> getAll();

}
