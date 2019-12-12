package ru.akozlovskiy.springdz07.shell;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.util.CollectionUtils;

import ru.akozlovskiy.springdz07.domain.Author;
import ru.akozlovskiy.springdz07.domain.Book;
import ru.akozlovskiy.springdz07.domain.Comment;
import ru.akozlovskiy.springdz07.domain.Genre;
import ru.akozlovskiy.springdz07.domain.repository.AuthorRepository;
import ru.akozlovskiy.springdz07.domain.repository.GenreRepository;
import ru.akozlovskiy.springdz07.domain.service.BookService;
import ru.akozlovskiy.springdz07.domain.service.CommentService;
import ru.akozlovskiy.springdz07.exception.DaoException;

@ShellComponent
public class LibraryApplicationShellCommands {
	
	private static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	private AuthorRepository authorRepository;

	private BookService bookService;

	private GenreRepository genreRepository;

	private CommentService commentService;

	public LibraryApplicationShellCommands(AuthorRepository authorRepository, BookService bookService,
			GenreRepository genreRepository, CommentService commentService) {
		this.authorRepository = authorRepository;
		this.bookService = bookService;
		this.genreRepository = genreRepository;
		this.commentService = commentService;
	}

	@ShellMethod(value = "Поиск всех книг", key = { "falb", "getAllBook" })
	public String getAllBook() throws DaoException {

		List<Book> bookList = bookService.getAll();

		StringBuilder strb = new StringBuilder();
		bookList.stream().forEach(book -> {
			strb.append(book.toString()).append("\n");
		});
		return strb.toString();
	}

	@ShellMethod(value = "Поиск книг по автору", key = { "faba", "findAllBookByAuthor" })
	public String findAllBookByAuthor(String auhtor) throws DaoException {

		List<Book> bookList = bookService.findAllByAuthor(auhtor);
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
			long bookid = bookService.add(bookname, authorName, genre);
			return "Книга добавлена, ID = " + bookid;
		} catch (Exception ex) {
			return ex.getMessage();
		}
	}

	@ShellMethod(value = "Поиск книги по имени", key = { "fbbn", "findBookByName" })
	public String findBookByName(String bookname) throws DaoException {

		Optional<Book> book = bookService.findByName(bookname);

		if (!book.isPresent()) {
			return "Книга с названием " + bookname + " не найдена";
		}
		return book.toString();
	}

	@ShellMethod(value = "Список всех авторов", key = { "gaat", "getAllAuthor" })
	public String getAllAuthor() throws DaoException {

		List<Author> bookid = authorRepository.findAll();
		StringBuilder strb = new StringBuilder();
		bookid.stream().forEach(book -> {
			strb.append(book.toString()).append("\n");
		});
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
		Author author = new Author();
		author.setBirthDate(localDate);
		author.setName(name);
		authorRepository.save(author);
		
		return "Добавлен новый автор, ID = " + author.getId();
	}

	@ShellMethod(value = "Список жанров", key = { "gagr", "getAllGenre" })
	public String getAllGenre() throws DaoException {

		List<Genre> genreid = genreRepository.findAll();
		StringBuilder strb = new StringBuilder();
		genreid.stream().forEach(genre -> {
			strb.append(genre.toString()).append("\n");
		});
		return strb.toString();
	}

	@ShellMethod(value = "Добавить жанр", key = { "adgr", "addGenre" })
	public String addGenre(String description) {
		Genre genre = new Genre();
		genre.setDescription(description);
		long bookid = genreRepository.save(genre).getId();
		return "Добавлен новый жанр, ID = " + bookid;
	}

	@ShellMethod(value = "Добавить комментарий к книге", key = { "adcm", "addComment" })
	public String addComment(String comment, String bookName) {
		long commentid;
		try {
			commentid = commentService.add(comment, bookName);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Добавлен новый коментарий, ID = " + commentid;
	}

	@ShellMethod(value = "Найти все комментарии к книге", key = { "facb", "findAllComment" })
	public String addComment(String bookName) {
		List<Comment> comList = commentService.findAllByBookName(bookName);
		StringBuilder strb = new StringBuilder();
		comList.stream().forEach(comment -> {
			strb.append(comment.getComment()).append("\n");
		});
		return strb.toString();
	}
}