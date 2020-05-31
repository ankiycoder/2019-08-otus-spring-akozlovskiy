package ru.akozlovskiy.userstat.actuator;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import lombok.RequiredArgsConstructor;
import ru.akozlovskiy.userstat.repositories.UserRequestRepository;

@RequiredArgsConstructor
@Component
@Endpoint(id = "userrequeststat")
public class UserRequestStatEndpoint {

	private final UserRequestRepository userRequestRepository;		

	@ReadOperation
	public List<?> getBooksCountByUserStat(@Selector StatType statType) {

		Assert.notNull(statType, "category must not be null");
		switch (statType) {
		case BOOKS_COUNT_BY_USER:
			return userRequestRepository.getBooksCountByUser();
		case BOOKS_COUNT_BY_BOOK:
			return userRequestRepository.getBooksCountByBook();
		default:
			return Collections.emptyList();
		}
	}

	enum StatType {
		BOOKS_COUNT_BY_USER, BOOKS_COUNT_BY_BOOK
	}
}
