package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.College;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.model.Recommendation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.FindClass;

@Controller
public class RecommendControllers {

	@Autowired
	private CirpRepository repo;

	@Autowired
	FindClass find;
	
	@GetMapping("/request-recommendation")
	public String requestRecommendation(@RequestParam("recommender_id") String recommender_id,
			RedirectAttributes redirectAttributes, Authentication authentication) {
		String username = authentication.getName();
		NonAdmin user = repo.findById(username), recommender = repo.findById(recommender_id);
		Recommendation recommendation = new Recommendation();
		recommendation.setRecommender_id(recommender_id);
		recommendation.setRequester_id(username);
		recommendation.setRequester_name(user.getName());
		recommendation.setRecommender_name(recommender.getName());
		repo.requestRecommendation(recommendation, find.findClass(username),
				find.findClass(recommender_id));
		redirectAttributes.addFlashAttribute("message", "Your reccomendation request has been submitted!");
		return "redirect:/view?id=" + recommender_id;
	}

	@PostMapping(value="/recommend", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String recommend(@RequestBody Recommendation recommendation, Authentication authentication) {
		String username = authentication.getName();
//		logger.info("received recommendation request with parameters - recommender_id:" + recommendation.getRecommender_id()
//				+", requester_id: " + recommendation.getRequester_id() + ", reccommender_name: "+ recommendation.getRecommender_name()
//				+ ", requester_name: " + recommendation.getRequester_name() + ", recc_msg: " + recommendation.getRecc_msg());
		repo.recommend(recommendation, find.findClass(recommendation.getRequester_id()), find.findClass(username));
		if(repo.findById(username) instanceof College) {
			return "redirect:/college/home";
		}
		else if(repo.findById(username) instanceof Recruiter) {
			return "redirect:/recruiter/home";
		}
		else {
			return "redirect:/student/home";
		}		
	}

	@PostMapping(value="/reject-recommend-req", consumes= MediaType.APPLICATION_JSON_VALUE)
	public String rejectRecommendationRequest(@RequestBody Recommendation recommendation, Authentication authentication) {
		String username = authentication.getName();
		repo.rejectRecommendationRequest(recommendation, find.findClass(recommendation.getRequester_id()), find.findClass(username));
		if(repo.findById(username) instanceof College) {
			return "redirect:/college/home";
		}
		else if(repo.findById(username) instanceof Recruiter) {
			return "redirect:/recruiter/home";
		}
		else {
			return "redirect:/student/home";
		}
	}
}
