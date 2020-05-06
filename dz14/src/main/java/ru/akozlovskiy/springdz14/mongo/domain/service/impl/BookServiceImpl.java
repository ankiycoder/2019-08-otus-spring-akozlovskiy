package ru.akozlovskiy.springdz14.mongo.domain.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.Genre;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.BookRepository;
import ru.akozlovskiy.springdz14.mongo.domain.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final AuthorRepository authorRepository;

	private final BookRepository bookRepository;

	public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	public String add(String bookName, String authorName, String genreDescription) throws DaoException {
		Book book = new Book();
		book.setAuthor(new Author(authorName));
		book.setTitle(bookName);
		book.setGenre(new Genre(genreDescription));

		return bookRepository.save(book).getId();
	}

	@Override
	public List<Book> getAll() throws DaoException {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findAllByAuthor(String author) throws DaoException {
		Optional<Author> findAuthor = authorRepository.findByName(author);
		if (findAuthor.isPresent()) {
			return bookRepository.findAllByAuthorId(findAuthor.get().getId());
		}
		return Collections.emptyList();
	}

	@Override
	public Optional<Book> findByTitle(String bookname) {
		return bookRepository.findByTitle(bookname);
	}

	@Override
	public void removeByTitle(String title) {
		bookRepository.removeByTitle(title);
	}
}