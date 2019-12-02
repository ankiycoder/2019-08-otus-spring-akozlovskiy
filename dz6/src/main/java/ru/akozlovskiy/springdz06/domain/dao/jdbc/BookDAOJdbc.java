package ru.akozlovskiy.springdz06.domain.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.CollectionUtils;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.domain.dao.GenreDAO;
import ru.akozlovskiy.springdz06.exception.DaoException;

//@Repository
public class BookDAOJdbc implements BookDAO {

	private static final String ID_FIELD = "id";
	private static final String GENRE_ID_FIELD = "genreID";
	private static final String BOOK_NAME_FIELD = "bookName";
	private static final String AUTHOR_ID_FIELD = "authorId";

	private static final String SELECT_SQL = "SELECT bk.id, bk.BookName, ath.id as authorId, ath.name as author_name, ath.birthdate as author_birthdate,  bk.genreid as genreid, g.description as genre_description from book bk inner join author ath on bk.authorId = ath.id inner join genre g on bk.genreid = g.id WHERE ";

	private final AuthorDAO authorDAO;

	private final GenreDAO genreDAO;

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDAO authorDAO,
			GenreDAO genreDAO) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
		this.authorDAO = authorDAO;
		this.genreDAO = genreDAO;
	}

	@Override
	public Book getById(long id) {
		String sql = SELECT_SQL + " bk.id = ?";
		List<Book> query = namedParameterJdbcOperations.getJdbcOperations().query(sql, new BookMapper(), id);

		if (CollectionUtils.isEmpty(query)) {
			return null;
		}
		return query.get(0);
	}

	@Override
	public long add(String bookname, String authorName, String description) throws DaoException {

		Author author = getAuthor(authorName);
		Genre genre = getGenre(description);

		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameters = new MapSqlParameterSource().addValue(BOOK_NAME_FIELD, bookname)
				.addValue(AUTHOR_ID_FIELD, author.getId()).addValue(GENRE_ID_FIELD, genre.getId());
		namedParameterJdbcOperations.update(
				"INSERT INTO BOOK (BookName, authorId, genreID) VALUES (:bookName, :authorId, :genreID)", parameters,
				holder);
		return holder.getKey().longValue();
	}

	@Override
	public List<Book> findAllByAuthor(String authorName) {
		String sql = SELECT_SQL + "ath.name = ?";
		return namedParameterJdbcOperations.getJdbcOperations().query(sql, new BookMapper(), authorName);
	}

	@Override
	public Book findByName(String bookName) {
		String sql = SELECT_SQL + "bk.bookname = ?";
		return namedParameterJdbcOperations.getJdbcOperations().queryForObject(sql, new BookMapper(), bookName);
	}

	private class BookMapper implements RowMapper<Book> {
		private static final String GENRE_DESCRIPTION_FIELD = "genre_description";
		private static final String AUTHOR_BIRTHDATE_FIELD = "author_birthdate";
		private static final String AUTHOR_NAME_FIELD = "author_name";

		@Override
		public Book mapRow(ResultSet rs, int i) throws SQLException {
			Book book = new Book();
			book.setId(rs.getLong(ID_FIELD));
			book.setBookName(rs.getString(BOOK_NAME_FIELD));

			Author author = new Author();
			author.setId(rs.getLong(AUTHOR_ID_FIELD));
			author.setName(rs.getString(AUTHOR_NAME_FIELD));
			java.sql.Date birthDate = rs.getDate(AUTHOR_BIRTHDATE_FIELD);
			author.setBirthDate(birthDate.toLocalDate());
			book.setAuthor(author);

			Genre genre = new Genre();
			genre.setId(rs.getLong(GENRE_ID_FIELD));
			genre.setDescription(rs.getString(GENRE_DESCRIPTION_FIELD));
			book.setGenre(genre);

			return book;
		}
	}

	private Genre getGenre(String description) throws DaoException {
		try {
			return genreDAO.getByDescription(description);
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + description);
		}
	}

	private Author getAuthor(String authorName) throws DaoException {
		try {
			return authorDAO.getByName(authorName);
		} catch (org.springframework.dao.IncorrectResultSizeDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}
	}

	@Override
	public List<Book> getAll() throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}
}