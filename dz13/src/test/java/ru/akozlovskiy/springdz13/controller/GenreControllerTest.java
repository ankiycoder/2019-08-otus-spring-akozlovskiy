package ru.akozlovskiy.springdz13.controller;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import ru.akozlovskiy.springdz13.controller.GenreController;
import ru.akozlovskiy.springdz13.domain.Genre;
import ru.akozlovskiy.springdz13.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz13.domain.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = GenreController.class)
@DisplayName("Контроллер по работе с жанрами")
public class GenreControllerTest {

	@MockBean
	private GenreRepository genreRepository;
	
	@MockBean
	private UserRepository userRepository;

	@Autowired
	private MockMvc mvc;

	private Genre testGenre;

	@BeforeEach
	public void beforeEach() {
		testGenre = new Genre();
		testGenre.setDescription("description");
		testGenre.setId(1l);
	}

	@Test
	@DisplayName("Добавление жанра")
    @WithMockUser("admin")
	void testAddAuthor() throws Exception {

		when(genreRepository.save(testGenre)).thenReturn(testGenre);

		RequestBuilder request = MockMvcRequestBuilders.post("/addGenre")
				.param("description", testGenre.getDescription()).param("id", String.valueOf(testGenre.getId()));

		mvc.perform(request).andExpect(status().is3xxRedirection());

		ArgumentCaptor<Genre> argumentCaptor = ArgumentCaptor.forClass(Genre.class);
		verify(genreRepository, times(1)).save(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(testGenre);
	}

	@Test
	@DisplayName("Изменение жанра")
	@WithMockUser("admin")
	void testShowUpdateGenrePage() throws Exception {

		Long id = 1l;

		Optional<Genre> genreOpt = Optional.of(testGenre);

		when(genreRepository.findById(id)).thenReturn(genreOpt);

		RequestBuilder request = MockMvcRequestBuilders.get("/updateGenre/{id}", id);

		mvc.perform(request).andExpect(status().isOk()).andExpect(view().name("updateGenre"))
				.andExpect(model().attribute("genre", hasProperty("id", equalTo(testGenre.getId()))))
				.andExpect(model().attribute("genre", hasProperty("description", equalTo(testGenre.getDescription()))));

		verify(genreRepository, times(1)).findById(id);
	}

	@Test
	@DisplayName("Изменение жанра")
	@WithMockUser("admin")
	void testUpdateGenre() throws Exception {

		when(genreRepository.save(testGenre)).thenReturn(testGenre);

		RequestBuilder request = MockMvcRequestBuilders.post("/addGenre")
				.param("description", testGenre.getDescription()).param("id", String.valueOf(testGenre.getId()));

		mvc.perform(request).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));

		ArgumentCaptor<Genre> argumentCaptor = ArgumentCaptor.forClass(Genre.class);
		verify(genreRepository, times(1)).save(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(testGenre);
	}
}