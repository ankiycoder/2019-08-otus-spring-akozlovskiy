package ru.akozlovskiy.springdz05.domain.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Book;
import ru.akozlovskiy.springdz05.domain.Genre;
import ru.akozlovskiy.springdz05.exception.DaoException;

@Repository
public class BookDAOJdbc implements BookDAO {

	private static final String ID_FIELD = "id";

	private static final String GENRE_ID_FIELD = "genreID";

	private static final String BOOK_NAME_FIELD = "BookName";

	private static final String AUTHOR_ID_FIELD = "authorId";
	
	private static final String SELECT_SQL = "SELECT bk.id, bk.BookName, ath.id as authorId, bk.genreid as genreid from book bk inner join author ath on bk.authorId = ath.id inner join genre g on bk.genreid = g.id WHERE ";

	private final AuthorDAO authorDAO;

	private final GenreDAO genreDAO;

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public BookDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations, AuthorDAO authorDAO, GenreDAO genreDAO) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
		this.authorDAO = authorDAO;
		this.genreDAO = genreDAO;
	}

	@Override
	public Book getById(long id) {
		String sql = SELECT_SQL + " bk.id = ?";
		List<Book> query = namedParameterJdbcOperations.getJdbcOperations().query(sql,
				new BookResultSetExtractor(authorDAO, genreDAO), id);

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
				.addValue("authorId", author.getId()).addValue(GENRE_ID_FIELD, genre.getId());
		namedParameterJdbcOperations.update(
				"INSERT INTO BOOK (BookName, authorId, genreID) VALUES (:BookName, :authorId, :genreID)", parameters,
				holder);
		return holder.getKey().longValue();
	}

	@Override
	public List<Book> findAllByAuthor(String authorName) {

		String sql = SELECT_SQL + "ath.name = ?";
		return namedParameterJdbcOperations.getJdbcOperations().query(sql,
				new BookResultSetExtractor(authorDAO, genreDAO), authorName);
	}

	@Override
	public List<Book> findByName(String bookName) {

		String sql = SELECT_SQL + "bk.bookname = ?";
		return namedParameterJdbcOperations.getJdbcOperations().query(sql,
				new BookResultSetExtractor(authorDAO, genreDAO), bookName);
	}

	static class BookResultSetExtractor implements ResultSetExtractor<List<Book>> {

		List<Book> books = new ArrayList<>();

		private Author author = null;
		private Genre genre = null;

		private final AuthorDAO authorDAO;

		private final GenreDAO genreDAO;

		public BookResultSetExtractor(AuthorDAO authorDAO, GenreDAO genreDAO) {
			this.authorDAO = authorDAO;
			this.genreDAO = genreDAO;
		}

		@Override
		public List<Book> extractData(ResultSet set) throws SQLException {

			while (set.next()) {
				Book book = new Book();
				book.setId(set.getLong(ID_FIELD));
				book.setBookName(set.getString(BOOK_NAME_FIELD));

				if (author == null) {
					long authorId = set.getLong(AUTHOR_ID_FIELD);
					author = authorDAO.getById(authorId);
				}
				book.setAuthor(author);

				if (genre == null) {
					long genreId = set.getLong(GENRE_ID_FIELD);
					genre = genreDAO.getById(genreId);
				}
				book.setGenre(genre);
				books.add(book);
			}
			return books;
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
}