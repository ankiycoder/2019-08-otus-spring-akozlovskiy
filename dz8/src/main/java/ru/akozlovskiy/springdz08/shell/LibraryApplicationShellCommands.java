package ru.akozlovskiy.springdz08.shell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.Comment;
import ru.akozlovskiy.springdz08.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz08.domain.service.BookService;
import ru.akozlovskiy.springdz08.domain.service.CommentService;
import ru.akozlovskiy.springdz08.exception.DaoException;

@ShellComponent
public class LibraryApplicationShellCommands {

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	private final AuthorRepository authorRepository;

	private final BookService bookService;

	private final CommentService commentService;

	public LibraryApplicationShellCommands(AuthorRepository authorRepository, BookService bookService,
			CommentService commentService) {
		this.authorRepository = authorRepository;
		this.bookService = bookService;
		this.commentService = commentService;
	}

	@ShellMethod(value = "Поиск всех книг", key = { "falb", "getAllBook" })
	public String getAllBook() throws DaoException {

		List<Book> bookList = bookService.getAll();

		StringBuilder strb = new StringBuilder();
		bookList.forEach(book -> strb.append(book.toString()).append("\n"));
		return strb.toString();
	}

	@ShellMethod(value = "Поиск книг по автору", key = { "faba", "findAllBookByAuthor" })
	public String findAllBookByAuthor(String auhtor) throws DaoException {

		List<Book> bookList = bookService.findAllByAuthor(auhtor);
		if (CollectionUtils.isEmpty(bookList)) {
			return "Для автора [" + auhtor + "] книги не найдены";
		}
		StringBuilder strb = new StringBuilder();
		bookList.forEach(book -> strb.append(book.toString()).append("\n"));

		return strb.toString();
	}

	@ShellMethod(value = "Добавление новой книги", key = { "adbk", "addBook" })
	public String addBook(String bookname, String authorName, String genre) throws DaoException {

		try {
			String bookid = bookService.add(bookname, authorName, genre);
			return "Книга добавлена, ID = " + bookid;
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@ShellMethod(value = "Поиск книги по имени", key = { "fbbn", "findBookByName" })
	public String findBookByName(String bookname) throws DaoException {

		Optional<Book> book = bookService.findByTitle(bookname);

		return book.map(Objects::toString).orElse(String.format("Книга с названием %s не найдена", bookname));
	}

	@ShellMethod(value = "Список всех авторов", key = { "gaat", "getAllAuthor" })
	public String getAllAuthor() {

		List<Author> bookid = authorRepository.findAll();
		StringBuilder strb = new StringBuilder();
		bookid.forEach(book -> strb.append(book.toString()).append("\n"));
		return strb.toString();
	}

	@ShellMethod(value = "Добавить автора", key = { "adat", "addAuthor" })
	public String addAuthor(String name, String birthDate) {

		LocalDate localDate;
		try {
			localDate = LocalDate.parse(birthDate, dateFormatter);
		} catch (Exception ex) {
			return ("Не корректный формат даты рождения, должен быть: " + YYYY_MM_DD);
		}
		Author author = new Author(name);
		author.setBirthDate(localDate);
		authorRepository.save(author);

		return "Добавлен новый автор, ID = " + author.getId();
	}

	@ShellMethod(value = "Добавить комментарий к книге", key = { "adcm", "addComment" })
	public String addComment(String commentText, String bookName) {
		Comment addComment = null;
		try {
			addComment = commentService.add(commentText, bookName);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Добавлен новый коментарий, ID = " + addComment.getId();
	}

	@ShellMethod(value = "Найти все комментарии к книге", key = { "facb", "findAllComment" })
	public String addComment(String bookName) {
		List<Comment> comList = commentService.findAllByBookName(bookName);
		StringBuilder strb = new StringBuilder();
		comList.forEach(comment -> strb.append(comment.getText()).append("\n"));
		return strb.toString();
	}
}