package ru.akozlovskiy.springdz17.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.akozlovskiy.springdz17.domain.Book;
import ru.akozlovskiy.springdz17.domain.dto.BookDTO;
import ru.akozlovskiy.springdz17.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz17.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz17.domain.service.BookService;
import ru.akozlovskiy.springdz17.exception.DaoException;

@Controller
public class WelcomeController {

	private final BookService bookService;

	private final GenreRepository genreRepository;

	private final AuthorRepository authorRepository;

	public WelcomeController(BookService bookService, GenreRepository genreRepository,
			AuthorRepository authorRepository) {
		this.bookService = bookService;
		this.genreRepository = genreRepository;
		this.authorRepository = authorRepository;
	}

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) throws DaoException {

		List<Book> books = bookService.getAll();
		List<BookDTO> bookDTOList = books.stream().map(BookDTO::new).collect(Collectors.toList());
		model.addAttribute("bookDTOList", bookDTOList);

		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		return "index";
	}

}
