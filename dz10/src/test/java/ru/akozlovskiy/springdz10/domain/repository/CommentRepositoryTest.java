package ru.akozlovskiy.springdz10.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.akozlovskiy.springdz10.exception.DaoException;
import ru.akozlovskiy.springdz10.domain.Book;
import ru.akozlovskiy.springdz10.domain.Comment;
import ru.akozlovskiy.springdz10.domain.repository.BookRepository;
import ru.akozlovskiy.springdz10.domain.repository.CommentRepository;

@DataJpaTest
@DisplayName("Репозиторий по работе с комментариями")
public class CommentRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Успешность добавления")
	public void testAdd() throws DaoException {
		Book book = bookRepository.getOne(1l);
		Comment comment = new Comment();
		comment.setComment("TestComment");
		comment.setBook(book);
		em.persist(comment);

		Comment getIdComment = em.find(Comment.class, comment.getId());
		assertThat(getIdComment).isEqualToComparingFieldByField(comment);
	}

	@Test
	@DisplayName("Успешность поиска по имени книги")
	public void testGetByBookName() throws DaoException {
		Book book = bookRepository.getOne(1l);
		Comment comment = new Comment();
		comment.setComment("TestComment");
		comment.setBook(book);
		em.persist(comment);

		Comment comment2 = new Comment();
		comment2.setComment("TestComment2");
		comment2.setBook(book);
		em.persist(comment2);

		List<Comment> commentList = commentRepository.findByBookTitle(book.getTitle());
		assertEquals(2, commentList.size());
		assertThat(commentList).containsOnly(comment, comment2);
	}
}