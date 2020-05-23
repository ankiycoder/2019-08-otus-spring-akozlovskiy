package ru.akozlovskiy.springdz17.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.akozlovskiy.springdz17.domain.Book;
import ru.akozlovskiy.springdz17.exception.DaoException;

@RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book, Long> {

	@RestResource(path = "title", rel = "title")	
	Optional<Book> findByTitle(String title);

	@EntityGraph(value = "book-entity-graph")
	@RestResource(path = "name", rel = "name")
	List<Book> findAllByAuthorName(String authorName) throws DaoException;

	@EntityGraph(value = "book-entity-graph")
	@RestResource(path = "all", rel = "all")
	List<Book> findAll();
}
