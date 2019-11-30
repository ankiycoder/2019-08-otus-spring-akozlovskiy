package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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

import ru.akozlovskiy.springdz05.domain.Genre;

@Repository
public class GenreDAOJdbc implements GenreDAO {

	private static final String ID_FIELD = "id";
	private static final String DESCRIPTION_FIELD = "description";

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public GenreDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public long add(String description) {

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue(DESCRIPTION_FIELD, description);

		namedParameterJdbcOperations.update("INSERT INTO GENRE (description) VALUES (:description)", parameters,
				holder);
		return holder.getKey().longValue();
	}

	@Override
	public Genre getByDescription(String description) {
		Map<String, Object> params = Collections.singletonMap(DESCRIPTION_FIELD, description);
		String sql = "SELECT * FROM GENRE WHERE description =:description";
		Genre genre = namedParameterJdbcOperations.queryForObject(sql, params, new GenreMapper());
		return genre;
	}

	@Override
	public Genre getById(long id) {
		Map<String, Object> params = Collections.singletonMap(ID_FIELD, id);
		String sql = "SELECT * FROM Genre WHERE ID =:id";
		return namedParameterJdbcOperations.queryForObject(sql, params, new GenreMapper());
	}

	@Override
	public List<Genre> getAll() {
		String sql = "SELECT * FROM Genre";
		List<Genre> orgList = namedParameterJdbcOperations.query(sql, new GenreMapper());
		return orgList;
	}

	static class GenreMapper implements RowMapper<Genre> {
		public Genre mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Genre genre = new Genre();
			genre.setId(resultSet.getLong(ID_FIELD));
			genre.setDescription(resultSet.getString(DESCRIPTION_FIELD));
			return genre;
		}
	}
}
