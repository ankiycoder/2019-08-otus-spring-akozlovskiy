package ru.akozlovskiy.userstat.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import ru.akozlovskiy.userstat.domain.BookStatElem;
import ru.akozlovskiy.userstat.domain.UserRequest;
import ru.akozlovskiy.userstat.domain.UserStatElem;

/*@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {

	@Query(value = "select ur.userLogin, count(*) from User_Request ur GROUP BY ur.userLogin", nativeQuery = true)
	List<?> getBooksCountByUser();

	@Query(value = "select ur.bookName, count(*) from User_Request ur GROUP BY ur.bookName", nativeQuery = true)
	List<?> getBooksCountByBook();*/

@Transactional
public interface UserRequestRepository extends JpaRepository<UserRequest, Long> {
	@Query(value = "select new ru.akozlovskiy.userstat.domain.UserStatElem(ur.userLogin, count(*)) from UserRequest ur GROUP BY ur.userLogin")
	List<UserStatElem> getBooksCountByUser();

	@Query(value = "select new ru.akozlovskiy.userstat.domain.BookStatElem (ur.bookName, count(*)) from UserRequest ur GROUP BY ur.bookName")
	List<BookStatElem> getBooksCountByBook();
}
