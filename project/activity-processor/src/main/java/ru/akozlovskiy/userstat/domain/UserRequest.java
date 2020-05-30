package ru.akozlovskiy.userstat.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "UserRequest")
public class UserRequest {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@JsonProperty("Логин пользователя")
	@Column(name = "USERLOGIN")
	private String userLogin;

	@JsonProperty("Книга")
	@Column(name = "BOOKNAME")
	private String bookName;

	@JsonProperty("Дата запроса")
	@Column(name = "REQUESTDATE")
	private Date requestDate;
}
