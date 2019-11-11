package ru.akozlovskiy.springdz01.domain;

/**
 * Ответы 
 * @author akozlovskiy
 *
 */
public class Answer {

	
	/**
	 * Вопрос
	 */
	private Question question;
	
	/**
	 * Ответ
	 */
	private int answerNumber;

	public Answer(Question question, int answerNumber) {
		this.question = question;
		this.answerNumber = answerNumber;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}

	public void setAnswerNumber(int answerNumber) {
		this.answerNumber = answerNumber;
	}

	public boolean answerIscorrect() {
		return question.getCorrectAnswerNumber() == answerNumber;
	}
}