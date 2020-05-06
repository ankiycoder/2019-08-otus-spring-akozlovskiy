package ru.akozlovskiy.springdz14.jpa.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.jpa.domain.Book;
import ru.akozlovskiy.springdz14.jpa.domain.Comment;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaBookRepository;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaCommentRepository;
import ru.akozlovskiy.springdz14.jpa.domain.service.CommentService;

@Service
public class JpaCommentServiceImpl implements CommentService {

	private JpaCommentRepository commentRepository;

	private JpaBookRepository bookRepository;

	public JpaCommentServiceImpl(JpaCommentRepository commentRepository, JpaBookRepository bookRepository) {
		this.commentRepository = commentRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public long add(String bookComment, String title) throws DaoException {

		Optional<Book> book = bookRepository.findByTitle(title);

		if (!book.isPresent()) {
			throw new DaoException("Ошибка добавления комментария, книга не найдена: " + title);
		}

		Comment comment = new Comment();
		comment.setComment(bookComment);
		comment.setBook(book.get());
		return commentRepository.save(comment).getId();
	}

	@Override
	public List<Comment> findAllByBookName(String bookName) {
		return commentRepository.findByBookTitle(bookName);
	}
}