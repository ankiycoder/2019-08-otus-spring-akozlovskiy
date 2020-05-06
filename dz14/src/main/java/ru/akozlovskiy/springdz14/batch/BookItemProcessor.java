package ru.akozlovskiy.springdz14.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import ru.akozlovskiy.springdz14.jpa.domain.Book;

public class BookItemProcessor implements ItemProcessor<Book, ru.akozlovskiy.springdz14.mongo.domain.Book> {

	private static final Logger log = LoggerFactory.getLogger(BookItemProcessor.class);

	@Override
	public ru.akozlovskiy.springdz14.mongo.domain.Book process(Book item) throws Exception {
		log.debug("Process book: {}", item);
		return new ru.akozlovskiy.springdz14.mongo.domain.Book();
	}
}