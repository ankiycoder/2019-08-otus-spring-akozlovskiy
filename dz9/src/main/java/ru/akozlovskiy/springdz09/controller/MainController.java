package ru.akozlovskiy.springdz09.controller;

import java.util.List;
import java.util.stream.Collectors;

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

	private final BookService bookService;

	private final GenreRepository genreRepository;

	private final AuthorRepository authorRepository;

	public MainController(BookService bookService, GenreRepository genreRepository, AuthorRepository authorRepository) {
		this.bookService = bookService;
		this.genreRepository = genreRepository;
		this.authorRepository = authorRepository;
	}

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

	@PostMapping(value = { "/addBook" })
	public String saveBook(Model model, @ModelAttribute("bookDto") BookDTO bookDTO) throws DaoException {
		bookService.add(bookDTO.getTitle(), bookDTO.getAuthorName(), bookDTO.getGenre());
		return "redirect:/index";
	}

	@GetMapping("/updateBook/{id}")
	public String showUpdateBookPage(@PathVariable("id") long id, Model model) {
		Book book = bookService.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Book Id:" + id));
		model.addAttribute("book", new BookDTO(book));
		model.addAttribute("genres", genreRepository.findAll());
		model.addAttribute("authors", authorRepository.findAll());
		return "updateBook";
	}

	@PostMapping(value = { "/updateBook/{id}" })
	public String updateBook(@PathVariable("id") Long id, BookDTO bookDTO, Model model) throws DaoException {
		bookService.update(id, bookDTO.getTitle(), bookDTO.getAuthorId(), bookDTO.getGenreId());
		return "redirect:/index";
	}

	@GetMapping(value = { "/addAuthor" })
	public String showAddAuthor(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}

	@PostMapping(value = { "/addAuthor" })
	public String saveAuthor(Model model, @ModelAttribute("author") Author author) throws DaoException {
		authorRepository.save(author);
		return "redirect:/index";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateAuthorPage(@PathVariable("id") long id, Model model) {
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Author Id:" + id));
		model.addAttribute("author", author);
		return "updateAuthor";
	}

	@PostMapping(value = { "/updateAuthor/{id}" })
	public String updateAuthor(@PathVariable("id") Long id, Author author, Model model) {
		authorRepository.save(author);
		return "redirect:/index";
	}

	@GetMapping(value = { "/addGenre" })
	public String showAddGenre(Model model) {
		Genre genre = new Genre();
		model.addAttribute("genre", genre);
		return "addGenre";
	}

	@PostMapping(value = { "/addGenre" })
	public String saveGenre(Model model, @ModelAttribute("genre") Genre genre) throws DaoException {
		genreRepository.save(genre);
		return "redirect:/index";
	}

	@GetMapping("/updateGenre/{id}")
	public String showUpdateGenrePage(@PathVariable("id") long id, Model model) {
		Genre genre = genreRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Genre Id:" + id));
		model.addAttribute("genre", genre);
		return "updateGenre";
	}

	@PostMapping(value = { "/updateGenre/{id}" })
	public String updateGenre(Genre genre, Model model) {
		genreRepository.save(genre);
		return "redirect:/index";
	}
}