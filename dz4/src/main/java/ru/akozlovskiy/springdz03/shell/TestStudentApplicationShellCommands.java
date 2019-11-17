package ru.akozlovskiy.springdz03.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import ru.akozlovskiy.springdz03.service.StudentTesterService;

@ShellComponent
public class TestStudentApplicationShellCommands {

	@Autowired
	private StudentTesterService studentTesterService;

	@ShellMethod(value = "Begin student test", key = { "s", "start" })
	public String start() {
		studentTesterService.test();
		return "Тестирование закончено";
	}
}
