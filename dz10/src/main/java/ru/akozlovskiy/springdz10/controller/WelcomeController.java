package ru.akozlovskiy.springdz10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class WelcomeController {

	@GetMapping(value = { "/", "/index" })
	public String printWelcome(ModelMap model) {
		return "index";
	}

}
