package ru.akozlovskiy.springdz15.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz15.domain.Document;

@Transactional
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
