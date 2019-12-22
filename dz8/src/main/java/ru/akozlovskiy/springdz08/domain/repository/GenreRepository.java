package ru.akozlovskiy.springdz08.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz08.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	Genre findByDescription(String genreDescription);
}