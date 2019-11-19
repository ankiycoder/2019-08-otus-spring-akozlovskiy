package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import ru.akozlovskiy.springdz05.domain.Book;

public class BookDAOJdbc implements BookDAO {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public void insert(Book book) {
		// TODO Auto-generated method stub

	}

	@Override
	public Book getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static class BookMapper implements RowMapper<Book> {

		@Override
		public Book mapRow(ResultSet resultSet, int i) throws SQLException {
			int id = resultSet.getInt("id");
			String title = resultSet.getString("title");
			long authorID = resultSet.getLong("authorID");
			long genreID = resultSet.getLong("genreID");
			return new Book(id, title, authorID, genreID);
		}
	}
}
