package ru.akozlovskiy.springdz07.domain.repository.impl;

import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryCustomImpl  {

	/*private static final String YYYY_MM_DD = "yyyy-MM-dd";

	private final static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);

	@PersistenceContext
	private EntityManager em;

	@Override
	public long add(String name, String birthDate) throws DaoException {
		LocalDate localDate;
		try {
			localDate = LocalDate.parse(birthDate, dateFormatter);
		} catch (Exception ex) {
			throw new DaoException("Не корректный формат даты рождения, должен быть: " + YYYY_MM_DD);
		}
		Author author = new Author();
		author.setBirthDate(localDate);
		author.setName(name);
		em.persist(author);

		return author.getId();
	}*/
}
