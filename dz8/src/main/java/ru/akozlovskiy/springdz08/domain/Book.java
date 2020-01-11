package ru.akozlovskiy.springdz08.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String id;

	private String title;

	@DBRef
	private Author author;

	private Genre genre;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	/*public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(id).append(", title = ").append(title).append(", author = ")
				.append(author.getName()).append(", genre = ").append(genre.getDescription());
		return strb.toString();

	}*/
}