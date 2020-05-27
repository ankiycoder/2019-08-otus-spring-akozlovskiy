package ru.akozlovskiy.springdz18.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz18.domain.Genre;

public interface GenreService {

	Genre findByDescription(String genreDescription);

	void save(Genre genre);

	void deleteById(long id);

	List<Genre> findAll();
}
