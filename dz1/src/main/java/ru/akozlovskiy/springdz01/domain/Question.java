package ru.akozlovskiy.springdz01.domain;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

/**
 * Вопрос
 * 
 * @author akozlovskiy
 *
 */
public class Question {

	@Autowired
	MessageSource messageSource;

	/**
	 * Текст вопроса
	 */
	private String questionText;

	/**
	 * Список ответов через запятую
	 */
	private String responses;

	/**
	 * Номер правильного ответа
	 */
	private int correctAnswerNumber;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public int getCorrectAnswerNumber() {
		return correctAnswerNumber;
	}

	public void setCorrectAnswerNumber(int correctAnswerNumber) {
		this.correctAnswerNumber = correctAnswerNumber;
	}

	public String getResponses() {
		return responses;
	}

	public void setResponses(String responses) {
		this.responses = responses;
	}		

	public void printQuestion() {

		System.out.println("\n" + questionText + ":");

		String[] slpittedResponses = responses.split(",");

		for (int i = 0; i < slpittedResponses.length; i++) {
			System.out.println((i + 1) + " - " + slpittedResponses[i].trim());
		}
	}
}