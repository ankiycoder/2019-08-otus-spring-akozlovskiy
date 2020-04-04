package ru.akozlovskiy.springdz11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ru.akozlovskiy.springdz11.exception.DaoException;

@Controller
public class WelcomeController {

	@GetMapping(value = { "/", "/index" })
	public String index(Model model) throws DaoException {
		return "index";
	}

}
