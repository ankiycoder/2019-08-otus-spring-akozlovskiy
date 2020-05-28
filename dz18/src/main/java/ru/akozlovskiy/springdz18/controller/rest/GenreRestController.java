package ru.akozlovskiy.springdz18.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz18.domain.Genre;
import ru.akozlovskiy.springdz18.domain.service.GenreService;

@RestController
public class GenreRestController {

	private static Logger logger = LoggerFactory.getLogger(GenreRestController.class);

	private final GenreService genreService;

	public GenreRestController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping("/api/genre")
	public List<Genre> getAllGenre() {
		logger.debug("***Call getAllGenre");
		return genreService.findAll();
	}

	@PutMapping("/api/genre")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateGenre(@RequestBody Genre genre) {
		logger.debug("***Call updateGenre for GenrerID = {}", genre.getId());
		genreService.save(genre);
	}

	@DeleteMapping("/api/genre/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteGenre(@PathVariable("id") long id) {
		logger.debug("***Call delete for GenreID = {}", id);
		genreService.deleteById(id);
	}
}