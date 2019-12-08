package ru.akozlovskiy.springdz06.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name="bookname")
	private String bookName;

	@ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "authorid")
	private Author author;

	@ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "genreid")
	private Genre genre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
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
		strb.append("ID = ").append(id).append(", bookName = ").append(bookName).append(", author = ")
				.append(author.getName()).append(", genre = ").append(genre.getDescription());
		return strb.toString();

	}
}