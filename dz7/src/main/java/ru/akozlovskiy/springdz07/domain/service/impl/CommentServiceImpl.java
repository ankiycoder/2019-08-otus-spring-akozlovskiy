package ru.akozlovskiy.springdz07.domain.service.impl;

import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Comment;
import ru.akozlovskiy.springdz07.domain.dao.BookDAO;
import ru.akozlovskiy.springdz07.domain.dao.CommentDAO;
import ru.akozlovskiy.springdz07.domain.service.CommentService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentDAO commentDAO;

	private BookDAO bookDAO;

	public CommentServiceImpl(CommentDAO commentDAO, BookDAO bookDAO) {
		this.commentDAO = commentDAO;
		this.bookDAO = bookDAO;
	}

	@Override
	public long add(String bookComment, String bookName) throws DaoException {
		Book book;
		try {
			book = bookDAO.findByName(bookName);
		} catch (NoResultException | EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления комментария, книга не найдена");
		}

		Comment comment = new Comment();
		comment.setComment(bookComment);
		comment.setBook(book);
		return commentDAO.add(comment);
	}

	@Override
	public List<Comment> finAllByBookName(String bookName) {
		return commentDAO.findAllByBookName(bookName);
	}
}
