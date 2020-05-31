package ru.akozlovskiy.userstat.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookStatElem {

	private String bookName;

	private Long requestCount;

	public BookStatElem(String bookName, Long requestCount) {
		this.bookName = bookName;
		this.requestCount = requestCount;
	}
}