package ru.akozlovskiy.springdz05.domain.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import ru.akozlovskiy.springdz05.domain.Author;
import ru.akozlovskiy.springdz05.domain.Genre;

public class GenreDAOJdbc implements GenreDAO {

	private final NamedParameterJdbcOperations namedParameterJdbcOperations;

	public GenreDAOJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
		this.namedParameterJdbcOperations = namedParameterJdbcOperations;
	}

	@Override
	public void insert(Genre genre) {
		// TODO Auto-generated method stub
	}

	@Override
	public Genre getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
