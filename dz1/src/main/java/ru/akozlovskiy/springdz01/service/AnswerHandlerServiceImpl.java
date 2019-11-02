package ru.akozlovskiy.springdz01.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz01.domain.Answer;
import ru.akozlovskiy.springdz01.domain.Question;

public class AnswerHandlerServiceImpl implements AnswerHandlerService {

	@Autowired
	LocalizationService localizationService;

	List<Answer> answerList = new ArrayList<Answer>();

	public void addAnswer(Question question, int answerNumber) {
		answerList.add(new Answer(question, answerNumber));
	}

	public void printTestingResult() {
		for (Answer answer : answerList) {
			Question question = answer.getQuestion();
			String result = answer.answerIscorrect() ? localizationService.getString("response.ok")
					: localizationService.getString("response.error");
			System.out
					.println(localizationService.getString("question") + question.getQuestionText() + " -> " + result);
		}
	}
}