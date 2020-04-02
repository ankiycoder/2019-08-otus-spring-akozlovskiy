package ru.akozlovskiy.springdz11.domain.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Book;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, String> {

	Mono<Book> findByTitle(String title);

	Flux<Book> findAll();

	Flux<Book> findAllByAuthorId(String id);

	void removeByTitle(String title);

}