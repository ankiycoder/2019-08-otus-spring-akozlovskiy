package ru.akozlovskiy.springdz12.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz12.domain.Book;
import ru.akozlovskiy.springdz12.domain.Comment;
import ru.akozlovskiy.springdz12.domain.repository.BookRepository;
import ru.akozlovskiy.springdz12.domain.repository.CommentRepository;
import ru.akozlovskiy.springdz12.domain.service.CommentService;
import ru.akozlovskiy.springdz12.exception.DaoException;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private BookRepository bookRepository;

	public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
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