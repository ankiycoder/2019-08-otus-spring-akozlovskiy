package ru.akozlovskiy.springdz01.service;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import ru.akozlovskiy.springdz01.dao.QuestionDAO;
import ru.akozlovskiy.springdz01.domain.Question;

public class TesterImpl implements Tester {

	private QuestionDAO questionDAO;

	public TesterImpl(QuestionDAO questionDAO) {
		this.questionDAO = questionDAO;
	}

	public void testing() {
		List<Question> questionList;

		try (Scanner scanner = new Scanner(System.in)) {

			questionList = questionDAO.getQuestionList();

			System.out.println("Введите ФИО студена:");

			String fio = scanner.nextLine();

			for (Question question : questionList) {
				System.out.println(question.getQuestionText() + ":");
				String resp = scanner.nextLine();
				question.setResponse(resp);
			}

			System.out.println("Результаты тестирования студента " + fio + ":");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}