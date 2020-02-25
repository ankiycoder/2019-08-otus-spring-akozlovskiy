package ru.akozlovskiy.springdz09.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.akozlovskiy.springdz09.domain.Genre;
import ru.akozlovskiy.springdz09.domain.repository.GenreRepository;

@Controller
public class GenreController {

	private final GenreRepository genreRepository;

	public GenreController(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@GetMapping(value = { "/addGenre" })
	public String showAddGenre(Model model) {
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
	}

	@GetMapping("/updateGenre/{id}")
	public String showUpdateGenrePage(@PathVariable("id") long id, Model model) {
		Genre genre = genreRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Genre Id:" + id));
		model.addAttribute("genre", genre);
		return "updateGenre";
	}

	@PostMapping(value = { "/updateGenre/{id}" })
	public String updateGenre(Model model, @PathVariable("id") long id, @Valid Genre genre, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("genre", genre);
			return "updateGenre";
		}
		genreRepository.save(genre);
		return "redirect:/index";
	}
}