package ru.akozlovskiy.springdz06.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz06.domain.Author;
import ru.akozlovskiy.springdz06.domain.Book;
import ru.akozlovskiy.springdz06.domain.Genre;
import ru.akozlovskiy.springdz06.domain.dao.AuthorDAO;
import ru.akozlovskiy.springdz06.domain.dao.BookDAO;
import ru.akozlovskiy.springdz06.domain.dao.GenreDAO;
import ru.akozlovskiy.springdz06.exception.DaoException;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookDAOJpa implements BookDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private GenreDAO genreDAO;

	public BookDAOJpa(AuthorDAO authorDAO, GenreDAOJpa genreDAO) {
		this.authorDAO = authorDAO;
		this.genreDAO = genreDAO;
	}

	@Override
	public long add(String bookName, String authorName, String description) throws DaoException {

		Author author = getAuthor(authorName);
		Genre genre = getGenre(description);

		Book book = new Book();
		book.setAuthor(author);
		book.setGenre(genre);
		book.setBookName(bookName);
		em.persist(book);

		return book.getId();
	}

	@Override
	public Book getById(long id) throws DaoException {
		return em.find(Book.class, id);
	}

	@Override
	public Book findByName(String name) {
		TypedQuery<Book> query = em.createQuery(
				"select b from Book b join fetch b.genre g join fetch b.author a where b.bookName=:bookName",
				Book.class);
		query.setParameter("bookName", name);
		return query.getSingleResult();
	}

	@Override
	public List<Book> findAllByAuthor(String authorName) throws DaoException {
		TypedQuery<Book> query = em.createQuery(
				"select b from Book b join fetch b.author ath join fetch b.genre g where ath.name = :authorName",
				Book.class);

		query.setParameter("authorName", authorName);
		return query.getResultList();
	}

	private Genre getGenre(String description) throws DaoException {
		try {
			return genreDAO.getByDescription(description);
		} catch (EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден жанр: " + description);
		}
	}

	private Author getAuthor(String authorName) throws DaoException {
		try {
			return authorDAO.getByName(authorName);
		} catch (EmptyResultDataAccessException e) {
			throw new DaoException("Ошибка добавления книги. В базе на найден автор с именем: " + authorName);
		}
	}

	@Override
	public List<Book> getAll() throws DaoException {
		TypedQuery<Book> query = em.createQuery(
				"select b from Book b",
				Book.class);
		return query.getResultList();
	}
}