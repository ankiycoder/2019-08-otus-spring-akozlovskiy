package ru.akozlovskiy.springdz11.domain.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Book;

public interface BookService {

	Mono<Book> add(String bookName, String authorName, String genre);

	Flux<Book> getAll();

	Mono<Void> removeById(String id);

	Mono<Book> update(String id, String title, String genre, String authorId);
}