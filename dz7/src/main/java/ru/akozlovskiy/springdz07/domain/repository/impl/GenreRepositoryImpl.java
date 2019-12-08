package ru.akozlovskiy.springdz07.domain.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepositoryCustom;

@Repository
public class GenreRepositoryImpl implements GenreRepositoryCustom {

	@Autowired
	GenreRepository genreRepository;

	@Override
	public long add(String description) {
		Genre genre = new Genre();
		genre.setDescription(description);
		genreRepository.save(genre);
		return genre.getId();
	}
}
