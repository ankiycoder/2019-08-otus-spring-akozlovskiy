package ru.akozlovskiy.springdz11.controller.rest;

import java.time.Duration;

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

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;

@RestController
public class AuthorRestController {

	private static final int DELAY_PER_ITEM_MS = 10;

	private static Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorRepository authorRepository;

	public AuthorRestController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping("/api/author")
	@ResponseStatus(value = HttpStatus.OK)
	public Flux<Author> getAllAuthor() {
		logger.debug("***Call getAllAuthor");
		return authorRepository.findAll().delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
	}

	@DeleteMapping("/api/author/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<Void> deleteAuthor(@PathVariable("id") String id) {
		logger.debug("***Call delete for AuthorID = {}", id);
		return authorRepository.deleteById(id);
	}

	@PutMapping("/api/author")
	@ResponseStatus(value = HttpStatus.OK)
	public Mono<Author> updateAuthor(@RequestBody Author author) {
		logger.debug("***Call updateAuthor for author  {}", author);
		return authorRepository.save(author);
	}
}