package com.cirp.app.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.repository.CirpRepository;

public class RecommendControllers {
	
	@Autowired
	private CirpRepository repo;
	
	@RequestMapping("/request-recommendation")
	public void requestRecommendation(String recommender_id, Authentication authentication) {
		repo.requestRecommendation(authentication.getName(), recommender_id);

	}

	@RequestMapping("/recommend")
	public void recommend(@RequestParam ObjectId recommendation_id, @RequestParam String recc_msg) {
		repo.recommend(recommendation_id, recc_msg);

	}
}
