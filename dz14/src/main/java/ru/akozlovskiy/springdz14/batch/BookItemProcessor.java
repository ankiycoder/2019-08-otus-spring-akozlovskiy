package ru.akozlovskiy.springdz14.batch;

import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz14.jpa.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.Genre;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;

@Service
public class BookItemProcessor implements ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> {

	private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);

	private final AuthorRepository authorRepository;

	public BookItemProcessor(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public ru.akozlovskiy.springdz14.mongo.domain.Book process(Book bookItem) throws Exception {

		log.debug("Process book: {}", bookItem);

		ru.akozlovskiy.springdz14.mongo.domain.Book book = new ru.akozlovskiy.springdz14.mongo.domain.Book();
		book.setTitle(bookItem.getTitle());
		book.setGenre(new Genre(bookItem.getGenre().getDescription()));

		ru.akozlovskiy.springdz14.jpa.domain.Author bookItemAuthor = bookItem.getAuthor();

		if (Objects.nonNull(bookItemAuthor)) {

			Optional<Author> authorOpt = authorRepository.findByName(bookItemAuthor.getName());
			if (authorOpt.isPresent()) {
				book.setAuthor(authorOpt.get());
			} else {
				Author newAuthor = new Author(bookItemAuthor.getName());
				newAuthor.setBirthDate(bookItemAuthor.getBirthDate());
				book.setAuthor(newAuthor);
				authorRepository.save(newAuthor);
			}
		}

		log.debug("Create  book for mongo: {}", book);

		return book;
	}
}
