package ru.akozlovskiy.library.domain.service;

import java.util.List;

import ru.akozlovskiy.library.domain.Comment;
import ru.akozlovskiy.library.exception.DaoException;

public interface CommentService {

	long add(String comment, String bookName) throws DaoException;

	List<Comment> findAllByBookName(String bookName);
}
