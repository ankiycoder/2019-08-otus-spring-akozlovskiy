package ru.akozlovskiy.springdz14.jpa.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.akozlovskiy.springdz14.jpa.domain.Comment;

public interface JpaCommentRepository extends JpaRepository<Comment, Long> {

	//@Query("from Comment c join c.book b where b.bookName = :bookName")
	//public List<Comment> findByBookName(@Param("bookName") String bookName);
	
	public List<Comment> findByBookTitle(String title);

}
