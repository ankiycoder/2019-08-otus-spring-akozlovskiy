package ru.akozlovskiy.springdz01.service;

import java.util.ArrayList;
import java.util.List;

import ru.akozlovskiy.springdz01.domain.Answer;
import ru.akozlovskiy.springdz01.domain.Question;

public class AnswerHandlerServiceImpl implements AnswerHandlerService {
	List<Answer> answerList = new ArrayList<Answer>();

	public void addAnswer(Question question, int answerNumber) {
		answerList.add(new Answer(question, answerNumber));
	}

	public void printTestingResult() {
		for (Answer answer : answerList) {
			Question question = answer.getQuestion();
			String result = answer.answerIscorrect() ? "Правильно" : "Ошибка";
			System.out.println("Вопрос: " + question.getQuestionText() + " -> " + result);
		}
	}
}
