package ru.akozlovskiy.springdz08.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.exception.DaoException;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

	Optional<Book> findByTitle(String title);

	List<Book> findAllByAuthorName(String authorName) throws DaoException;

	List<Book> findAll();
}
