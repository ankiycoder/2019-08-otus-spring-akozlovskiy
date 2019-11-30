package ru.akozlovskiy.springdz06.domain.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.exception.DaoException;

@Repository
public class AuthorDAOJdbc implements AuthorDAO {

	private static final String NAME_FIELD = "name";
	private static final String ID_FIELD = "id";
	private static final String BIRTH_DATE_FIELD = "birthDate";

	private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	public AuthorDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public List<Author> getAll() {
		String sql = "SELECT * FROM AUTHOR";
		List<Author> orgList = namedParameterJdbcOperations.query(sql, new AuthorMapper());
		return orgList;
	}

	@Override
	public long add(String name, String birthDate) throws DaoException {

		LocalDate localDate;
		try {
			localDate = LocalDate.parse(birthDate, dateFormatter);
		} catch (Exception ex) {
			throw new DaoException("Не корректный формат даты рождения, должен быть: " + YYYY_MM_DD);
		}

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("name", name).addValue(BIRTH_DATE_FIELD,
				localDate);

		namedParameterJdbcOperations.update("INSERT INTO AUTHOR (name, birthDate) VALUES (:name, :birthDate)",
				parameters, holder);

		return holder.getKey().longValue();
	}

	@Override
	public Author getById(long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		String sql = "SELECT * FROM AUTHOR WHERE ID =:id";
		Author author = namedParameterJdbcOperations.queryForObject(sql, params, new AuthorMapper());
		return author;
	}

	@Override
	public Author getByName(String name) {
		Map<String, Object> params = Collections.singletonMap("name", name);
		String sql = "SELECT * FROM AUTHOR WHERE NAME =:name";
		Author author = namedParameterJdbcOperations.queryForObject(sql, params, new AuthorMapper());
		return author;
	}

	private static class AuthorMapper implements RowMapper<Author> {

		@Override
		public Author mapRow(ResultSet resultSet, int i) throws SQLException {
			java.sql.Date birthDate = resultSet.getDate(BIRTH_DATE_FIELD);
			Author author = new Author();
			author.setId(resultSet.getLong(ID_FIELD));
			author.setName(resultSet.getString(NAME_FIELD));
			author.setBirthDate(birthDate.toLocalDate());
			return author;
		}
	}
}