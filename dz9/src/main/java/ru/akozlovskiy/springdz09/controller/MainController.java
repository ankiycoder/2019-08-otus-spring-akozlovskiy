package ru.akozlovskiy.springdz09.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz09.domain.Author;
import ru.akozlovskiy.springdz09.domain.Book;
import ru.akozlovskiy.springdz09.domain.Genre;
import ru.akozlovskiy.springdz09.domain.dto.BookDTO;
import ru.akozlovskiy.springdz09.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz09.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz09.domain.service.BookService;
import ru.akozlovskiy.springdz09.exception.DaoException;

@Controller
public class MainController {

	@Autowired
	private BookService bookService;

	@Autowired
	private GenreRepository genreRepository;

	@Autowired
	private AuthorRepository authorRepository;

	@Value("${error.message}")
	private String errorMessage;

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

	@GetMapping(value = { "/addAuthor" })
	public String showAddAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}
	
	@GetMapping(value = { "/addGenre" })
	public String showAddGenre(Model model) {
		Genre genre = new Genre();
		model.addAttribute("genre", genre);
		return "addGenre";
	}
	
	@GetMapping(value = { "/updateGenre/{id}" })
	public String updateGenre(@PathVariable("id") Long id, Model model) {
		System.out.println("DELETE for ID = " + id);

		//model.addAttribute("genres", genres);
		///model.addAttribute("authors", authors);
		return "index";
	}

	@GetMapping(value = { "/deleteGenre/{id}" })
	public String deleteGenre(@PathVariable("id") Long id, Model model) {
		System.out.println("DELETE for ID = " + id);

		//model.addAttribute("genres", genres);
		//model.addAttribute("authors", authors);
		return "index";
	}

	@PostMapping(value = { "/addBook" })
	public String saveBook(Model model, @ModelAttribute("bookDto") BookDTO bookDTO) throws DaoException {
		bookService.add(bookDTO.getTitle(), bookDTO.getAuthorName(), bookDTO.getGenre());
		return "redirect:/index";
	}

	@PostMapping(value = { "/addAuthor" })
	public String saveAuthor(Model model, @ModelAttribute("author") Author author) throws DaoException {
		authorRepository.save(author);
		return "redirect:/index";
	}
	
	@PostMapping(value = { "/addGenre" })
	public String saveGenre(Model model, @ModelAttribute("genre") Genre genre) throws DaoException {
		genreRepository.save(genre);
		return "redirect:/index";
	}

	// model.addAttribute("errorMessage", errorMessage);
	// return "addBook";

}