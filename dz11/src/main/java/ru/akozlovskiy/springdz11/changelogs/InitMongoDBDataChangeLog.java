package ru.akozlovskiy.springdz11.changelogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.Book;
import ru.akozlovskiy.springdz11.domain.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

	private Author author;

	@ChangeSet(order = "000", id = "dropDB", author = "ak", runAlways = true)
	public void dropDB(MongoDatabase database) {
		database.drop();
	}

	@ChangeSet(order = "001", id = "initAuthor", author = "ak", runAlways = true)
	public void initAuthor(MongoTemplate template) {
		Author authorForSave = new Author("Самуил Яковлевич Маршак", "1887-11-03");
		author = template.save(authorForSave);
		template.save(new Author("Александр Сергеевич Пушкин", "1799-06-06"));
		template.save(new Author("Михаил Юрьевич Лермонтов", "1814-10-15"));
	}

	@ChangeSet(order = "002", id = "initBook", author = "ak", runAlways = true)
	public void initBook(MongoTemplate template) {

		Book book = new Book();
		book.setTitle("Усатый полосатый");
		book.setAuthor(author);
		book.setGenre(new Genre("Детский"));
		template.save(book);

		book = new Book();
		book.setTitle("Вот такой рассеяный");
		book.setAuthor(author);
		book.setGenre(new Genre("Детский"));
		template.save(book);

		book = new Book();
		book.setTitle("Багаж");
		book.setAuthor(author);
		book.setGenre(new Genre("Детский"));
		template.save(book);

		book = new Book();
		book.setTitle("Сказка об умном мышонке");
		book.setAuthor(author);
		book.setGenre(new Genre("Детский"));
		template.save(book);
	}
}
