package ru.akozlovskiy.library.domain;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	@JsonProperty("Логин пользователя")
	private String userLogin;

	@JsonProperty("Книга")
	private String bookName;

	@JsonProperty("Дата запроса")
	@Column(name = "REQUESTDATE")
	private Date requestDate;

	public UserRequest(Book book, String userLogin) {
		this.userLogin = userLogin;
		this.bookName = book.getTitle();
		this.requestDate = new Date();
	}
}
