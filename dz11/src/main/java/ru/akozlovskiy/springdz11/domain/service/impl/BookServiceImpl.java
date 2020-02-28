package ru.akozlovskiy.springdz11.domain.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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

	@Override
	public void update(String id, String title, String genre) {
		Optional<Book> bookOpt = bookRepository.findById(id);
		if (bookOpt.isPresent()) {
			Book book = bookOpt.get();
			book.setTitle(title);
			book.setGenre(new Genre(genre));
			bookRepository.save(book);
		}
	}
}