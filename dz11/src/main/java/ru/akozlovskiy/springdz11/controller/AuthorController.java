package ru.akozlovskiy.springdz11.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import reactor.core.publisher.Mono;
import ru.akozlovskiy.springdz11.domain.Author;
import ru.akozlovskiy.springdz11.domain.repository.AuthorRepository;

@Controller
public class AuthorController {
	
	private static Logger logger = LoggerFactory.getLogger(AuthorController.class);

	private final AuthorRepository authorRepository;

	public AuthorController(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
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
		logger.debug("***Call saveAuthor for authorID = {}", author);		
		Mono<@Valid Author> authorSaveMono = authorRepository.save(author);		
		authorSaveMono.subscribe();				
		return "redirect:/index";
	}

}