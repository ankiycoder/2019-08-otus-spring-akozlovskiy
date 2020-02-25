package ru.akozlovskiy.springdz10.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz10.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
	Genre findByDescription(String genreDescription);
}