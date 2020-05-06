package ru.akozlovskiy.springdz14.jpa.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz14.jpa.domain.Author;

@Repository
public interface JpaAuthorRepository extends JpaRepository<Author, Long> {

	 Optional<Author> findByName(String name);
}
