package ru.akozlovskiy.springdz10.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz10.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	 Optional<Author> findByName(String name);
}
