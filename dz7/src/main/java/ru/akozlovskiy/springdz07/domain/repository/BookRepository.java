package ru.akozlovskiy.springdz07.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.exception.DaoException;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, BookRepositoryCustom {

	Book findByBookName(String bookName);

	@Query("select b from Book b join fetch b.author ath join fetch b.genre g where ath.name = :authorName")
	List<Book> findAllByAuthor(String authorName) throws DaoException;

}
