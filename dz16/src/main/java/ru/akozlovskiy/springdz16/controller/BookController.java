package ru.akozlovskiy.springdz16.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz16.domain.dto.BookDTO;
import ru.akozlovskiy.springdz16.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz16.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz16.domain.service.BookService;
import ru.akozlovskiy.springdz16.exception.DaoException;

@Controller
public class BookController {

	private final BookService bookService;

	private final GenreRepository genreRepository;

	private final AuthorRepository authorRepository;

	public BookController(BookService bookService, GenreRepository genreRepository, AuthorRepository authorRepository) {
		this.bookService = bookService;
		this.genreRepository = genreRepository;
		this.authorRepository = authorRepository;
	}

	@GetMapping(value = { "/addBook" })
	public String showAddBookPage(Model model) {

		BookDTO bookDTO = new BookDTO();
		model.addAttribute("bookDto", bookDTO);
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		return "addBook";
	}

	@PostMapping(value = { "/addBook" })
	public String saveBook(Model model, @ModelAttribute("bookDto") @Valid BookDTO bookDTO, BindingResult result)
			throws DaoException {
		if (result.hasErrors()) {
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("authors", authorRepository.findAll());
			model.addAttribute("bookDto", bookDTO);
			return "addBook";
		}

		bookService.add(bookDTO.getTitle(), bookDTO.getAuthorName(), bookDTO.getGenre());
		return "redirect:/index";
	}
}