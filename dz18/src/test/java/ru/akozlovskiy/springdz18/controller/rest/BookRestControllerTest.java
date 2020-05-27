package ru.akozlovskiy.springdz18.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ru.akozlovskiy.springdz18.JsonUtil;
import ru.akozlovskiy.springdz18.controller.rest.BookRestController;
import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.Book;
import ru.akozlovskiy.springdz18.domain.Genre;
import ru.akozlovskiy.springdz18.domain.dto.BookDTO;
import ru.akozlovskiy.springdz18.domain.service.BookService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BookRestController.class)
@DisplayName("Rest контроллер для книг")
public class BookRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService bookService;

	private Book testBook;

	private BookDTO testBookDTO;

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
	@DisplayName("Поиск всех книг")
	public void testGetAllGenre() throws Exception {

		List<BookDTO> bookList = Arrays.asList(testBookDTO);
		when(bookService.getAll()).thenReturn(Arrays.asList(testBook));

		this.mockMvc.perform(get("/api/book")).andExpect(status().isOk())
				.andExpect(content().json(JsonUtil.mapToJson(bookList)));
	}

	@Test
	@DisplayName("Изменение книги")
	public void updateBook() throws Exception {

		when(bookService.getAll()).thenReturn(Arrays.asList(testBook));

		this.mockMvc.perform(
				put("/api/book").content(JsonUtil.mapToJson(testBookDTO)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}
}