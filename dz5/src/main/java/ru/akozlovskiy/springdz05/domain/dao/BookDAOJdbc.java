package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Book;
import ru.akozlovskiy.springdz05.domain.Genre;
import ru.akozlovskiy.springdz05.exception.DaoException;

public class BookDAOJdbc implements BookDAO {

	private final AuthorDAOJdbc authorDAOJdbc;
	
	private final GenreDAOJdbc genreDAOJdbc;

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDAOJdbc authorDAOJdbc,
			JdbcTemplate jdbcTemplate,  GenreDAOJdbc genreDAOJdbc) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
		this.authorDAOJdbc = authorDAOJdbc;
		this.genreDAOJdbc = genreDAOJdbc;
	}

	@Override
	public Book getById(long id) {
		Map<String, Object> params = Collections.singletonMap("id", id);
		String sql = "SELECT * FROM BOOK WHERE ID =:id";
		return namedParameterJdbcOperations.queryForObject(sql, params, new BookMapper(authorDAOJdbc));
	}

	@Override
	public long add(String bookname, String authorName, String description) throws DaoException {

		Author author = getAuthor(authorName);
		Genre genre = getGenre(description);

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue("BookName", bookname)
				.addValue("authorId", author.getId()).addValue("genreID", genre.getId());
		namedParameterJdbcOperations.update(
				"INSERT INTO BOOK (BookName, authorId, genreID) VALUES (:BookName, :authorId, :genreID)", parameters,
				holder);
		return holder.getKey().longValue();
	}

	private Genre getGenre(String description) throws DaoException {
		try {
			return genreDAOJdbc.getByDescription(description);
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + description);
		}
	}
	
	private Author getAuthor(String authorName) throws DaoException {
		try {
			return authorDAOJdbc.getByName(authorName);
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}
	}

	@Override
	public List<Book> findAllByAuthor(String authorName) {

		String sql2 = "SELECT bk.id, bk.BookName, ath.id as authorId from book bk inner join author ath on bk.authorId = ath.id inner join genre g on bk.genreid = g.id WHERE ath.name = ?";
		return namedParameterJdbcOperations.getJdbcOperations().query(sql2, new ResultSetExtractor<List<Book>>() {

			List<Book> books = new ArrayList<Book>();

			Author author = null;

			@Override
			public List<Book> extractData(ResultSet set) throws SQLException {

				while (set.next()) {
					Book book = new Book();
					book.setId(set.getLong("id"));
					book.setBookName(set.getString("BookName"));

					if (author == null) {
						long authorId = set.getLong("authorId");
						author = authorDAOJdbc.getById(authorId);
					}
					book.setAuthor(author);
					books.add(book);
				}
				return books;
			}
		}, authorName);
	}

	private static class BookMapper implements RowMapper<Book> {

		private AuthorDAOJdbc authorDAOJdbc;

		public BookMapper(AuthorDAOJdbc authorDAOJdbc) {
			this.authorDAOJdbc = authorDAOJdbc;
		}

		@Override
		public Book mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			Book book = new Book();
			book.setId(resultSet.getLong("id"));
			book.setBookName(resultSet.getString("BookName"));
			long authorid = resultSet.getLong("authorid");
			book.setAuthor(authorDAOJdbc.getById(authorid));
			return book;
		}
	}
}