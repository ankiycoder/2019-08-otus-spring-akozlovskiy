package ru.akozlovskiy.springdz11.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.domain.service.impl.BookServiceImpl;
import ru.akozlovskiy.springdz11.exception.DaoException;

/**
 * В сервисе проверяем только метод add, т.к. остальные методы проверяются в
 * BookDAOJpaTest
 */

@DataJpaTest
@Import({ BookServiceImpl.class })
@DisplayName("Сервис по работе с книгами")
public class BookServiceImplTest {

	private static final String AUTHOR_NAME_IN_BD = "Самуил Яковлевич Маршак";
	private static final String GENRE_IN_BD = "Детский";

	@Autowired
	private BookServiceImpl bookServiceImpl;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Возврат ошибки при добавлении с не заведенным автором")
	public void tesAddDaoWithWrongAuthor() throws DaoException {
		assertThrows(DaoException.class, () -> {
			bookServiceImpl.add("bookname", "WRONG_AUTHOR_NAME", GENRE_IN_BD);
		});
	}

	@Test
	@DisplayName("Возврат ошибки при добавлении с не заведенным жанром")
	public void tesAddDaoWithWrongGenre() throws DaoException {
		assertThrows(DaoException.class, () -> {
			bookServiceImpl.add("bookname", AUTHOR_NAME_IN_BD, "WRONG_GENRE");
		});
	}

	@Test
	@DisplayName("Добавление")
	public void tesAdd() throws DaoException {

		long bookid = bookServiceImpl.add("bookNameTest", AUTHOR_NAME_IN_BD, GENRE_IN_BD);

		Book findBook = em.find(Book.class, bookid);

		assertEquals(findBook.getTitle(), "bookNameTest");
		assertEquals(findBook.getAuthor().getName(), AUTHOR_NAME_IN_BD);
		assertEquals(findBook.getGenre().getDescription(), GENRE_IN_BD);
	}
}