package ru.akozlovskiy.springdz16.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import ru.akozlovskiy.springdz16.domain.Genre;

@RepositoryRestResource(path = "genres")
public interface GenreRepository extends JpaRepository<Genre, Long> {

	@RestResource(path = "description", rel = "description")
	Genre findByDescription(String genreDescription);
}