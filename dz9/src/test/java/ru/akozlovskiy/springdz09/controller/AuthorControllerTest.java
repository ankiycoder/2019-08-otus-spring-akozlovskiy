package ru.akozlovskiy.springdz09.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.akozlovskiy.springdz09.domain.Author;
import ru.akozlovskiy.springdz09.domain.repository.AuthorRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AuthorController.class)
public class AuthorControllerTest {

	@MockBean
	private AuthorRepository authorRepository;

	@Autowired
	private MockMvc mvc;

	private Author testAuthor;

	@Autowired
	private ObjectMapper objectMapper;

	@BeforeEach
	public void beforeEach() {
		testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(1l);
		testAuthor.setBirthDate(LocalDate.now());
	}

	@Test
	void testExample() throws Exception {

		Author testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(1l);
		testAuthor.setBirthDate(LocalDate.now());

		given(authorRepository.save(testAuthor)).willReturn(any());


		mvc.perform(post("/addAuthor").contentType(MediaType.APPLICATION_FORM_URLENCODED).sessionAttr("author", testAuthor)).
		
		
		andExpect(status().is3xxRedirection());

		verify(authorRepository, times(1)).save(testAuthor);
	}

	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}