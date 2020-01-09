package ru.akozlovskiy.springdz08.changelogs;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;

import ru.akozlovskiy.springdz08.domain.Author;
import ru.akozlovskiy.springdz08.domain.Book;

@ChangeLog(order = "001")
public class InitMongoDBDataChangeLog {

	// private Knowledge springDataKnowledge;
	// private Knowledge mongockKnowledge;
	// private Knowledge aggregationApiKnowledge;
//
	@ChangeSet(order = "000", id = "dropDB", author = "ak", runAlways = true)
	public void dropDB(MongoDatabase database) {
		database.drop();
	}

	@ChangeSet(order = "001", id = "initBook", author = "ak", runAlways = true)
	public void initKnowledges(MongoTemplate template) {

		Book book = new Book();
		book.setTitle("TestTiitle");
		Author author = new Author();
		author.setName("AuthorName");
		book.setAuthor(author);
		template.save(book);

		Book book2 = new Book();
		book2.setTitle("TestTiitle2");
		Author author2 = new Author();
		author2.setName("AuthorName2");
		book.setAuthor(author2);
		template.save(book2);
	}

	@ChangeSet(order = "002", id = "initStudents", author = "ak", runAlways = true)
	public void initStudents(MongoTemplate template) {
		// template.save(new Student("Student #1", springDataKnowledge,
		// mongockKnowledge));
	}

	@ChangeSet(order = "003", id = "Teacher", author = "ak", runAlways = true)
	public void initTeachers(MongoTemplate template) {
		// val teacher = new Teacher("Teacher #1", springDataKnowledge,
		// mongockKnowledge, aggregationApiKnowledge);
		// template.save(teacher);
	}
}