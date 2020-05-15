package ru.akozlovskiy.springdz14.mongo.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.akozlovskiy.springdz14.mongo.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, String> {

	 List<Comment> findAllByBookId(String id);

	 void removeByBookId(String id);
}