package ru.akozlovskiy.springdz07.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz07.domain.repository.BookRepository;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz07.domain.service.BookService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Service
public class BookServiceImpl implements BookService {

	private final AuthorRepository authorRepository;

	private final GenreRepository genreRepository;

	private final BookRepository bookRepository;

	public BookServiceImpl(AuthorRepository authorRepository, BookRepository bookRepository,
			GenreRepository genreRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.genreRepository = genreRepository;
	}

	@Override
	public long add(String bookName, String authorName, String genreDescription) throws DaoException {

		Genre genre = genreRepository.findByDescription(genreDescription);

		if (Objects.isNull(genre)) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + genreDescription);
		}

		Optional<Author> author = authorRepository.findByName(authorName);
		if (!author.isPresent()) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}

		Book book = new Book();
		book.setAuthor(author.get());
		book.setTitle(bookName);
		book.setGenre(genre);

		return bookRepository.save(book).getId();
	}

	@Override
	public List<Book> getAll() throws DaoException {
		return bookRepository.findAll();
	}

	@Override
	public List<Book> findAllByAuthor(String author) throws DaoException {
		return bookRepository.findAllByAuthorName(author);
	}

	@Override
	public Optional<Book> findByName(String bookname) {
		return bookRepository.findByTitle(bookname);
	}

}
