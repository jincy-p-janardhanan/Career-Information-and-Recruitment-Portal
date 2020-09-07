package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirp.app.model.*;

import com.cirp.app.repository.CirpRepository;

@CrossOrigin
@RestController
@RequestMapping("/cirp")
public class CirpController {

	@Autowired
	private CirpRepository repo;

	@RequestMapping("/registerCollege")
	public void create(@RequestParam String affil_univ, @RequestParam String landph, @RequestParam String public_email,
			@RequestParam String name, @RequestParam String username, @RequestParam String password,
			@RequestParam Address address, @RequestParam String mobile, @RequestParam String email) {

		College college = new College(affil_univ, landph, public_email, address, password, name, username);
		repo.register(college);
	}

	@RequestMapping("/registerStudent")
	public void registerStudent(@RequestBody Student student) {
		String password = new BCryptPasswordEncoder().encode(student.getPassword());
		student.setPassword(password);
		repo.register(student);
	}

	@RequestMapping("/registerRecruiter")
	public void registerRecruiter(@RequestBody Recruiter recruiter) {
		String password = new BCryptPasswordEncoder().encode(recruiter.getPassword());
		recruiter.setPassword(password);
		repo.register(recruiter);
	}

	@RequestMapping("/update-profile-pic")
	public void updateProfilePic(@RequestParam String profile_pic, @RequestParam String username) {
		repo.updateProfilePic(profile_pic, username);
	}

	@RequestMapping("/update-bg-img")
	public void updateBgImg(@RequestParam String bg_img, @RequestParam String username) {
		repo.updateBgImg(bg_img, username);
	}

	@RequestMapping("/update-about")
	public void updateAbout(@RequestParam String desc, @RequestParam String username) {
		repo.updateDesc(desc, username);
	}

	@RequestMapping("/update-contact")
	public void updateContact(@RequestBody ContactInfo contact, @RequestParam String username) {
		repo.updateContact(contact, username);
	}

	@RequestMapping("/update-personalisation")
	public void updatePersonalisation(@RequestBody Personalisation personalisation, @RequestParam String username) {
		repo.updatePersonalisation(personalisation, username);
	}

	@RequestMapping("/optout-request")
	public void optoutRequests(String username) {
		repo.optoutRequest(username);
	}
	
	@RequestMapping("/deactivate-user")
	public void deactivateUser(String username) {
		repo.deleteUser(username);
	}
	
	
}