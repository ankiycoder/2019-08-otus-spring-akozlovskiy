package ru.akozlovskiy.springdz08.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
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

	private static final String BOOK_NAME_IN_BD = "Усатый полосатый";
	private static final String AUTHOR_NAME_IN_BD = "Маршак";
	private static final String GENRE_IN_BD = "Детский";

	@Autowired
	private BookRepository bookRepository;

	@Test
	@DisplayName("Добавление книги")
	public void testSave() throws DaoException {

		String authorNameForTest = "AuthorNameForTest";
		String genreForTest = "GenreForTest";
		String titleForTest = "TitleForTest";

		Book book = new Book();
		book.setTitle(titleForTest);

		Genre genre = new Genre(genreForTest);
		genre.setDescription(genreForTest);
		book.setGenre(genre);

		Author author = new Author(authorNameForTest);
		book.setAuthor(author);

		Book savedBook = bookRepository.save(book);
		Optional<Book> findedBookOpt = bookRepository.findById(savedBook.getId());

		Book findBook = findedBookOpt.get();

		assertEquals(titleForTest, findBook.getTitle());
		assertEquals(authorNameForTest, findBook.getAuthor().getName());
		assertEquals(genreForTest, findBook.getGenre().getDescription());
		
		assertThat(savedBook).usingRecursiveComparison()
        .isEqualTo(findBook);
	}

	@Test
	@DisplayName("Поиск по описанию")
	public void testFindByTitle() throws DaoException {

		Optional<Book> bookOp = bookRepository.findByTitle(BOOK_NAME_IN_BD);
		Book book = bookOp.get();
		assertEquals(BOOK_NAME_IN_BD, book.getTitle());
		assertEquals(AUTHOR_NAME_IN_BD, book.getAuthor().getName());
		assertEquals(GENRE_IN_BD, book.getGenre().getDescription());
	}

	@Test
	@DisplayName("Поиск по автору")
	public void testFindByAuthorID() throws DaoException {

		Author author = new Author(AUTHOR_NAME_IN_BD);

		Genre genre = new Genre(GENRE_IN_BD);

		Book book1 = new Book();
		book1.setTitle("Title1");
		book1.setAuthor(author);
		book1.setGenre(genre);

		Book savedBook = bookRepository.save(book1);

		Book book2 = new Book();
		book2.setTitle("Title2");
		book2.setAuthor(author);
		book2.setGenre(genre);

		bookRepository.save(book2);

		List<Book> bookList = bookRepository.findAllByAuthorId(savedBook.getAuthor().getId());
		assertThat(bookList).hasSize(2);
	}
}