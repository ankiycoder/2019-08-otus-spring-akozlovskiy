package ru.akozlovskiy.springdz17.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.akozlovskiy.springdz17.domain.Comment;

@RepositoryRestResource(path = "comments")
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	@RestResource(path = "title", rel = "title")	
	public List<Comment> findByBookTitle(String title);

}
