package ru.akozlovskiy.springdz14.mongo.domain.events;

import java.util.Objects;
import java.util.Optional;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

	private final AuthorRepository authorRepository;

	@Override
	public void onBeforeConvert(BeforeConvertEvent<Book> event) {
		
		super.onBeforeConvert(event);
		
		val book = event.getSource();
		
		if (Objects.nonNull(book.getAuthor())) {

			Author author = book.getAuthor();
			Optional<Author> authorOpt = authorRepository.findByName(author.getName());

			if (authorOpt.isPresent()) {
				book.setAuthor(authorOpt.get());
			} else {
				authorRepository.save(author);
			}
		}
	}
}
