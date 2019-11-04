package ru.akozlovskiy.springdz01.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;

public class ConsoleServiceImpl implements ConsoleService {

	@Autowired
	LocalizationService localizationService;

	private Scanner scanner;

	public ConsoleServiceImpl() {
		scanner = new Scanner(System.in);
	}

	@Override
	public Integer getAnswerNumber() {
		while (!scanner.hasNextInt()) {
			System.out.println(localizationService.getString("enter.correct.number"));
			scanner.next();
		}
		return scanner.nextInt();
	}

	@Override
	public String getString() {
		return scanner.next();
	}
}
