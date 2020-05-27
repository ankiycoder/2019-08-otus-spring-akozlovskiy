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

import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.service.AuthorService;

@RestController
public class AuthorRestController {

	private static Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorService authorService;

	public AuthorRestController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/api/author")
	public List<Author> getAllAuthor() {
		logger.debug("***Call getAllAuthor");
		return authorService.findAll();
	}

	@DeleteMapping("/api/author/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteAuthor(@PathVariable("id") long id) {
		logger.debug("***Call delete for AuthorID = {}", id);
		authorService.deleteById(id);
	}

	@PutMapping("/api/author")
	@ResponseStatus(value = HttpStatus.OK)
	public Author updateAuthor(@RequestBody Author author) {
		logger.debug("***Call updateAuthor for authorID = {}", author.getId());
		return authorService.save(author);
	}
}