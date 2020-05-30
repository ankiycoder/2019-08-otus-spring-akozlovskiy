package ru.akozlovskiy.useractivity.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserAcitvity {

	/*@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;*/

	@JsonProperty("Логин пользователя")
	private String userLogin;

	@JsonProperty("Книга")
	private String bookName;

	@JsonProperty("Автор")
	private String authorName;


}
