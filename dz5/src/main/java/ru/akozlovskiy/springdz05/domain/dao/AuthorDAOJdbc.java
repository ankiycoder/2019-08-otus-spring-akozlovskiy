package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
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

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", author.getId());
		params.put("name", author.getName());
		params.put("birthdate", author.getBirthDate());
		namedParameterJdbcOperations.update("INSERT INTO  AUTHOR VALUES (:id, :name, :birthdate)", params);
	}

	@Override
	public Author getById(long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		return namedParameterJdbcOperations.queryForObject("SELECT * FROM AUTHOR WHERE id =:id", params,
				new AuthorMapper());
	}

	@Override
	public List<Author> getAll() {
		String sql = "SELECT * FROM AUTHOR";
		List<Author> orgList = namedParameterJdbcOperations.query(sql, new BeanPropertyRowMapper<Author>(Author.class));
		return orgList;
	}

	private static class AuthorMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet resultSet, int i) throws SQLException {
			Long id = resultSet.getLong("id");
			String name = resultSet.getString("name");
			java.sql.Date birthDate = resultSet.getDate("birthDate");
			return new Author(id, name, birthDate.toLocalDate());
		}
	}
}