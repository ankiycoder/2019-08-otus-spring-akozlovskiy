package ru.akozlovskiy.springdz03.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

@Service
public class ConsoleServiceImpl implements ConsoleService {

	private final LocalizationService localizationService;

	private final Scanner scanner;

	public ConsoleServiceImpl(LocalizationService localizationService) {
		this.localizationService = localizationService;
		scanner = new Scanner(System.in);
	}

	@Override
	public int getAnswerNumber() {
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
