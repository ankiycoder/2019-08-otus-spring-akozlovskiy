package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz05.domain.Author;

@Repository
public class AuthorDAOJdbc implements AuthorDAO {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public AuthorDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public void insert(Author author) {
		// TODO Auto-generated method stub

	}

	@Override
	public Author getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static class AuthorMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet resultSet, int i) throws SQLException {
			int id = resultSet.getInt("id");
			String name = resultSet.getString("name");
			Date birthDate = resultSet.getDate("birthDate");
			return new Author(id, name, birthDate);
		}
	}

}
