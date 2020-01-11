package ru.akozlovskiy.springdz08.changelogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.domain.Book;
import ru.akozlovskiy.springdz08.domain.Genre;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

	private Author author;

	@ChangeSet(order = "000", id = "dropDB", author = "ak", runAlways = true)
	public void dropDB(MongoDatabase database) {
		database.drop();
	}

	@ChangeSet(order = "001", id = "initAuthor", author = "ak", runAlways = true)
	public void initAuthor(MongoTemplate template) {
		Author authorForSave = new Author("Маршак");
		author = template.save(authorForSave);
	}

	@ChangeSet(order = "002", id = "initBook", author = "ak", runAlways = true)
	public void initBook(MongoTemplate template) {

		Book book = new Book();
		book.setTitle("Усатый полосатый");
		book.setAuthor(author);
		book.setGenre(new Genre("Детский"));

		template.save(book);
	}
}
