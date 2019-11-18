package ru.akozlovskiy.springdz03.dao;

import java.util.List;

import ru.akozlovskiy.springdz03.domain.Question;

/**
 * 
 * Сервис получения списка вопросов
 * 
 * @author akozlovskiy
 *
 */
public interface QuestionDAO {
	List<Question> getQuestionList();
}