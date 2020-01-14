package ru.akozlovskiy.springdz08.domain;

public class Genre {

	private String description;

	public Genre(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("description = ").append(description);
		return strb.toString();
	}
}
