package ru.akozlovskiy.springdz18.domain.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ru.akozlovskiy.springdz18.domain.Genre;
import ru.akozlovskiy.springdz18.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz18.domain.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	private final GenreRepository genreRepository;

	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Override
	public Genre findByDescription(String genreDescription) {
		return genreRepository.findByDescription(genreDescription);
	}

	@Override
	public void save(Genre genre) {
		genreRepository.save(genre);
	}

	@Override
	public void deleteById(long id) {
		genreRepository.deleteById(id);
	}

	@Override
	@HystrixCommand(fallbackMethod = "findAllFallback", ignoreExceptions = Throwable.class)
	public List<Genre> findAll() {
		return genreRepository.findAll();
	}

	public List<Genre> findAllFallback() {
		Genre genre = new Genre();
		genre.setDescription("N/A");
		return Collections.singletonList(genre);
	}
}