package ru.akozlovskiy.springdz06.domain.dao;

import java.util.List;

import ru.akozlovskiy.springdz06.domain.Comment;

public interface CommentDAO {

	List<Comment> findAllByBookName(String bookName);

	long add(Comment comment);

}
