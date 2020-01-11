package ru.akozlovskiy.springdz08.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.exception.DaoException;

@Repository
<<<<<<< HEAD
public interface BookRepository extends MongoRepository<Book, String> {
=======
public interface BookRepository extends MongoRepository<Book, Long> {
>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b

	Optional<Book> findByTitle(String title);

	List<Book> findAllByAuthorName(String authorName) throws DaoException;

	List<Book> findAll();
}
