package ru.akozlovskiy.springdz11.domain.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.exception.DaoException;

public interface BookService {

	Mono<String> add(String bookName, String authorName, String genre) throws DaoException;

	Flux<Book> getAll() throws DaoException;

	void removeById(String id);

	void update(String id, String title, String genre);
}