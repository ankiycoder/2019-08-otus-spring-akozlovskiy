package ru.akozlovskiy.springdz01.dao;

import java.util.List;

import ru.akozlovskiy.springdz01.domain.Question;

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