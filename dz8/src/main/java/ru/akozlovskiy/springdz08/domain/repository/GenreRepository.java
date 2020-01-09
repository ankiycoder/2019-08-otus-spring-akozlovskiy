package ru.akozlovskiy.springdz08.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz08.domain.Genre;

@Repository
public interface GenreRepository extends MongoRepository<Genre, Long> {
	Genre findByDescription(String genreDescription);
}