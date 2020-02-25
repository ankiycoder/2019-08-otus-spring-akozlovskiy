package ru.akozlovskiy.springdz11.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz11.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, Long> {

	 Optional<Author> findByName(String name);
}
