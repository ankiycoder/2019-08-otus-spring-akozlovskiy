package ru.akozlovskiy.springdz06.domain.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.springdz06.domain.Comment;
import ru.akozlovskiy.springdz06.domain.dao.CommentDAO;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class CommentDAOJpa implements CommentDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long add(Comment comment) {
		em.persist(comment);
		return comment.getId();
	}

	@Override
	public List<Comment> findAllByBookName(String bookName) {
		TypedQuery<Comment> query = em.createQuery(
				"select c from Comment c join fetch c.book b  where b.bookName = :bookName", Comment.class);

		query.setParameter("bookName", bookName);
		return query.getResultList();
	}
}
