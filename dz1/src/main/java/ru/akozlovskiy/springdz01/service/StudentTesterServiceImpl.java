package ru.akozlovskiy.springdz01.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.domain.Question;

@Service
public class StudentTesterServiceImpl implements StudentTesterService {

	private final QuestionDAO questionDAO;

	private final AnswerHandlerService answerHandlerService;

	private final ConsoleService consoleService;

	private String surname;

	private String name;

	private final LocalizationService localizationService;

	public StudentTesterServiceImpl(QuestionDAO questionDAO, AnswerHandlerService answerHandler,
			ConsoleService consoleService, LocalizationService localizationService) {
		this.questionDAO = questionDAO;
		this.answerHandlerService = answerHandler;
		this.consoleService = consoleService;
		this.localizationService = localizationService;
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