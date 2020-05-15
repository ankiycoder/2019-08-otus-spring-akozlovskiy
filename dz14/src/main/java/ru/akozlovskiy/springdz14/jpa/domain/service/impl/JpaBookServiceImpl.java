package ru.akozlovskiy.springdz14.jpa.domain.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.jpa.domain.Author;
import ru.akozlovskiy.springdz14.jpa.domain.Book;
import ru.akozlovskiy.springdz14.jpa.domain.Genre;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaAuthorRepository;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaBookRepository;
import ru.akozlovskiy.springdz14.jpa.domain.repository.JpaGenreRepository;
import ru.akozlovskiy.springdz14.jpa.domain.service.BookService;

@Service
public class JpaBookServiceImpl implements BookService {

	private final JpaAuthorRepository authorRepository;

	private final JpaGenreRepository genreRepository;

	private final JpaBookRepository bookRepository;

	public JpaBookServiceImpl(JpaAuthorRepository authorRepository, JpaBookRepository bookRepository,
			JpaGenreRepository genreRepository) {
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
