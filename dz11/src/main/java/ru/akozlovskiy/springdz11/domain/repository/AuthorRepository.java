package ru.akozlovskiy.springdz11.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz11.domain.Author;


@Repository
public interface AuthorRepository extends MongoRepository<Author, String> {

	Optional<Author> findByName(String name);
}
