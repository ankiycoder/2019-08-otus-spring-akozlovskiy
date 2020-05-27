package ru.akozlovskiy.springdz17.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Genre {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotBlank(message = "Поле название обязательное")
	private String description;
	
	public Genre() {		
	}

	public Genre(long id, @NotBlank(message = "Поле название обязательное") String description) {
		super();
		this.id = id;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(id).append(", description = ").append(description);
		return strb.toString();
	}
}
