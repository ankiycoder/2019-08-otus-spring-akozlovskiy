package ru.akozlovskiy.springdz09.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz09.domain.Comment;
import ru.akozlovskiy.springdz09.exception.DaoException;

public interface CommentService {

	long add(String comment, String bookName) throws DaoException;

	List<Comment> findAllByBookName(String bookName);
}
