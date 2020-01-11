package ru.akozlovskiy.springdz08.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz08.domain.Comment;
import ru.akozlovskiy.springdz08.domain.service.impl.CommentServiceImpl;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DataJpaTest
@Import({ CommentServiceImpl.class })
@DisplayName("Сервис по работе с комментариями")
public class CommentServiceImplTest {

	private static final String BOOK_NAME_BD = "BOOK_NAME1";

	@Autowired
	private CommentServiceImpl сommentServiceImpl;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Возврат ошибки при добавлении для не найденной книги")
	public void tesAddWithWrongBookName() throws DaoException {
		assertThrows(DaoException.class, () -> {
			сommentServiceImpl.add("bookComment", "WrongBookName");
		});
	}

	@Test
	@DisplayName("Добавление")
	public void tesAdd() throws DaoException {

		String bookComment = "bookComment";
		long commentId = сommentServiceImpl.add(bookComment, BOOK_NAME_BD);

		Comment commentBook = em.find(Comment.class, commentId);

		assertEquals(commentBook.getComment(), bookComment);
		assertEquals(commentBook.getBook().getTitle(), BOOK_NAME_BD);
	}
}
