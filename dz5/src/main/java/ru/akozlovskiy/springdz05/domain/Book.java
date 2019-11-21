package ru.akozlovskiy.springdz05.domain;

public class Book {

	private long id;

	private String title;

	private Long authorId;

	private Long genreID;

	public Book(long id, String title, Long authorId, Long genreID) {
		super();
		this.id = id;
		this.title = title;
		this.authorId = authorId;
		this.genreID = genreID;
	}

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

	public Long getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Long authorId) {
		this.authorId = authorId;
	}

	public Long getGenreID() {
		return genreID;
	}

	public void setGenreID(Long genreID) {
		this.genreID = genreID;
	}

}