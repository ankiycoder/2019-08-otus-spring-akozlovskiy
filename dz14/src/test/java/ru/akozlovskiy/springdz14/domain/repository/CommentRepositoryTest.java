package ru.akozlovskiy.springdz14.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.Comment;
import ru.akozlovskiy.springdz14.mongo.domain.repository.BookRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.CommentRepository;

@DisplayName("Репозиторий по работе с комментариями")
public class CommentRepositoryTest extends AbstractRepositoryTest {

	private static final String BOOK_NAME_IN_BD = "Усатый полосатый";

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Test
	@DisplayName("Успешность добавления и поиска и удаления")
	public void testSaveAndFindAllByBookId() throws DaoException {
		Optional<Book> book = bookRepository.findByTitle(BOOK_NAME_IN_BD);

		Comment comment = new Comment();
		comment.setText("TestComment");
		comment.setBook(book.get());
		Comment savedComment = commentRepository.save(comment);

		Optional<Comment> findComment = commentRepository.findById(comment.getId());

		assertThat(findComment.get()).usingRecursiveComparison().isEqualTo(savedComment);

		List<Comment> commentList = commentRepository.findAllByBookId(book.get().getId());

		assertThat(commentList.get(0)).usingRecursiveComparison().isEqualTo(savedComment);
		
		commentRepository.removeByBookId(book.get().getId());
		
		List<Comment> commentListAfterDelete = commentRepository.findAllByBookId(book.get().getId());
		
		assertThat(commentListAfterDelete).isEmpty();
	}
}