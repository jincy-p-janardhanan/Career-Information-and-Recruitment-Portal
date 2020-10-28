package com.cirp.app.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.repository.SearchAll;

@Controller
public class SearchControllers {

	@Autowired
	private CirpRepository repo;

	@Autowired
	private SearchAll searchall;
	
	@Autowired
	ChatChannelRepo chatChannelRepo;

	@GetMapping("/search")
	public String Search(@RequestParam String search_text, Model model, Authentication authentication) {
		List<Document> results = searchall.search(search_text);
		model.addAttribute("results", results);
		String username = authentication.getName();
		NonAdmin user = repo.findById(username);
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		model.addAttribute("profile_pic", user.getProfile_pic());
		return "common/search_results";
	}

	@GetMapping("/filtered-search")
	public String Search(@RequestParam("text") String search_text, @RequestParam("filter") String filter, Model model,
			Authentication authentication) {
		if(filter.equals("all")) {
			model.addAttribute("results", searchall.search(search_text));
		} else {
			model.addAttribute("results", repo.search(search_text, filter));
		}
		String username = authentication.getName();
		NonAdmin user = repo.findById(username);
		model.addAttribute("profile_pic", user.getProfile_pic());
		
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "common/search_results";
	}
}
