package ru.akozlovskiy.springdz01.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.domain.Question;

public class StudentTesterServiceImpl implements StudentTesterService {

	private QuestionDAO questionDAO;
	private AnswerHandlerService answerHandler;

	public StudentTesterServiceImpl(QuestionDAO questionDAO, AnswerHandlerService answerHandler) {
		this.questionDAO = questionDAO;
		this.answerHandler = answerHandler;
	}

	public void test() {

		List<Question> questionList;

		try (Scanner scanner = new Scanner(System.in)) {

			questionList = questionDAO.getQuestionList();

			System.out.println("Введите фамилию:");			
			String surname = scanner.nextLine();

			System.out.println("Введите имя:");
			String name = scanner.nextLine();

			for (Question question : questionList) {
				question.printQuestion();

				int answerNumber = getAnswerNumber(scanner);
				answerHandler.addAnswer(question, answerNumber);
			}

			System.out.println("\nРезультаты тестирования студента " + surname + " " + name + ":");
			answerHandler.printTestingResult();

		} catch (IOException e) {
			System.out.println("Ошибка при чтении данных из файла с вопросами");
		}
	}

	private Integer getAnswerNumber(Scanner scanner) {
		while (!scanner.hasNextInt()) {
			System.out.println("Введите номер ответа соответствующий предложенным вариантам!");
			scanner.next();
		}
		return scanner.nextInt();
	}
}