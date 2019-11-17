package ru.akozlovskiy.springdz03.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz03.domain.Answer;
import ru.akozlovskiy.springdz03.domain.Question;

@Service
public class AnswerHandlerServiceImpl implements AnswerHandlerService {

	private final LocalizationService localizationService;

	private final List<Answer> answerList = new ArrayList<Answer>();

	public AnswerHandlerServiceImpl(LocalizationService localizationService) {
		this.localizationService = localizationService;
	}

	public void addAnswer(Question question, int answerNumber) {
		answerList.add(new Answer(question, answerNumber));
	}

	public String printTestingResult() {

		StringBuilder strb = new StringBuilder();

		for (Answer answer : answerList) {
			Question question = answer.getQuestion();
			String result = answer.answerIscorrect() ? localizationService.getString("response.ok")
					: localizationService.getString("response.error");

			strb.append(localizationService.getString("question") + question.getQuestionText() + " -> " + result + "\n");

		}
		return strb.toString();
	}
}