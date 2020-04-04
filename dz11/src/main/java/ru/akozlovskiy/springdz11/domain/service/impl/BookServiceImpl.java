package ru.akozlovskiy.springdz11.domain.service.impl;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.domain.Genre;
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz11.domain.repository.BookRepository;
import ru.akozlovskiy.springdz11.domain.service.BookService;
import ru.akozlovskiy.springdz11.exception.DaoException;

@Service
public class BookServiceImpl implements BookService {

	private final AuthorRepository authorRepository;

	private final BookRepository bookRepository;

	public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public Mono<Book> add(String bookName, String authorName, String genre) {

		return authorRepository.findByName(authorName).flatMap(author -> {
			Book book = new Book();
			book.setAuthor(author);
			book.setTitle(bookName);
			book.setGenre(new Genre(genre));
			return bookRepository.save(book);
		});
	}

	@Override
	public Flux<Book> getAll()  {
		return bookRepository.findAll();
	}

	@Override
	public Mono<Book> update(String id, String title, String genre, String authorId) {

		Mono<Author> authorMono = authorRepository.findById(authorId);
		return authorMono.flatMap(author -> {
			Book book = new Book();
			book.setId(id);
			book.setAuthor(author);
			book.setTitle(title);
			book.setGenre(new Genre(genre));
			return Mono.just(book);
		}).flatMap(book -> bookRepository.save(book));
	}

	@Override
	public Mono<Void> removeById(String id) {
		return bookRepository.deleteById(id);
	}
}