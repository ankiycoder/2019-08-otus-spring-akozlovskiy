package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Book;

@Repository
public class AuthorDAOJdbc implements AuthorDAO {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public AuthorDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public void add(Author author) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", author.getId());
		params.put("name", author.getName());
		params.put("birthdate", author.getBirthDate());
		namedParameterJdbcOperations.update("INSERT INTO  AUTHOR VALUES (:id, :name, :birthdate)", params);
	}

	@Override
	public Author getById(long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		String sql = "SELECT ath.id, ath.name, ath.birthDate, bk.id as book_id, bk.tittle as book_tittle, bk.authorId as book_authorId FROM AUTHOR ath  INNER JOIN BOOK bk ON ath.id = bk.id WHERE ath.id =:id";
		return namedParameterJdbcOperations.queryForObject(sql, params, new AuthorMapper2(new BookMapper()));
	}

	@Override
	public List<Author> getAll() {
		String sql = "SELECT * FROM AUTHOR";
		List<Author> orgList = namedParameterJdbcOperations.query(sql, new AuthorMapper());
		return orgList;
	}

	/*
	 * List<Book> getAllNBooks(){ return null; }
	 */

	private static class AuthorMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet resultSet, int i) throws SQLException {
			Long id = resultSet.getLong("id");
			String name = resultSet.getString("name");
			java.sql.Date birthDate = resultSet.getDate("birthDate");
			return new Author(id, name, birthDate.toLocalDate());
		}
	}

	public static class AuthorMapper2 implements RowMapper<Author> {

		private final Map<Long, Author> authorMap = new HashMap<Long, Author>();

		private final BookMapper bookMapper;

		public AuthorMapper2(BookMapper bookMapper) {
			this.bookMapper = bookMapper;
		}

		public Author mapRow(ResultSet rs, int rowNum) throws SQLException {

			Long id = rs.getLong("id");

			Author author = authorMap.get(id);

			if (author == null) {
				String name = rs.getString("name");
				java.sql.Date birthDate = rs.getDate("birthDate");
				author = new Author(id, name, birthDate.toLocalDate());
				authorMap.put(id, author);
			}

			Book book = this.bookMapper.mapRow(rs, rowNum);
			author.getBookList().add(book);

			return author;
		}
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet resultSet, int i) throws SQLException {
			Long id = resultSet.getLong("book_id");
			String title = resultSet.getString("book_tittle");
			long authorId = resultSet.getLong("book_authorId");
			return new Book(id, title, authorId, null);
		}
	}
}