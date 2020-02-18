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

import ru.akozlovskiy.springdz10.domain.Author;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz10.exception.DaoException;

@RestController
public class AuthorRestController {

	private static Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorRepository authorRepository;

	public AuthorRestController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping("/author")
	public List<Author> getAllAuthor() throws DaoException {
		logger.debug("***Call getAllAuthor");
		return authorRepository.findAll();
	}

	@DeleteMapping("/author/delete")
	public ResponseEntity<Void> deleteAuthor(@RequestBody Author author) throws DaoException {
		logger.debug("***Call delete for AuthorID = {}", author.getId());
		authorRepository.deleteById(author.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/author/update")
	public ResponseEntity<Void> updateAuthor(@RequestBody Author author) throws DaoException {
		logger.debug("***Call updateAuthor for authorID = {}", author.getId());
		authorRepository.save(author);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}