package ru.akozlovskiy.springdz18.domain.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz18.domain.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public Author updateAuthor(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	@Override
	@HystrixCommand(fallbackMethod = "findAllFallback", ignoreExceptions = Throwable.class)
	public List<Author> findAll() {
		return authorRepository.findAll();
	}

	public List<Author> findAllFallback() {
		Author author = new Author();
		author.setName("N/A");
		return Collections.singletonList(author);
	}

	@Override
	public void deleteById(long id) {
		authorRepository.deleteById(id);
	}

}
