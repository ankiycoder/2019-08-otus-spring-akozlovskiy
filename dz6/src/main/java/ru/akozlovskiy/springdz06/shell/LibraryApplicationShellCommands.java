package ru.akozlovskiy.springdz06.shell;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.domain.dao.GenreDAO;
import ru.akozlovskiy.springdz06.domain.service.CommentService;
import ru.akozlovskiy.springdz06.exception.DaoException;

@ShellComponent
public class LibraryApplicationShellCommands {

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private GenreDAO genreDAO;

	@Autowired
	private CommentService commentService;

	@ShellMethod(value = "Поиск всех книг", key = { "falb", "getAllBook" })
	public String getAllBook() throws DaoException {

		List<Book> bookList = bookDAO.getAll();

		StringBuilder strb = new StringBuilder();
		bookList.stream().forEach(book -> {
			strb.append(book.toString()).append("\n");
		});
		return strb.toString();
	}

	@ShellMethod(value = "Поиск книг по автору", key = { "faba", "findAllBookByAuthor" })
	public String findAllBookByAuthor(String auhtor) throws DaoException {

		List<Book> bookList = bookDAO.findAllByAuthor(auhtor);
		if (CollectionUtils.isEmpty(bookList)) {
			return "Для автора [" + auhtor + "] книги не найдены";
		}
		StringBuilder strb = new StringBuilder();
		bookList.stream().forEach(book -> {
			strb.append(book.toString()).append("\n");
		});

		return strb.toString();
	}

	@ShellMethod(value = "Добавление новой книги", key = { "adbk", "addBook" })
	public String addBook(String bookname, String authorName, String genre) throws DaoException {

		try {
			long bookid = bookDAO.add(bookname, authorName, genre);
			return "Книга добавлена, ID = " + bookid;
		} catch (DaoException ex) {
			return ex.getMessage();
		}
	}

	@ShellMethod(value = "Поиск книги по имени", key = { "fbbn", "findBookByName" })
	public String findBookByName(String bookname) throws DaoException {

		Book book = bookDAO.findByName(bookname);

		if (Objects.isNull(book)) {
			return "Книга с названием " + bookname + " не найдена";
		}
		return book.toString();
	}

	@ShellMethod(value = "Список всех авторов", key = { "gaat", "getAllAuthor" })
	public String getAllAuthor() throws DaoException {

		List<Author> bookid = authorDAO.getAll();
		StringBuilder strb = new StringBuilder();
		bookid.stream().forEach(book -> {
			strb.append(book.toString()).append("\n");
		});
		return strb.toString();
	}

	@ShellMethod(value = "Добавить автора", key = { "adat", "addAuthor" })
	public String addAuthor(String name, String birthDate) {
		long author;
		try {
			author = authorDAO.add(name, birthDate);
		} catch (DaoException e) {
			return e.getMessage();
		}
		return "Добавлен новый автор, ID = " + author;
	}

	@ShellMethod(value = "Список жанров", key = { "gagr", "getAllGenre" })
	public String getAllGenre() throws DaoException {

		List<Genre> genreid = genreDAO.getAll();
		StringBuilder strb = new StringBuilder();
		genreid.stream().forEach(genre -> {
			strb.append(genre.toString()).append("\n");
		});
		return strb.toString();
	}

	@ShellMethod(value = "Добавить жанр", key = { "adgr", "addGenre" })
	public String addGenre(String description) {
		long bookid = genreDAO.add(description);
		return "Добавлен новый жанр, ID = " + bookid;
	}

	@ShellMethod(value = "Добавить комментарий к книге", key = { "adcm", "addComment" })
	public String addComment(String comment, String bookName) {
		long commentid;
		try {
			commentid = commentService.add(comment, bookName);
		} catch (Throwable e) {
			return e.getMessage();
		}
		return "Добавлен новый коментарий, ID = " + commentid;
	}
}