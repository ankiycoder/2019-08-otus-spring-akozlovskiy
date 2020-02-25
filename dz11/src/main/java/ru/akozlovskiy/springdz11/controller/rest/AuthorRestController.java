package ru.akozlovskiy.springdz11.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;

@RestController
public class AuthorRestController {

	private static Logger logger = LoggerFactory.getLogger(AuthorRestController.class);

	private final AuthorRepository authorRepository;

	public AuthorRestController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping(value = "/api/author", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Author> getAllAuthor() {
		logger.debug("***Call getAllAuthor");
		return authorRepository.findAll();
	}

	@DeleteMapping("/api/author/{id}")
	public Mono<ResponseEntity<Void>> deleteAuthor(@PathVariable("id") long id) {
		logger.debug("***Call delete for AuthorID = {}", id);
		return authorRepository.deleteById(id).then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)));
	}

	@PutMapping("/api/author")
	public Mono<ResponseEntity<Author>> updateAuthor(@RequestBody Author author) {
		logger.debug("***Call updateAuthor for authorID = {}", author.getId());

		return authorRepository.findById(author.getId()).flatMap(find -> authorRepository.save(author))
				.map(updatedTweet -> new ResponseEntity<>(updatedTweet, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}