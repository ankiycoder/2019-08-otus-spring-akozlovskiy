package ru.akozlovskiy.springdz10.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz10.domain.Genre;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.exception.DaoException;

@RestController
public class GenreRestController {

	private static Logger logger = LoggerFactory.getLogger(GenreRestController.class);

	private final GenreRepository genreRepository;

	public GenreRestController(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@GetMapping("/genre")
	public List<Genre> getAllGenre() throws DaoException {
		logger.debug("***Call getAllGenre");
		return genreRepository.findAll();
	}

	@PostMapping("/genre/update")
	public ResponseEntity<Void> updateGenre(@RequestBody Genre genre) throws DaoException {
		logger.debug("***Call updateGenre for GenrerID = {}", genre.getId());
		genreRepository.save(genre);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping("/genre/delete")
	public ResponseEntity<Void> deleteGenre(@RequestBody Genre genre) throws DaoException {
		logger.debug("***Call delete for GenreID = {}", genre.getId());
		genreRepository.deleteById(genre.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}