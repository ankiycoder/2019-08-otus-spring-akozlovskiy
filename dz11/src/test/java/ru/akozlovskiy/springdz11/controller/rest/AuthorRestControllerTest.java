package ru.akozlovskiy.springdz11.controller.rest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.assertj.core.api.Assertions.anyOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
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
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;

@ExtendWith(SpringExtension.class)
@WebFluxTest(value = AuthorRestController.class)
@DisplayName("Rest контроллер для авторов")
public class AuthorRestControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private AuthorRepository authorRepository;

	private Author testAuthor = new Author("AuthorName", "1891-05-15");

	@Test
	@DisplayName("Поиск всех авторов")
	public void testFindAllAuthor() throws Exception {

		when(authorRepository.findAll()).thenReturn(Flux.just(testAuthor));

		webTestClient.get().uri("/api/author").accept(MediaType.APPLICATION_JSON).exchange().expectStatus().isOk()
				.expectBodyList(Author.class).consumeWith(response -> assertThat(response.getResponseBody().get(0))
						.isEqualToComparingFieldByField(testAuthor));

		Mockito.verify(authorRepository, times(1)).findAll();
	}

	@Test
	@DisplayName("Удаление автора")
	public void testDeleteAuthor() throws Exception {

		String authorId = "1";
		Mockito.when(authorRepository.deleteById(authorId)).thenReturn(Mono.empty());

		webTestClient.delete().uri("/api/author/{id}", authorId).accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk();

		Mockito.verify(authorRepository, times(1)).deleteById(authorId);
	}

	@Test
	@DisplayName("Изменение автора")
	public void updateAuthor() throws Exception {

		when(authorRepository.save(any())).thenReturn(Mono.just(testAuthor));

		webTestClient.put().uri("/api/author").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).body(Mono.just(testAuthor), Author.class).exchange().expectStatus()
				.isOk().expectBody(Author.class).consumeWith(
						response -> assertThat(testAuthor).isEqualToComparingFieldByField(response.getResponseBody()));

		ArgumentCaptor<Author> requestCaptor = ArgumentCaptor.forClass(Author.class);
		Mockito.verify(authorRepository, times(1)).save(requestCaptor.capture());
		assertThat(requestCaptor.getValue()).isEqualToComparingFieldByField(testAuthor);
	}
}