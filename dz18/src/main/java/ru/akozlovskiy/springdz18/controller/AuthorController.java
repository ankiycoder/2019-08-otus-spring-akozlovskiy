package ru.akozlovskiy.springdz18.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz18.domain.Author;
import ru.akozlovskiy.springdz18.domain.service.AuthorService;

@Controller
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping(value = { "/addAuthor" })
	public String showAddAuthorWindow(Model model) {
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}

	@PostMapping(value = { "/addAuthor" })
	public String saveAuthor(Model model, @ModelAttribute("author") @Valid Author author, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("author", author);
			return "addAuthor";
		}

		authorService.save(author);
		return "redirect:/index";
	}

}