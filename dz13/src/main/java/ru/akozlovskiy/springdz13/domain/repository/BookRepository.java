package ru.akozlovskiy.springdz13.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz13.domain.Book;
import ru.akozlovskiy.springdz13.exception.DaoException;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	Optional<Book> findByTitle(String title);

	@EntityGraph(value = "book-entity-graph")
	List<Book> findAllByAuthorName(String authorName) throws DaoException;

	@EntityGraph(value = "book-entity-graph")
	List<Book> findAll();
}
