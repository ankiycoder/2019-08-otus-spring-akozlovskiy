package ru.akozlovskiy.springdz11.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document
public class Author {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Id
	private String id;

	@NotBlank(message = "Поле имя обязательное")
	private String name;

	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private LocalDate birthDate;

	public Author() {
	}

	public Author(String name, String birthDate) {
		this.name = name;
		this.birthDate = LocalDate.parse(birthDate, dateFormatter);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(id).append(", name = ").append(name).append(", birthDate = ").append(birthDate);
		return strb.toString();
	}
}