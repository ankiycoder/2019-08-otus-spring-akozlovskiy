package ru.akozlovskiy.springdz14.jpa.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz14.jpa.domain.Genre;

@Repository
public interface JpaGenreRepository extends JpaRepository<Genre, Long> {
	Genre findByDescription(String genreDescription);
}