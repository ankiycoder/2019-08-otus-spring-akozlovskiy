package ru.akozlovskiy.springdz06.domain.dao.jpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Comment;
import ru.akozlovskiy.springdz07.domain.dao.jpa.AuthorDAOJpa;
import ru.akozlovskiy.springdz07.domain.dao.jpa.BookDAOJpa;
import ru.akozlovskiy.springdz07.domain.dao.jpa.CommentDAOJpa;
import ru.akozlovskiy.springdz07.domain.dao.jpa.GenreDAOJpa;
import ru.akozlovskiy.springdz07.exception.DaoException;

@DataJpaTest
@Import({ BookDAOJpa.class, CommentDAOJpa.class, AuthorDAOJpa.class, GenreDAOJpa.class })
@DisplayName("DAO по работе с комментариями")
public class CommentDAOJpaTest {

	@Autowired
	private BookDAOJpa bookDAO;

	@Autowired
	private CommentDAOJpa commentDAOJpa;

	@Autowired
	private TestEntityManager em;

	@Test
	@DisplayName("Успешность добавления")
	public void testAdd() throws DaoException {
		Book book = bookDAO.getById(1);
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
		Book book = bookDAO.getById(1);
		Comment comment = new Comment();
		comment.setComment("TestComment");
		comment.setBook(book);
		em.persist(comment);

		Comment comment2 = new Comment();
		comment2.setComment("TestComment2");
		comment2.setBook(book);
		em.persist(comment2);

		List<Comment> commentList = commentDAOJpa.findAllByBookName(book.getBookName());
		assertEquals(2, commentList.size());
		assertThat(commentList).containsOnly(comment, comment2);
	}
}