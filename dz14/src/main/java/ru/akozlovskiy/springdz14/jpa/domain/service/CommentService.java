package ru.akozlovskiy.springdz14.jpa.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.jpa.domain.Comment;

public interface CommentService {

	long add(String comment, String bookName) throws DaoException;

	List<Comment> findAllByBookName(String bookName);
}
