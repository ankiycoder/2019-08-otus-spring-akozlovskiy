package ru.akozlovskiy.springdz05.domain;

import java.time.LocalDate;

public class Author {

	private Long id;

	private String name;

	private LocalDate birthDate;

	public Author() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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