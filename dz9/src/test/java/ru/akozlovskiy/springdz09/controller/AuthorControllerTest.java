package ru.akozlovskiy.springdz09.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.akozlovskiy.springdz09.domain.Author;
import ru.akozlovskiy.springdz09.domain.repository.AuthorRepository;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = AuthorController.class)
public class AuthorControllerTest {

	@MockBean
	private AuthorRepository authorRepository;

	@Autowired
	private MockMvc mvc;

	private Author testAuthor;

	@Autowired
	private ObjectMapper objectMapper;
	
	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	@BeforeEach
	public void beforeEach() {
		testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(1l);
		testAuthor.setBirthDate(LocalDate.now());
	}

	@Test
	void testAddAuthor() throws Exception {

		Author testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(1l);
		testAuthor.setBirthDate(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		
		when(authorRepository.save(any())).thenReturn(testAuthor);
		
		String writeValueAsString = objectMapper.writeValueAsString(testAuthor);
		
		RequestBuilder request = MockMvcRequestBuilders
		        .post("/addAuthor").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", testAuthor.getName())
                .param("birthDate", "2000-10-10");
	
		mvc.perform(request); //.andExpect(status().is3xxRedirection());

		verify(authorRepository, times(1)).save(testAuthor);
	}
	

	//@Test
	void testAddAuthor2() throws Exception {

		Author testAuthor = new Author();
		testAuthor.setName("test");
		testAuthor.setId(333l);
		testAuthor.setBirthDate(LocalDate.now());

		Optional<Author> au = Optional.of(testAuthor);
		
		when(authorRepository.findById(333l)).thenReturn(au);

		RequestBuilder request = MockMvcRequestBuilders
		        .get("/updateAuthor/{id}", 333);
	
		mvc.perform(request).andExpect(status().is3xxRedirection());

		verify(authorRepository, times(1)).save(testAuthor);
	}

}