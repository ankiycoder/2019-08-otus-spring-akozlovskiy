package ru.akozlovskiy.springdz08.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DisplayName("Репозиторий по работе с авторами")
public class AuthorRepositoryTest extends AbstractRepositoryTest {

	private static final String AUTHOR_BIRTH_DATE = "1891-05-15";

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	private static final String AUTHOR_NAME_FOR_TEST = "AUTHOR_NAME";
	
	@Autowired
	private AuthorRepository authorRepository;

	@Test
	@DisplayName("Добавление книги")
	public void testAdd() throws DaoException {

		Author author = new Author();
		author.setName(AUTHOR_NAME_FOR_TEST);
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));

		Author authorSaved = authorRepository.save(author);
		Optional<Author> findedAuthorOpt = authorRepository.findById(authorSaved.getId());

		assertThat(author).isEqualToComparingFieldByField(findedAuthorOpt.get());
	}
}