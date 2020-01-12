package ru.akozlovskiy.springdz09.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ru.akozlovskiy.springdz09.domain.Book;

@Controller
public class MainController {

	private static List<Book> books = new ArrayList<Book>();

	static {
		Book book1 = new Book();
		book1.setTitle("title");

		Book book2 = new Book();
		book2.setTitle("title2");

		books.add(book1);
		books.add(book2);
	}

	// ​​​​​​​
	// Вводится (inject) из application.properties.
	@Value("${welcome.message}")
	private String message;

	@Value("${error.message}")
	private String errorMessage;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		model.addAttribute("message", message);

		return "index";
	}

	@RequestMapping(value = { "/bookList" }, method = RequestMethod.GET)
	public String personList(Model model) {

		model.addAttribute("books", books);

		return "bookList";
	}

	@RequestMapping(value = { "/addBook" }, method = RequestMethod.GET)
	public String showAddPersonPage(Model model) {

		PersonForm personForm = new PersonForm();
		model.addAttribute("personForm", personForm);

		return "addBook";
	}

	@RequestMapping(value = { "/addBook" }, method = RequestMethod.POST)
	public String savePerson(Model model, //
			@ModelAttribute("personForm") PersonForm personForm) {

		String firstName = personForm.getFirstName();

		if (firstName != null) {
			Book book = new Book();
			book.setTitle(firstName);

			books.add(book);

			return "redirect:/bookList";
		}

		model.addAttribute("errorMessage", errorMessage);
		return "addBook";
	}

}