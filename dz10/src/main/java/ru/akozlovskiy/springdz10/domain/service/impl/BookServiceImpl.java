package ru.akozlovskiy.springdz10.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz10.domain.Author;
import ru.akozlovskiy.springdz10.domain.Book;
import ru.akozlovskiy.springdz10.domain.Genre;
import ru.akozlovskiy.springdz10.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz10.domain.repository.BookRepository;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz10.domain.service.BookService;
import ru.akozlovskiy.springdz10.exception.DaoException;

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
	public void update(long bookId, String bookName, long authorId, long genreId) throws DaoException {

		Book book = bookRepository.getOne(bookId);

		Optional<Genre> genre = genreRepository.findById(genreId);

		if (!genre.isPresent()) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + genreId);
		}

		Optional<Author> author = authorRepository.findById(authorId);
		if (!author.isPresent()) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorId);
		}
		book.setAuthor(author.get());
		book.setTitle(bookName);
		book.setGenre(genre.get());

		bookRepository.save(book);
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

	@Override
	public Optional<Book> findById(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public void save(Book book) {
		bookRepository.save(book);
	}

	@Override
	public void delete(long bookId) throws DaoException {
		bookRepository.deleteById(bookId);		
	}
}