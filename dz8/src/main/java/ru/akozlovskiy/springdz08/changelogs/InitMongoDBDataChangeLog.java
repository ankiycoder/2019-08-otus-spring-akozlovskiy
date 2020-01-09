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

	@ChangeSet(order = "003", id = "Author", author = "ak", runAlways = true)
	public void initAuthor(MongoTemplate template) {
		Author authorForSave = new Author();
		authorForSave.setName("AuthorName");
		author = template.save(authorForSave);
	}
	
	@ChangeSet(order = "001", id = "initBook", author = "ak", runAlways = true)
	public void initKnowledges(MongoTemplate template) {

		Book book = new Book();
		book.setTitle("TestTiitle");
		book.setAuthor(author);

		Genre genre = new Genre();
		genre.setDescription("Genre");
		book.setGenre(genre);

		template.save(book);
	}

	@ChangeSet(order = "002", id = "initComments", author = "ak", runAlways = true)
	public void initStudents(MongoTemplate template) {
		// template.save(new Student("Student #1", springDataKnowledge,
		// mongockKnowledge));
	}


}