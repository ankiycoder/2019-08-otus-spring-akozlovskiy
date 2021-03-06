package ru.akozlovskiy.springdz17.controller.rest;

import java.util.List;
import java.util.stream.Collectors;

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

import ru.akozlovskiy.springdz17.domain.Book;
import ru.akozlovskiy.springdz17.domain.dto.BookDTO;
import ru.akozlovskiy.springdz17.domain.service.BookService;
import ru.akozlovskiy.springdz17.exception.DaoException;

@RestController
public class BookRestController {

	private static Logger logger = LoggerFactory.getLogger(BookRestController.class);

	private final BookService bookService;

	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/book")
	public List<BookDTO> getAllBook() throws DaoException {
		logger.debug("***Call getAllBook");
		return bookService.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
	}

	@DeleteMapping("/api/book/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteBook(@PathVariable("id") long id) throws DaoException {
		logger.debug("***Call delete for BookID = {}", id);
		bookService.delete(id);
	}

	@PutMapping("/api/book")
	@ResponseStatus(value = HttpStatus.OK)
	public Book updateBook(@RequestBody BookDTO bookDTO) throws DaoException {
		Long id = bookDTO.getId();
		logger.debug("***Call updateBook for BookID = {}", id);
		return bookService.update(id, bookDTO.getTitle(), bookDTO.getAuthorId(), bookDTO.getGenreId());
	}
}