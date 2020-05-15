package ru.akozlovskiy.springdz14.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;

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
		Author author = new Author("TestName");
		author.setBirthDate(LocalDate.parse(AUTHOR_BIRTH_DATE, dateFormatter));

		Author authorSaved = authorRepository.save(author);
		Optional<Author> findedAuthorOpt = authorRepository.findById(authorSaved.getId());
		assertThat(author).isEqualToComparingFieldByField(findedAuthorOpt.get());
	}

	@Test
	@DisplayName("Поиск по имени")
	public void testFindByName() throws DaoException {
		Author author = new Author(AUTHOR_NAME_FOR_TEST);
		Author savedAuthor = authorRepository.save(author);
		Optional<Author> findedAuthorOpt = authorRepository.findByName(AUTHOR_NAME_FOR_TEST);
		assertThat(savedAuthor).isEqualToComparingFieldByField(findedAuthorOpt.get());
	}
}