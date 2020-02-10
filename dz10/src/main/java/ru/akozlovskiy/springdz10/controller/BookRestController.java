package ru.akozlovskiy.springdz10.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz10.domain.dto.BookDTO;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.domain.service.BookService;
import ru.akozlovskiy.springdz10.exception.DaoException;

@RestController
public class BookRestController {

	private final BookService bookService;

	private final GenreRepository genreRepository;

	private final AuthorRepository authorRepository;

	public BookRestController(BookService bookService, GenreRepository genreRepository,
			AuthorRepository authorRepository) {
		this.bookService = bookService;
		this.genreRepository = genreRepository;
		this.authorRepository = authorRepository;
	}

	@GetMapping("/book")
	public List<BookDTO> getAllBook() throws DaoException {
		return bookService.getAll().stream().map(BookDTO::new).collect(Collectors.toList());
	}
	
	@PostMapping("/book/save")
    public ResponseEntity<Void> saveBook(BookDTO bookDTO) throws DaoException {
		System.out.print("***********************save");
		bookService.add(bookDTO.getTitle(), bookDTO.getAuthorName(), bookDTO.getGenre());
		return new ResponseEntity<Void>(HttpStatus.OK);
    }
	
	@PostMapping("/book/update")
	public ResponseEntity<Void>updateBook(BookDTO bookDTO) throws DaoException {
		System.out.print("*********************** update");
		/*if (result.hasErrors()) {
			model.addAttribute("bookDto", bookDTO);
			model.addAttribute("genres", genreRepository.findAll());
			model.addAttribute("authors", authorRepository.findAll());
			return "updateBook";
		}*/
		bookService.update(1l, bookDTO.getTitle(), bookDTO.getAuthorId(), bookDTO.getGenreId());
		//return "redirect:/index";
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}