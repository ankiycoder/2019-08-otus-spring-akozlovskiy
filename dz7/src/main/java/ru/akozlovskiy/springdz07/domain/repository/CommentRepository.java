package ru.akozlovskiy.springdz07.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ru.akozlovskiy.springdz07.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("from Comment c join c.book b where b.bookName = :bookName")
	public List<Comment> findByBookName(@Param("bookName") String bookName);

}
