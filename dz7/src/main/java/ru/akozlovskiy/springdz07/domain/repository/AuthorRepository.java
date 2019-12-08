package ru.akozlovskiy.springdz07.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz07.domain.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, AuthorRepositoryCustom {

	Author findByName(String name);
}
