package ru.akozlovskiy.springdz06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.domain.dao.GenreDAO;
import ru.akozlovskiy.springdz06.domain.dao.jpa.AuthorDAOJpa;
import ru.akozlovskiy.springdz06.domain.dao.jpa.BookDAOJpa;
import ru.akozlovskiy.springdz06.domain.dao.jpa.GenreDAOJpa;

@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApplication.class, args);
	}

	public BookDAO getBookDAO(AuthorDAO authorDAO, GenreDAOJpa genreDAO) {
		return new BookDAOJpa(authorDAO, genreDAO);
	}

	public AuthorDAO getAuthorDAO() {
		return new AuthorDAOJpa();
	}

	public GenreDAO getGenreDAO() {
		return new GenreDAOJpa();
	}
}
