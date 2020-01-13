package ru.akozlovskiy.springdz09.domain.dto;

import ru.akozlovskiy.springdz09.domain.Book;

public class BookDTO {

	private String title;
	private String authorName;
	private String genre;
	
	public BookDTO(Book book) {
		
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

}