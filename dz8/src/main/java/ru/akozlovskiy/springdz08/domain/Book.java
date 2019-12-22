package ru.akozlovskiy.springdz08.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;

@NamedEntityGraph(
		  name = "book-entity-graph",
		  attributeNodes = {
		    @NamedAttributeNode("author"),
		    @NamedAttributeNode("genre")
		  }
		)
@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "title")
	private String title;

	@ManyToOne(targetEntity = Author.class, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(nullable = false, name = "authorid")
	private Author author;

	@ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER, optional = false)
	@JoinColumn(nullable = false, name = "genreid")
	private Genre genre;

	public long getId() {
		return id;
	}

	public void setId(long id) {
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