package ru.akozlovskiy.springdz05.domain;

public class Book {

	private long id;

	private String bookName;

	private Author author;

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