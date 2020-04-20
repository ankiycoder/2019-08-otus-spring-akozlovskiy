package ru.akozlovskiy.springdz12.domain.dto;

import javax.validation.constraints.NotBlank;

import ru.akozlovskiy.springdz12.domain.Book;

public class BookDTO {

	private Long id;

	@NotBlank (message = "Поле Название обязательное") 
	private String title;
	private String authorName;
	private Long authorId;
	private String genre;
	private Long genreId;

	public BookDTO(Book book) {

		this.id = book.getId();

		this.title = book.getTitle();

		if (book.getAuthor() != null) {
			this.authorName = book.getAuthor().getName();
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getGenreId() {
		return genreId;
	}

	public void setGenreId(Long genreId) {
		this.genreId = genreId;
	}

}