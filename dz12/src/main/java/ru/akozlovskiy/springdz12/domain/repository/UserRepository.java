package ru.akozlovskiy.springdz12.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.akozlovskiy.springdz12.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String name);
}
