package ru.akozlovskiy.springdz11.controller.rest;

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

import ru.akozlovskiy.springdz11.JsonUtil;
import ru.akozlovskiy.springdz11.controller.rest.GenreRestController;
import ru.akozlovskiy.springdz11.domain.Genre;
import ru.akozlovskiy.springdz11.domain.repository.GenreRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GenreRestController.class)
@DisplayName("Rest контроллер для жанров")
public class GenreRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private GenreRepository genreRepository;

	@Test
	@DisplayName("Поиск всех жанров")
	public void testGetAllGenre() throws Exception {

		Genre genre1 = new Genre(1, "test1");
		Genre genre2 = new Genre(2, "test2");

		List<Genre> genreList = Arrays.asList(genre1, genre2);
		when(genreRepository.findAll()).thenReturn(genreList);

		this.mockMvc.perform(get("/api/genre")).andExpect(status().isOk())
				.andExpect(content().json(JsonUtil.mapToJson(genreList)));
	}

	@Test
	@DisplayName("Изменение жанра")
	public void updateGenre() throws Exception {
		Genre genre = new Genre();
		this.mockMvc
				.perform(put("/api/genre").content(JsonUtil.mapToJson(genre)).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("Удаление жанра")
	public void deleteGenre() throws Exception {
		this.mockMvc.perform(MockMvcRequestBuilders.delete("/api/genre/{id}", "1").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
