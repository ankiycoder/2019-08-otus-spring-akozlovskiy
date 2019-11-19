package ru.akozlovskiy.springdz05.domain;

public class Book {

	private long id;

	private String title;

	private long authorID;

	private long genreID;

	public Book(long id, String title, long authorID, long genreID) {
		super();
		this.id = id;
		this.title = title;
		this.authorID = authorID;
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

	public long getAuthorID() {
		return authorID;
	}

	public void setAuthorID(long authorID) {
		this.authorID = authorID;
	}

	public long getGenreID() {
		return genreID;
	}

	public void setGenreID(long genreID) {
		this.genreID = genreID;
	}
}