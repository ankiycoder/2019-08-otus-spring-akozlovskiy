package ru.akozlovskiy.springdz09.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.akozlovskiy.springdz09.domain.Author;
import ru.akozlovskiy.springdz09.domain.Book;
import ru.akozlovskiy.springdz09.domain.Genre;
import ru.akozlovskiy.springdz09.domain.dto.BookDTO;
import ru.akozlovskiy.springdz09.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz09.domain.repository.BookRepository;
import ru.akozlovskiy.springdz09.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz09.domain.service.BookService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookController.class)
@DisplayName("Контроллер по работе с книгами")
public class BookControllerTest {

	@MockBean
	private BookService bookService;

	@MockBean
	private GenreRepository genreRepository;

	@MockBean
	private AuthorRepository authorRepository;

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private MockMvc mvc;

	private BookDTO testBookDTO;
	private Book testBook;
	private Long id = 1l;

	@BeforeEach
	public void beforeEach() {
		
		Long id = 1l;
		
		testBook = new Book();
		testBook.setId(id);
		testBook.setTitle("title");
		
		Author author = new Author();
		author.setId(id);
		author.setName("name");
		testBook.setAuthor(author);
		
		Genre genre = new Genre();
		genre.setId(id);
		genre.setDescription("Description");
		testBook.setGenre(genre);
				
		testBookDTO = new BookDTO(testBook);
	}

	@Test
	@DisplayName("Добавление книги")
	void testAddBook() throws Exception {

		when(bookService.add("bookName", "authorName", "genreDescription")).thenReturn(1l);

		RequestBuilder request = MockMvcRequestBuilders.post("/addBook").param("title", testBookDTO.getTitle())
				.param("id", String.valueOf(testBookDTO.getId())).param("authorName", testBookDTO.getAuthorName())
				.param("genre", testBookDTO.getGenre());

		mvc.perform(request).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));

		verify(bookService, times(1)).add(testBookDTO.getTitle(), testBookDTO.getAuthorName(), testBookDTO.getGenre());
	}

	@Test
	@DisplayName("Изменение книги")
	void testShowUpdateBookPage() throws Exception {

		Optional<Book> bookOpt = Optional.of(testBook);

		when(bookService.findById(id)).thenReturn(bookOpt);

		RequestBuilder request = MockMvcRequestBuilders.get("/updateBook/{id}", id);

		mvc.perform(request).andExpect(status().isOk()).andExpect(view().name("updateBook"))
				.andExpect(model().attribute("bookDto", hasProperty("id", equalTo(id))))
				.andExpect(model().attribute("bookDto", hasProperty("authorName", equalTo(testBook.getAuthor().getName()))))
				.andExpect(model().attribute("bookDto", hasProperty("genre", equalTo(testBook.getGenre().getDescription()))))
				.andExpect(model().attribute("bookDto", hasProperty("title", equalTo(testBookDTO.getTitle()))));

		verify(bookService, times(1)).findById(id);
	}
}