package ru.akozlovskiy.library.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequest {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonProperty("Логин пользователя")
	private String userLogin;

	@JsonProperty("Книга")
	private String bookName;

	@JsonProperty("Автор")
	private String authorName;

	public UserRequest(Book book, String userLogin) {
		this.userLogin = userLogin;
		this.bookName = book.getTitle();
		this.authorName = book.getAuthor().getName();
	}

}
