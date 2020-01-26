package ru.akozlovskiy.springdz08.domain.events;

import java.util.Optional;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.val;
import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.repository.BookRepository;
import ru.akozlovskiy.springdz08.domain.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

	private final CommentRepository commentRepository;

	private final BookRepository bookRepository;

	@Override
	public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
		super.onBeforeDelete(event);
		Document source = event.getSource();
		val title = source.get("title").toString();
		Optional<Book> book = bookRepository.findByTitle(title);
		if (book.isPresent()) {
			commentRepository.removeByBookId(book.get().getId());
		}
	}
}
