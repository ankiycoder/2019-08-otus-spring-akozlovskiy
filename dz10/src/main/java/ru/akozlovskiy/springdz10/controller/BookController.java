package ru.akozlovskiy.springdz10.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz10.domain.Book;
import ru.akozlovskiy.springdz10.domain.dto.BookDTO;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.domain.service.BookService;
import ru.akozlovskiy.springdz10.exception.DaoException;

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
	
	@GetMapping(value = { "/", "/index" })
	public String index(Model model) throws DaoException {

		List<Book> books = bookService.getAll();
		List<BookDTO> bookDTOList = books.stream().map(BookDTO::new).collect(Collectors.toList());
		model.addAttribute("bookDTOList", bookDTOList);

		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		return "index";
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

	@GetMapping("/updateBook/{id}")
	public String showUpdateBookPage(@PathVariable("id") long id, Model model) {
		Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Book Id:" + id));
		model.addAttribute("bookDto", new BookDTO(book));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		return "updateBook";
	}

	@PostMapping(value = { "/updateBook/{id}" })
	public String updateBook(Model model, @ModelAttribute("bookDto") @Valid BookDTO bookDTO, BindingResult result,
			@PathVariable("id") Long id) throws DaoException {
		if (result.hasErrors()) {
			model.addAttribute("bookDto", bookDTO);
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("authors", authorRepository.findAll());
			return "updateBook";
		}
		bookService.update(id, bookDTO.getTitle(), bookDTO.getAuthorId(), bookDTO.getGenreId());
		return "redirect:/index";
	}

}