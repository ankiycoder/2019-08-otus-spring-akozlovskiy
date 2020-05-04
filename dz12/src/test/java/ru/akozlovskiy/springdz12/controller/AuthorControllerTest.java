package ru.akozlovskiy.springdz12.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import ru.akozlovskiy.springdz12.controller.AuthorController;
import ru.akozlovskiy.springdz12.domain.Author;
import ru.akozlovskiy.springdz12.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz12.domain.repository.UserRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthorController.class)
@DisplayName("Контроллер по работе с авторами")
public class AuthorControllerTest {

	private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@MockBean
	private AuthorRepository authorRepository;

	@Autowired
	private MockMvc mvc;

	private Author testAuthor;
	
	@MockBean
	private UserRepository userRepository;

	@BeforeEach
	public void beforeEach() {
		testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(1l);
		testAuthor.setBirthDate(LocalDate.now());
	}

	@Test
	@DisplayName("Добавление автора")
	@WithMockUser("admin")
	void testAddAuthor() throws Exception {

		when(authorRepository.save(testAuthor)).thenReturn(testAuthor);

		RequestBuilder request = MockMvcRequestBuilders.post("/addAuthor").param("name", testAuthor.getName())
				.param("birthDate", LocalDate.now().format(format)).param("id", String.valueOf(testAuthor.getId()));

		mvc.perform(request).andExpect(status().is3xxRedirection());

		ArgumentCaptor<Author> argumentCaptor = ArgumentCaptor.forClass(Author.class);
		verify(authorRepository, times(1)).save(argumentCaptor.capture());
		assertThat(argumentCaptor.getValue()).isEqualToComparingFieldByField(testAuthor);
	}

	@Test
	@DisplayName("Изменение автора")
	@WithMockUser("admin")
	void testShowUpdateAuthorPage() throws Exception {

		Long id = 1l;

		Optional<Author> authorOpt = Optional.of(testAuthor);

		when(authorRepository.findById(id)).thenReturn(authorOpt);

		RequestBuilder request = MockMvcRequestBuilders.get("/updateAuthor/{id}", id);

		mvc.perform(request).andExpect(status().isOk()).andExpect(view().name("updateAuthor"))
				.andExpect(model().attribute("author", hasProperty("id", equalTo(testAuthor.getId()))))
				.andExpect(model().attribute("author", hasProperty("name", equalTo(testAuthor.getName()))))
				.andExpect(model().attribute("author", hasProperty("birthDate", equalTo(testAuthor.getBirthDate()))));

		verify(authorRepository, times(1)).findById(id);
	}

	@Test
	@DisplayName("Изменение автора")
	@WithMockUser("admin")
	void testUpdateAuthor() throws Exception {

		Long id = 1l;

		when(authorRepository.save(testAuthor)).thenReturn(testAuthor);

		RequestBuilder request = MockMvcRequestBuilders.post("/updateAuthor/{id}", id)
				.param("name", testAuthor.getName()).param("birthDate", LocalDate.now().format(format))
				.param("id", String.valueOf(testAuthor.getId()));

		mvc.perform(request).andExpect(status().is3xxRedirection()).andExpect(view().name("redirect:/index"));
	}
}