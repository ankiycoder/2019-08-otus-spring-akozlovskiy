package ru.akozlovskiy.springdz11.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.domain.dto.BookDTO;
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;

import ru.akozlovskiy.springdz11.domain.service.BookService;
import ru.akozlovskiy.springdz11.exception.DaoException;

@Controller
public class BookController {

	private final BookService bookService;

	private final AuthorRepository authorRepository;

	public BookController(BookService bookService, AuthorRepository authorRepository) {
		this.bookService = bookService;
		this.authorRepository = authorRepository;
	}

	@GetMapping(value = { "/addBook" })
	public String showAddBookPage(Model model) {

		BookDTO bookDTO = new BookDTO();
		model.addAttribute("bookDto", bookDTO);
		model.addAttribute("authors", authorRepository.findAll());
		return "addBook";
	}

	@PostMapping(value = { "/addBook" })
	public String saveBook(Model model, @ModelAttribute("bookDto") @Valid BookDTO bookDTO, BindingResult result)
			throws DaoException {
		if (result.hasErrors()) {
			model.addAttribute("authors", authorRepository.findAll());
			model.addAttribute("bookDto", bookDTO);
			return "addBook";
		}

		Mono<Book> bookAdd = bookService.add(bookDTO.getTitle(), bookDTO.getAuthorName(), bookDTO.getGenre());
		bookAdd.subscribe();
		return "redirect:/index";
	}
}