package ru.akozlovskiy.springdz07.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz07.domain.Comment;
import ru.akozlovskiy.springdz07.exception.DaoException;

public interface CommentService {

	long add(String comment, String bookName) throws DaoException;

	List<Comment> findAllByBookName(String bookName);
}
