package ru.akozlovskiy.springdz08.domain.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz08.domain.Comment;
import ru.akozlovskiy.springdz08.domain.repository.AbstractRepositoryTest;
import ru.akozlovskiy.springdz08.domain.service.BookService;
import ru.akozlovskiy.springdz08.domain.service.CommentService;
import ru.akozlovskiy.springdz08.exception.DaoException;

@DisplayName("Сервис по работе с комментариями")
public class CommentServiceImplTest extends AbstractRepositoryTest {

	private static final String TEST_BOOK_NAME = "bookName";
	private static final String BOOK_COMMENT = "Тествый комментарий";

	@Autowired
	private CommentService сommentService;

	@Autowired
	private BookService bookService;

	@Test
	@DisplayName("Возврат ошибки при добавлении для не найденной книги")
	public void tesAddWithWrongBookName() throws DaoException {
		assertThrows(DaoException.class, () -> {
			сommentService.add("bookComment", "WrongBookName");
		});
	}

	@Test
	@DisplayName("Добавление")
	public void tesAdd() throws DaoException {
		bookService.add(TEST_BOOK_NAME, "authorName", BOOK_COMMENT);
		сommentService.add(BOOK_COMMENT, TEST_BOOK_NAME);
		List<Comment> commentList = сommentService.findAllByBookName(TEST_BOOK_NAME);

		assertEquals(BOOK_COMMENT, commentList.get(0).getText());
	}
}
