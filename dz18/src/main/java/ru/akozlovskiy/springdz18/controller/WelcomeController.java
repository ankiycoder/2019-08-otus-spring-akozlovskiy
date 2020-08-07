package ru.akozlovskiy.springdz18.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.akozlovskiy.springdz18.domain.Book;
import ru.akozlovskiy.springdz18.domain.dto.BookDTO;
import ru.akozlovskiy.springdz18.domain.service.AuthorService;
import ru.akozlovskiy.springdz18.domain.service.BookService;
import ru.akozlovskiy.springdz18.domain.service.GenreService;
import ru.akozlovskiy.springdz18.exception.DaoException;

@Controller
public class WelcomeController {

	private final BookService bookService;

	private final GenreService genreSecrvice;

	private final AuthorService authorService;

	public WelcomeController(BookService bookService, GenreService genreSecrvice, AuthorService authorService) {
		this.bookService = bookService;
		this.genreSecrvice = genreSecrvice;
		this.authorService = authorService;
	}

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) throws DaoException {

		List<Book> books = bookService.getAll();
		List<BookDTO> bookDTOList = books.stream().map(BookDTO::new).collect(Collectors.toList());
		model.addAttribute("bookDTOList", bookDTOList);

		model.addAttribute("genres", genreSecrvice.findAll());
		model.addAttribute("authors", authorService.findAll());
		return "index";
	}

}
