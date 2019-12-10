package ru.akozlovskiy.springdz07.domain.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Comment;
import ru.akozlovskiy.springdz07.domain.repository.BookRepository;
import ru.akozlovskiy.springdz07.domain.repository.CommentRepository;
import ru.akozlovskiy.springdz07.domain.service.CommentService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private BookRepository bookRepository;

	public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
		this.commentRepository = commentRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public long add(String bookComment, String bookName) throws DaoException {

		Book book = bookRepository.findByBookName(bookName);

		if (Objects.isNull(book)) {
			throw new DaoException("Ошибка добавления комментария, книга не найдена: " + bookName);
		}

		Comment comment = new Comment();
		comment.setComment(bookComment);
		comment.setBook(book);
		return commentRepository.save(comment).getId();
	}

	@Override
	public List<Comment> findAllByBookName(String bookName) {
		return commentRepository.findByBookName(bookName);
	}
}