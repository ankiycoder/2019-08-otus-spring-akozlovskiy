package ru.akozlovskiy.springdz11.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, String> {

	Mono<Author> findByName(String name);
}
