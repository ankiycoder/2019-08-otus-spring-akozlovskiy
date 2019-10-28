package ru.akozlovskiy.springdz01.domain;

/**
 * Вопрос
 * 
 * @author akozlovskiy
 *
 */
public class Question {

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

	private String response;

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
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