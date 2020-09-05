package com.cirp.app.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirp.app.model.College;
import com.cirp.app.repository.CirpRepository;

@RestController
@RequestMapping("/cirp")
public class CirpController {

	@Autowired
	private CirpRepository repo;

	
	@RequestMapping("/create")
	public void create(@RequestParam String affil_univ, @RequestParam String landph, @RequestParam String public_email) {
		repo.register(college);
		College c =repo.insert(affil_univ, landph, public_email);
	}

}