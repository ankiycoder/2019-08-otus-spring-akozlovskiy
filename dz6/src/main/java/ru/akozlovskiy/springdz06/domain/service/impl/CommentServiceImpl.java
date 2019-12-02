package ru.akozlovskiy.springdz06.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.domain.Comment;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.domain.dao.CommentDAO;
import ru.akozlovskiy.springdz06.domain.service.CommentService;
import ru.akozlovskiy.springdz06.exception.DaoException;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO commentDAO;

	@Autowired
	private BookDAO bookDAO;

	@Override
	public long add(String bookComment, String bookName) throws DaoException {
		Book book;
		try {
			 book = bookDAO.findByName(bookName);
		} catch (EmptyResultDataAccessException e) {
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
