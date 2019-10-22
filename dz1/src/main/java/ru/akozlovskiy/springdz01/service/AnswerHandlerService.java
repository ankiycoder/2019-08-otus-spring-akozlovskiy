package ru.akozlovskiy.springdz01.service;

import ru.akozlovskiy.springdz01.domain.Question;

/**
 * 
 * Сервис обработки ответов
 * @author akozlovskiy
 *
 */
public interface AnswerHandlerService {

	/** Добавляет Вопрос и полученный на него ответ 
	 * @param question
	 * @param answerNumber
	 */
	public void addAnswer(Question question, int answerNumber);

	/**
	 * Распечатывает результаты теста
	 */
	public void printTestingResult();
	
}
