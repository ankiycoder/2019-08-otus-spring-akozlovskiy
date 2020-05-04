package ru.akozlovskiy.springdz12.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Поле имя обязательное")
	private String name;

	@Column(name="BIRTHDATE")
	@DateTimeFormat(pattern = "yyyy-MM-dd")	
	private LocalDate birthDate;

	public Author() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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