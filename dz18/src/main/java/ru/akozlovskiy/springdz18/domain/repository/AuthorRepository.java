package ru.akozlovskiy.springdz18.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.akozlovskiy.springdz18.domain.Author;

@RepositoryRestResource(path = "authors")
@HystrixCommand(fallbackMethod = "callUserService_Fallback")
public interface AuthorRepository extends JpaRepository<Author, Long> {

	@RestResource(path = "name", rel = "name")
	 Optional<Author> findByName(String name);
}
