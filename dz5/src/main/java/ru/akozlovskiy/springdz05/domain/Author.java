package ru.akozlovskiy.springdz05.domain;

import java.time.LocalDate;

public class Author {

	private Long id;

	private String name;

	private LocalDate birthDate;
	
	public Author() {
	}

	public Author(Long id, String name, LocalDate localDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = localDate;
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
}