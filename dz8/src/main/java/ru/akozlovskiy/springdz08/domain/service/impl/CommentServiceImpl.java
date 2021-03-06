package ru.akozlovskiy.springdz08.domain.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.Comment;
import ru.akozlovskiy.springdz08.domain.repository.BookRepository;
import ru.akozlovskiy.springdz08.domain.repository.CommentRepository;
import ru.akozlovskiy.springdz08.domain.service.CommentService;
import ru.akozlovskiy.springdz08.exception.DaoException;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;

	private BookRepository bookRepository;

	public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
		this.commentRepository = commentRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public Comment add(String bookComment, String title) throws DaoException {

		Optional<Book> book = bookRepository.findByTitle(title);

		if (!book.isPresent()) {
			throw new DaoException("Ошибка добавления комментария, книга не найдена: " + title);
		}

		Comment comment = new Comment();
		comment.setText(bookComment);
		comment.setBook(book.get());
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> findAllByBookName(String bookName) {
		Optional<Book> book = bookRepository.findByTitle(bookName);
		if (book.isPresent()) {
			return commentRepository.findAllByBookId(book.get().getId());
		}
		return Collections.emptyList();
	}
}