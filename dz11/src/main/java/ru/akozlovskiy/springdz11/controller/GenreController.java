package ru.akozlovskiy.springdz11.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz11.domain.Genre;


//@Controller
public class GenreController {

	/*private final GenreRepository genreRepository;

	public GenreController(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@GetMapping(value = { "/addGenre" })
	public String showAddGenreWindow(Model model) {
		Genre genre = new Genre();
		model.addAttribute("genre", genre);
		return "addGenre";
	}

	@PostMapping(value = { "/addGenre" })
	public String saveGenre(Model model, @ModelAttribute("genre") @Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("genre", genre);
			return "addGenre";
		}
		genreRepository.save(genre);
		return "redirect:/index";
	}*/
}