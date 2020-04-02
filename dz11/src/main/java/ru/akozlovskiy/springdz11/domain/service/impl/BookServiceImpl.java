package ru.akozlovskiy.springdz11.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
	public Mono<String> add(String bookName, String authorName, String genre) throws DaoException {

		Mono<Author> monoAuthor = authorRepository.findByName(authorName);
						
		
		Author author = monoAuthor.block();
		/*if (!author.block()) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}*/

		Book book = new Book();
		book.setAuthor(author);
		book.setTitle(bookName);
		book.setGenre(new Genre(genre));

		return bookRepository.save(book).flatMap(savedBook -> Mono.just(savedBook.getId()));
	}

	@Override
	public Flux<Book> getAll() throws DaoException {
		return bookRepository.findAll();
	}

	@Override
	public void update(String id, String title, String genre) {
		Mono<Book> bookOpt = bookRepository.findById(id);
		Optional<Book> monoOpt = bookOpt.blockOptional();

		if (monoOpt.isPresent()) {
			Book book = monoOpt.get();
			book.setTitle(title);
			book.setGenre(new Genre(genre));
			bookRepository.save(book);
		}
	}

	@Override
	public void removeById(String id) {
		bookRepository.deleteById(id);
	}
}