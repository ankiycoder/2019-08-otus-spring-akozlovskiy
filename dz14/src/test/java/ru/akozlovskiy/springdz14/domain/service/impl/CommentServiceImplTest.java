package ru.akozlovskiy.springdz14.domain.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.Comment;
import ru.akozlovskiy.springdz14.mongo.domain.repository.BookRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.CommentRepository;
import ru.akozlovskiy.springdz14.mongo.domain.service.CommentService;
import ru.akozlovskiy.springdz14.mongo.domain.service.impl.CommentServiceImpl;

@DisplayName("Сервис по работе с комментариями")
@ExtendWith(SpringExtension.class)
@Import(CommentServiceImpl.class)
public class CommentServiceImplTest {

	private static final String ID = "ID";
	private static final String TEST_BOOK_NAME = "bookName";
	private static final String BOOK_COMMENT = "Тествый комментарий";

	@Autowired
	private CommentService сommentService;

	@MockBean
	private CommentRepository commentRepository;

	@MockBean
	private BookRepository bookRepository;

	private Comment comment;

	@BeforeEach
	public void initMock() {
		Book bookForTest = new Book();
		bookForTest.setId(ID);
		bookForTest.setTitle(TEST_BOOK_NAME);

		comment = new Comment();
		comment.setBook(bookForTest);
		comment.setText("text");
		when(bookRepository.findByTitle(any())).thenReturn(Optional.of(bookForTest));

		when(commentRepository.save(any(Comment.class))).thenReturn(comment);
		when(commentRepository.findAllByBookId(any())).thenReturn(Collections.singletonList(comment));
	}

	@Test
	@DisplayName("Добавление")
	public void tesAdd() throws DaoException {
		Comment result = сommentService.add(BOOK_COMMENT, TEST_BOOK_NAME);
		assertEquals(comment, result);
	}

	@Test
	@DisplayName("Поиск по имени книги")
	public void testFindAllByBookName() throws DaoException {
		List<Comment> result = сommentService.findAllByBookName(TEST_BOOK_NAME);
		assertThat(result).contains(comment);
	}
}