package ru.akozlovskiy.springdz09.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz09.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

	 Optional<Author> findByName(String name);
}
