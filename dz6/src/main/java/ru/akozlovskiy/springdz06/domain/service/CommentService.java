package ru.akozlovskiy.springdz06.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz06.domain.Comment;
import ru.akozlovskiy.springdz06.exception.DaoException;

public interface CommentService {

	long add(String comment, String bookName) throws DaoException;

	List<Comment> finAllByBookName(String bookName);
}
