package ru.akozlovskiy.springdz18.domain.service;

import java.util.List;

import ru.akozlovskiy.springdz18.domain.Author;

public interface AuthorService {

	public Author updateAuthor(Author author);

	public Author save(Author author);

	public List<Author> findAll();

	public void deleteById(long id);
}
