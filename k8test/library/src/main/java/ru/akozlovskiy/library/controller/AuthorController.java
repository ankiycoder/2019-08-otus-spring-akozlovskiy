package ru.akozlovskiy.library.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.library.domain.Author;
import ru.akozlovskiy.library.domain.repository.AuthorRepository;

@Controller
public class AuthorController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorController.class);

	private final AuthorRepository authorRepository;

	public AuthorController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@GetMapping(value = { "/addAuthor" })
	public String showAddAuthor(Model model) {
		
		logger.debug("*** Call showAddAuthor");
		
		Author author = new Author();
		model.addAttribute("author", author);
		return "addAuthor";
	}

	@PostMapping(value = { "/addAuthor" })
	public String saveAuthor(Model model, @ModelAttribute("author") @Valid Author author, BindingResult result) {
		
		logger.debug("*** Call saveAuthor");
		
		if (result.hasErrors()) {
			model.addAttribute("author", author);
			return "addAuthor";
		}

		authorRepository.save(author);
		return "redirect:/index";
	}

	@GetMapping("/updateAuthor/{id}")
	public String showUpdateAuthorPage(@PathVariable("id") long id, Model model) {
		
		logger.debug("*** Call showUpdateAuthorPage");
		
		Author author = authorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Author Id:" + id));
		model.addAttribute("author", author);
		return "updateAuthor";
	}

	@PostMapping(value = { "/updateAuthor/{id}" })
	public String updateAuthor(Model model, @PathVariable("id") Long id, @Valid Author author, BindingResult result) {
		
		logger.debug("*** Call updateAuthor");
		
		if (result.hasErrors()) {
			model.addAttribute("author", author);
			return "updateAuthor";
		}
		authorRepository.save(author);
		return "redirect:/index";
	}
}