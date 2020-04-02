package ru.akozlovskiy.springdz11.domain.dto;

import javax.validation.constraints.NotBlank;

import ru.akozlovskiy.springdz11.domain.Book;

public class BookDTO {

	private String id;

	@NotBlank (message = "Поле Название обязательное") 
	private String title;
	private String authorName;
	private String authorId;
	private String genre;
	private String genreId;

	public BookDTO(Book book) {

		this.id = book.getId();	
		this.title = book.getTitle();

		if (book.getAuthor() != null) {
			this.authorName = book.getAuthor().getName();
			this.authorId = book.getAuthor().getId();
		}

		if (book.getGenre() != null) {
			this.genre = book.getGenre().getDescription();
		}
	}

	public BookDTO() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthorId() {
		return authorId;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public String getGenreId() {
		return genreId;
	}

	public void setGenreId(String genreId) {
		this.genreId = genreId;
	}

}