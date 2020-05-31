package ru.akozlovskiy.userstat.actuator;

import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.userstat.domain.BookStatElem;
import ru.akozlovskiy.userstat.repositories.UserRequestRepository;

@RequiredArgsConstructor
@Component
@Endpoint(id = "bookrequeststat")
public class BookRequestStatEndpoint {

	private final UserRequestRepository userRequestRepository;

	@ReadOperation
	public List<BookStatElem> getBooksCountByBook() {
		return userRequestRepository.getBooksCountByBook();

	}
}