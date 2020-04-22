package ru.akozlovskiy.springdz11.controller.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.domain.Genre;
import ru.akozlovskiy.springdz11.domain.dto.BookDTO;
import ru.akozlovskiy.springdz11.domain.service.BookService;

@ExtendWith(SpringExtension.class)
@WebFluxTest(value = BookRestController.class)
@DisplayName("Rest контроллер для книг")
public class BookRestControllerTest {

	@MockBean
	private BookService bookService;

	private Book testBook;

	private BookDTO testBookDTO;

	@Autowired
	private WebTestClient webTestClient;

	@BeforeEach
	public void beforeEach() {

		String id = "1";
		testBook = new Book();
		testBook.setId(id);
		testBook.setTitle("title");

		Author author = new Author();
		author.setId(id);
		author.setName("name");
		testBook.setAuthor(author);

		Genre genre = new Genre("Description");
		testBook.setGenre(genre);

		testBookDTO = new BookDTO(testBook);
	}

	@Test
	@DisplayName("Поиск всех книг")
	public void testGetAllBook() throws Exception {

		when(bookService.getAll()).thenReturn(Flux.just(testBook));

		webTestClient.get().uri("/api/book").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBodyList(BookDTO.class).consumeWith(response -> assertThat(response.getResponseBody().get(0))
						.isEqualToComparingFieldByField(testBookDTO));

		Mockito.verify(bookService, times(1)).getAll();

	}

	@Test
	@DisplayName("Удаление книги")
	public void testDeleteAuthor() throws Exception {

		String bookId = "1";
		Mockito.when(bookService.removeById(bookId)).thenReturn(Mono.empty());

		webTestClient.delete().uri("/api/book/{id}", bookId).accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk();

		Mockito.verify(bookService, times(1)).removeById(bookId);
	}

	@Test
	@DisplayName("Изменение книги")
	public void testUpdateBook() throws Exception {

		when(bookService.update(any(), any(), any(), any())).thenReturn(Mono.just(testBook));

		webTestClient.put().uri("/api/book").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(testBookDTO), BookDTO.class).exchange().expectStatus().isOk().expectBody(Book.class)
				.consumeWith(response -> {
					Book responseBody = response.getResponseBody();
					assertThat(testBook).usingRecursiveComparison().isEqualTo(responseBody);
				});
	}
}