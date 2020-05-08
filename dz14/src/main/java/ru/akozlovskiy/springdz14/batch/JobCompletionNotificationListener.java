package ru.akozlovskiy.springdz14.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

import ru.akozlovskiy.springdz14.mongo.domain.Author;
import ru.akozlovskiy.springdz14.mongo.domain.Book;
import ru.akozlovskiy.springdz14.mongo.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz14.mongo.domain.repository.BookRepository;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private final BookRepository bookRepository;

	private final AuthorRepository authorRepository;

	public JobCompletionNotificationListener(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.debug("---Job Status = {}", jobExecution.getStatus());

		List<Book> findAllBooks = bookRepository.findAll();
		log.debug("Count books in mongo: {}", findAllBooks.size());
		findAllBooks.forEach(book -> log.debug("------- saved book in mongo = {}", book));

		List<Author> findAllAuthors = authorRepository.findAll();
		log.debug("Count authors in mongo: {}", findAllAuthors.size());
		findAllAuthors.forEach(author -> log.debug("------- saved author in mongo= {}", author));
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.debug("---Job Status = {}", jobExecution.getStatus());
	}
}