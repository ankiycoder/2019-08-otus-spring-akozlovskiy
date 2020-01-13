package ru.akozlovskiy.springdz09.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz09.domain.Author;
import ru.akozlovskiy.springdz09.domain.Book;
import ru.akozlovskiy.springdz09.domain.Genre;
import ru.akozlovskiy.springdz09.domain.dto.BookDTO;

@Controller
public class MainController {

	private static List<Book> books = new ArrayList<>();
	private static List<Author> authors = new ArrayList<>();

	static {
		Book book1 = new Book();
		book1.setTitle("title");

		Book book2 = new Book();
		book2.setTitle("title2");

		books.add(book1);
		books.add(book2);
		
		Author author = new Author();
		author.setName("Маршак");
		authors.add(author);
	}

	// ​​​​​​​
	// Вводится (inject) из application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@GetMapping(value = { "/bookList" })
	public String bookList(Model model) {

		List<BookDTO> bookDTOList = books.stream().map(BookDTO::new).collect(Collectors.toList());

		model.addAttribute("bookDTOList", bookDTOList);

		return "bookList";
	}
	
	@GetMapping(value = { "/authorList" })
	public String authorList(Model model) {

		model.addAttribute("authors", authors);

		return "authorList";
	}

	@GetMapping(value = { "/addBook" })
	public String showAddBookPage(Model model) {

		BookDTO  bookDTO = new BookDTO();
		model.addAttribute("bookDto", bookDTO);

		return "addBook";
	}

	@PostMapping(value = { "/addBook" })
	public String saveBook(Model model, //
			@ModelAttribute("bookDto") BookDTO  bookDTO) {

			Book book = new Book();
			book.setTitle(bookDTO.getTitle());
			
			Author author = new Author();
			author.setName(bookDTO.getAuthorName());
			book.setAuthor(author);
			
			Genre genre = new Genre();
			genre.setDescription(bookDTO.getGenre());
			book.setGenre(genre);

			books.add(book);

			return "redirect:/bookList";
		}

		//model.addAttribute("errorMessage", errorMessage);
		//return "addBook";
	
}