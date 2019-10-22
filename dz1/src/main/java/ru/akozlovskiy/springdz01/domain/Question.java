package ru.akozlovskiy.springdz01.domain;

public class Question {

	private String questionText;

	private String resp1;
	private String resp2;
	private String resp3;

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

}
