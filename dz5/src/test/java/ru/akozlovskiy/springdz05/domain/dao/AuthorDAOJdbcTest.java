package ru.akozlovskiy.springdz05.domain.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Book;

@ExtendWith(SpringExtension.class)
@JdbcTest
@Import({AuthorDAOJdbc.class, BookDAOJdbc.class})
public class AuthorDAOJdbcTest {

	@Autowired
	AuthorDAOJdbc authorDAOJdbc;
	
	@Autowired
	BookDAOJdbc bookDAOJdbc;

	@Test
	public void testDao() {
		Author author = new Author(1l, "name1", LocalDate.now());
		authorDAOJdbc.add(author);
		
		Author author2 = new Author(2l, "name2", LocalDate.now());
		authorDAOJdbc.add(author2);
		
		Book book = new Book(1l, "title", 2l, null) ;
		bookDAOJdbc.add(book);
		
		Book book2 = new Book(2l, "title2", 2l, null) ;
		bookDAOJdbc.add(book2);
		
		Author getByIdResult = authorDAOJdbc.getById(2);	
		assertThat(author).isEqualToComparingFieldByField(getByIdResult);
		
		List<Author> authorList = authorDAOJdbc.getAll();
		assertEquals(authorList.size(), 2);
		
		
	}
}
