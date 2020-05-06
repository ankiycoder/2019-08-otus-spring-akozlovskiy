package ru.akozlovskiy.springdz14.jpa.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz14.exception.DaoException;
import ru.akozlovskiy.springdz14.jpa.domain.Book;

@Repository
public interface JpaBookRepository extends PagingAndSortingRepository<Book, Long> {

	Optional<Book> findByTitle(String title);

	@EntityGraph(value = "book-entity-graph")
	List<Book> findAllByAuthorName(String authorName) throws DaoException;

	@EntityGraph(value = "book-entity-graph")
	List<Book> findAll();
}
