package ru.akozlovskiy.springdz14.mongo.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz14.mongo.domain.Author;

@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

	Optional<Author> findByName(String name);
}
