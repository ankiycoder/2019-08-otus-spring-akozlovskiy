package ru.akozlovskiy.springdz05.domain;

public class Genre {

	private long id;

	private String description;

	public Genre(long id, String description) {
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
}
