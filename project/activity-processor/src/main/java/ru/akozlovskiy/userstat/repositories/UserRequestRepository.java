package ru.akozlovskiy.userstat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.userstat.domain.UserRequest;

@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {

}
