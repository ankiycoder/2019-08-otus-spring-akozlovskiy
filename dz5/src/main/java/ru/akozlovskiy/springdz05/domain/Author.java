package ru.akozlovskiy.springdz05.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Author {

	private Long id;

	private String name;

	private LocalDate birthDate;

	private List<Book> bookList = new ArrayList<Book>();

	public Author() {
	}

	public Author(Long id, String name, LocalDate localDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = localDate;
	}

	public void addBook(Book book) {
		bookList.add(book);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public List<Book> getBookList() {
		return bookList;
	}

	public void setBookList(List<Book> bookList) {
		this.bookList = bookList;
	}
}