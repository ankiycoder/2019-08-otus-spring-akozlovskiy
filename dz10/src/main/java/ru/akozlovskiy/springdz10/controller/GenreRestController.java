package ru.akozlovskiy.springdz10.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.akozlovskiy.springdz10.domain.Genre;
import ru.akozlovskiy.springdz10.domain.repository.GenreRepository;

@RestController
public class GenreRestController {

	private final GenreRepository genreRepository;

	public GenreRestController(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}



	@PostMapping(value = { "/genre/update" })
	public String updateGenre(Model model, @PathVariable("id") long id, @Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("genre", genre);
			return "updateGenre";
		}
		genreRepository.save(genre);
		return "redirect:/index";
	}
}