package ru.akozlovskiy.springdz01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.domain.Question;

public class StudentTesterServiceImpl implements StudentTesterService {

	private QuestionDAO questionDAO;

	private AnswerHandlerService answerHandlerService;

	private ConsoleService consoleService;

	private String surname;

	private String name;

	@Autowired
	private LocalizationServiceImpl localizationService;

	public StudentTesterServiceImpl(QuestionDAO questionDAO, AnswerHandlerService answerHandler,
			ConsoleService consoleService) {
		this.questionDAO = questionDAO;
		this.answerHandlerService = answerHandler;
		this.consoleService = consoleService;
	}

	public void test() {

		List<Question> questionList = questionDAO.getQuestionList();

		readFio();

		for (Question question : questionList) {
			question.printQuestion();

			int answerNumber = consoleService.getAnswerNumber();

			answerHandlerService.addAnswer(question, answerNumber);
		}

		System.out.println("\n" + localizationService.getString("test.result") + " " + surname + " " + name + ":");
		answerHandlerService.printTestingResult();
	}

	private void readFio() {
		System.out.println(localizationService.getString("enter.surname"));
		surname = consoleService.getString();

		System.out.println(localizationService.getString("enter.name"));
		name = consoleService.getString();
	}
}