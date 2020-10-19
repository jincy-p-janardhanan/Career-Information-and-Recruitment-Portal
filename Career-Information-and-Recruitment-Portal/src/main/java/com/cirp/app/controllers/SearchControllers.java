package com.cirp.app.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.NonAdmin;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.repository.SearchAll;

@Controller
public class SearchControllers {

	@Autowired
	private CirpRepository repo;

	@Autowired
	private SearchAll searchall;

	@GetMapping("/search")
	public String Search(@RequestParam String search_text, Model model, Authentication authentication) {
		List<Document> results = searchall.search(search_text);
		model.addAttribute("results", results);
		NonAdmin user = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", user.getProfile_pic());
		return "common/search_results";
	}

	@GetMapping("/filtered-search")
	public <T> List<T> Search(@RequestParam String search_text, @RequestParam String filter) {
		return repo.search(search_text, filter);
	}
}
