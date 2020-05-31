package ru.akozlovskiy.userstat.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserStatElem {

	private String userLogin;

	private Long requestCount;

	public UserStatElem(String userLogin, Long requestCount) {
		this.userLogin = userLogin;
		this.requestCount = requestCount;
	}
}