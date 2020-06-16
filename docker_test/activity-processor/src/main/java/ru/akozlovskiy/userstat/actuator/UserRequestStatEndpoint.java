package ru.akozlovskiy.userstat.actuator;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.userstat.domain.UserStatElem;
import ru.akozlovskiy.userstat.repositories.UserRequestRepository;

@RequiredArgsConstructor
@Component
@Endpoint(id = "userrequeststat")
public class UserRequestStatEndpoint {

	private final UserRequestRepository userRequestRepository;

	@ReadOperation
	public List<UserStatElem> getBooksCountByUser() {
		return userRequestRepository.getBooksCountByUser();

	}
}
