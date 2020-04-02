package ru.akozlovskiy.springdz11.controller.rest;

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
import ru.akozlovskiy.springdz11.domain.dto.BookDTO;
import ru.akozlovskiy.springdz11.domain.service.BookService;
import ru.akozlovskiy.springdz11.exception.DaoException;

@RestController
public class BookRestController {

	private static Logger logger = LoggerFactory.getLogger(BookRestController.class);

	private final BookService bookService;

	public BookRestController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping("/api/book")
	public Flux<BookDTO> getAllBook() throws DaoException {
		logger.debug("***Call getAllBook");
		return bookService.getAll().map(BookDTO::new);
	}

	@DeleteMapping("/api/book/{id}")
	@ResponseStatus(value = HttpStatus.OK)
	public void deleteBook(@PathVariable("id") String id) {
		logger.debug("***Call delete for BookID = {}", id);
		bookService.removeById(id);
	}

	@PutMapping("/api/book")
	@ResponseStatus(value = HttpStatus.OK)
	public void updateBook(@RequestBody BookDTO bookDTO) {
		String id = bookDTO.getId();
		logger.debug("***Call updateBook for BookID = {}", id);
		bookService.update(id, bookDTO.getTitle(), bookDTO.getGenre());
	}
}