package ru.akozlovskiy.springdz08.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.akozlovskiy.springdz08.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	public List<Comment> findAllByBookId(String id);

}