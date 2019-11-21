package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import ru.akozlovskiy.springdz05.domain.Book;

public class BookDAOJdbc implements BookDAO {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public void add(Book book) {

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", book.getId());
		params.put("tittle", book.getTitle());
		params.put("authorId", book.getAuthorId());
		params.put("genreID", null);
		namedParameterJdbcOperations.update("INSERT INTO BOOK VALUES (:id, :tittle, :authorId, :genreID)", params);
	}

	@Override
	public Book getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() {
		String sql = "SELECT * FROM BOOK";
		List<Book> orgList = namedParameterJdbcOperations.query(sql, new BookMapper());
		return orgList;
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet resultSet, int i) throws SQLException {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("tittle");
			long authorID = resultSet.getLong("authorID");
			long genreID = resultSet.getLong("genreID");
			return new Book(id, title, authorID, genreID);
		}
	}
}