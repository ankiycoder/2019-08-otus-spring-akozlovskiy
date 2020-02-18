package ru.akozlovskiy.springdz10.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz10.domain.dto.BookDTO;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.domain.service.BookService;
import ru.akozlovskiy.springdz10.exception.DaoException;

@RestController
public class BookRestController {

	private static Logger logger = LoggerFactory.getLogger(BookRestController.class);

	private final BookService bookService;

	public BookRestController(BookService bookService, GenreRepository genreRepository,
			AuthorRepository authorRepository) {
		this.bookService = bookService;
	}

	@GetMapping("/book")
	public List<BookDTO> getAllBook() throws DaoException {
		logger.debug("***Call getAllBook");
		return bookService.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
	}

	@DeleteMapping("/book/delete")
	public ResponseEntity<Void> saveBook(@RequestBody BookDTO bookDTO) throws DaoException {
		logger.debug("***Call delete for BookID = {}", bookDTO.getId());
		bookService.delete(bookDTO.getId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PostMapping("/book/update")
	public ResponseEntity<Void> updateBook(@RequestBody BookDTO bookDTO) throws DaoException {
		logger.debug("***Call updateBook for BookID = {}", bookDTO.getId());
		bookService.update(bookDTO.getId(), bookDTO.getTitle(), bookDTO.getAuthorId(), bookDTO.getGenreId());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}