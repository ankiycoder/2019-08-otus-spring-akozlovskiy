package ru.akozlovskiy.springdz05.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class LibraryApplicationShellCommands {

	@Autowired
	// private StudentTesterService studentTesterService;

	@ShellMethod(value = "Begin student test", key = { "s", "start" })
	public String start() {
		// studentTesterService.test();
		return "Тестирование закончено";
	}
}
