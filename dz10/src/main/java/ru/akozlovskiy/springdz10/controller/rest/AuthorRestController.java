package ru.akozlovskiy.springdz10.controller.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz10.domain.Author;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;

@RestController
public class AuthorRestController {

	private static Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorRepository authorRepository;

	public AuthorRestController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping("/author")
	public List<Author> getAllAuthor() {
		logger.debug("***Call getAllAuthor");
		return authorRepository.findAll();
	}

	@DeleteMapping("/author/delete")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteAuthor(@RequestBody Author author) {
		logger.debug("***Call delete for AuthorID = {}", author.getId());
		authorRepository.deleteById(author.getId());
	}

	@PostMapping("/author/update")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateAuthor(@RequestBody Author author) {
		logger.debug("***Call updateAuthor for authorID = {}", author.getId());
		authorRepository.save(author);
	}
}