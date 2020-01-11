package ru.akozlovskiy.springdz08.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author {

	@Id
	private String id;

	private String name;

	private LocalDate birthDate;

	public Author(String name) {
		this.name = name;
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