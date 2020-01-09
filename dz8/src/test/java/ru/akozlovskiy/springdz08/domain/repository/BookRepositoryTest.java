package ru.akozlovskiy.springdz08.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.Genre;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DisplayName("Репозиторий по работе с книгами")
public class BookRepositoryTest extends AbstractRepositoryTest {

	private static final String AUTHOR_NAME_FOR_TEST = "AuthorNameForTest";

	private static final String GENRE_FOR_TEST = "GenreForTest";

	private static final String TITLE_FOR_TEST = "TitleForTest";

	@Autowired
	private BookRepository bookRepository;

	@Test
	@DisplayName("Добавление книги")
	public void testAdd() throws DaoException {

		Book book = new Book();
		book.setTitle(TITLE_FOR_TEST);

		Genre genre = new Genre();
		genre.setDescription(GENRE_FOR_TEST);
		book.setGenre(genre);

		Author author = new Author();
		author.setName(AUTHOR_NAME_FOR_TEST);
		book.setAuthor(author);

		Book savedBook = bookRepository.save(book);
		Optional<Book> findedBookOpt = bookRepository.findById(savedBook.getId());

		Book findBook = findedBookOpt.get();

		assertEquals(TITLE_FOR_TEST, findBook.getTitle());
		assertEquals(AUTHOR_NAME_FOR_TEST, findBook.getAuthor().getName());
		assertEquals(GENRE_FOR_TEST, findBook.getGenre().getDescription());
	}
}