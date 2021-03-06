package ru.akozlovskiy.springdz14.mongo.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz14.mongo.domain.Book;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findByTitle(String title);

	List<Book> findAll();

	List<Book> findAllByAuthorId(String id);

	void removeByTitle(String title);
}