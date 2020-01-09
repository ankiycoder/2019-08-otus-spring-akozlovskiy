package ru.akozlovskiy.springdz08.domain.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DisplayName("Репозиторий по работе с книгами")
public class BookRepositoryTest extends AbstractRepositoryTest {

	private static final String BOOK_NAME_BD = "BOOK_NAME1";
	private static final String AUTHOR_NAME_IN_BD = "AUTHOR_NAME";
	private static final String GENRE_IN_BD = "GENRE_TEST";

	@Autowired
	private BookRepository bookRepository;

	// @Autowired
	// private TestEntityManager em;

	@Test
	@DisplayName("Поиск по ID")
	public void testGetById() throws DaoException {
		Book book = new Book();
		book.setTitle("testTitlTest");
		bookRepository.save(book);
		
		Book book2 = new Book();
		book2.setTitle("testTitlTest2");
		bookRepository.save(book2);
		
		List<Book> bookList = bookRepository.findAll();
		System.out.println("*************** result = " + bookList.size());
		System.out.println("*************** title = " + bookList.get(0).getTitle());
	}

}