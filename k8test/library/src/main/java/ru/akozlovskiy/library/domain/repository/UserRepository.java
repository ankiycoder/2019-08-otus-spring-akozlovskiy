package ru.akozlovskiy.library.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.akozlovskiy.library.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String name);
}
