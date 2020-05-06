package ru.akozlovskiy.springdz14.mongo.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Comment;

public interface CommentService {

	Comment add(String comment, String bookName) throws DaoException;

	List<Comment> findAllByBookName(String bookName);
}
