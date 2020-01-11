package ru.akozlovskiy.springdz08.domain;

import org.springframework.data.annotation.Id;
<<<<<<< HEAD
import org.springframework.data.mongodb.core.mapping.DBRef;
=======
>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {

	@Id
	private String id;

	private String title;

<<<<<<< HEAD
	@DBRef
	private Author author;
	
=======
	private Author author;

>>>>>>> 207a043bde0d90ca0dc1ffd0239fbbf1e366848b
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

	public String toString() {
		StringBuilder strb = new StringBuilder();
		strb.append("ID = ").append(id).append(", title = ").append(title).append(", author = ")
				.append(author.getName()).append(", genre = ").append(genre.getDescription());
		return strb.toString();

	}
}