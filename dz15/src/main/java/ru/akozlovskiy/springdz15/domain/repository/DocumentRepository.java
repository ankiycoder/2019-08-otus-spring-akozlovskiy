package ru.akozlovskiy.springdz15.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.akozlovskiy.springdz15.domain.Document;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
