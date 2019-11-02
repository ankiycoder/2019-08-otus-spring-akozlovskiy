package ru.akozlovskiy.springdz01.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.domain.Question;

public class StudentTesterServiceImpl implements StudentTesterService {

	private QuestionDAO questionDAO;
	private AnswerHandlerService answerHandler;

	@Autowired
	MessageSource messageSource;

	@Autowired
	LocalizationService localizationService;

	public StudentTesterServiceImpl(QuestionDAO questionDAO, AnswerHandlerService answerHandler) {
		this.questionDAO = questionDAO;
		this.answerHandler = answerHandler;
	}

	public void test() {

		List<Question> questionList;

		try (Scanner scanner = new Scanner(System.in)) {

			questionList = questionDAO.getQuestionList();

			System.out.println(localizationService.getString("enter.surname"));
			String surname = scanner.nextLine();

			System.out.println(localizationService.getString("enter.name"));
			String name = scanner.nextLine();

			for (Question question : questionList) {
				question.printQuestion();

				int answerNumber = getAnswerNumber(scanner);
				answerHandler.addAnswer(question, answerNumber);
			}

			System.out.println("\n" + localizationService.getString("test.result")  + " " + surname + " " + name + ":");
			answerHandler.printTestingResult();

		}
	}

	private Integer getAnswerNumber(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println(localizationService.getString("enter.correct.number"));
			scanner.next();
		}
		return scanner.nextInt();
	}

}