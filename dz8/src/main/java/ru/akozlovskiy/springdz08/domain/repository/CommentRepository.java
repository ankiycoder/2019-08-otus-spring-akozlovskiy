package ru.akozlovskiy.springdz08.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ru.akozlovskiy.springdz08.domain.Comment;

public interface CommentRepository extends MongoRepository<Comment, Long> {

	//@Query("from Comment c join c.book b where b.bookName = :bookName")
	//public List<Comment> findByBookName(@Param("bookName") String bookName);
	
	public List<Comment> findByBookTitle(String title);

}
