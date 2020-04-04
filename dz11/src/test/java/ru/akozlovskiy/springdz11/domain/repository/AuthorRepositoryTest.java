package ru.akozlovskiy.springdz11.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.exception.DaoException;

@DisplayName("Репозиторий по работе с авторами")
public class AuthorRepositoryTest extends AbstractRepositoryTest {

	private static final String AUTHOR_BIRTH_DATE = "1891-05-15";

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final String AUTHOR_NAME_FOR_TEST = "AUTHOR_NAME";

	@Autowired
	private AuthorRepository authorRepository;

	@Test
	@DisplayName("Добавление автора")
	public void testAdd() throws DaoException {
		Mono<Author> authorMono = authorRepository.save(new Author(AUTHOR_NAME_FOR_TEST, AUTHOR_BIRTH_DATE));
		StepVerifier.create(authorMono)
		.assertNext(author -> assertNotNull(author.getId())).verifyComplete();
	}
	
	@Test
	@DisplayName("Поиск по имени")
	public void testFindByName() throws DaoException {
		// Поиск по имени
		Mono<Author> findAuthorMono = authorRepository.findByName(AUTHOR_NAME_FOR_TEST);

		StepVerifier.create(findAuthorMono).assertNext(author -> {
			assertEquals(AUTHOR_NAME_FOR_TEST, author.getName());
			assertEquals(AUTHOR_BIRTH_DATE, dateFormatter.format(author.getBirthDate()));
			assertNotNull(author.getId());
		}).expectComplete().verify();
				
		//Mono<Author> authorFindByNameMono = authorRepository.findByName(AUTHOR_NAME_FOR_TEST);

		//authorFlux.subscribe(value -> System.out.println("Value: " + value));
	}

	@Test
	@DisplayName("Поиск всех авторов")
	public void testFindAll() throws DaoException {
		Flux<Author> authorFlux = authorRepository.findAll();

		StepVerifier.create(authorFlux).recordWith(ArrayList::new).thenConsumeWhile(x -> true)
				.expectRecordedMatches(elements -> elements.size() == 3).verifyComplete();
	}	
}