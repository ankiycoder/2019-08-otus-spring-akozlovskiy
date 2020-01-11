package ru.akozlovskiy.springdz08.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz08.domain.Author;

@Repository
<<<<<<< HEAD
public interface AuthorRepository extends MongoRepository<Author, String> {
=======
public interface AuthorRepository extends MongoRepository<Author, Long> {
>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b

	 Optional<Author> findByName(String name);
}
