package ru.akozlovskiy.springdz18.controller.rest;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.akozlovskiy.springdz18.JsonUtil;
import ru.akozlovskiy.springdz18.controller.rest.AuthorRestController;
import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.repository.AuthorRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthorRestController.class)
@DisplayName("Rest контроллер для авторов")
public class AuthorRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthorRepository authorRepository;

	@Test
	@DisplayName("Поиск всех авторов")
	public void testGetAllGenre() throws Exception {

		Author author = new Author(1l, "name", null);
		Author author2 = new Author(2l, "name2", null);

		List<Author> authorList = Arrays.asList(author, author2);
		when(authorRepository.findAll()).thenReturn(authorList);

		this.mockMvc.perform(get("/api/author")).andExpect(status().isOk())
				.andExpect(content().json(JsonUtil.mapToJson(authorList)));
	}

	@Test
	@DisplayName("Изменение автора")
	public void updateGenre() throws Exception {
		Author author = new Author(1l, "name", null);
		this.mockMvc.perform(
				put("/api/author").content(JsonUtil.mapToJson(author)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Удаление автора")
	public void deleteGenre() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/author/{id}", "1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
