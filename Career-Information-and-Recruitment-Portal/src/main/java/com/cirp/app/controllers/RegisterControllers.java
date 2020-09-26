package com.cirp.app.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.RegisterMessage;

@Controller
public class RegisterControllers {
	
	@Autowired
	private CirpRepository repo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping(value = "/register-college", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String registerCollege(@Valid College college, RedirectAttributes redirectAttributes) {
		
		User user_exists = repo.findById(college.getUsername());
		User email_exists = repo.findByEmail(college.getEmail());
		
		if(user_exists == null && email_exists == null) {
			String password = passwordEncoder.encode(college.getPassword());
			college.setPassword(password);
			repo.register(college);
		}
		RegisterMessage.registerMessage(redirectAttributes, user_exists, email_exists, "");
		return "redirect:/register-college";
	}
	
	@PostMapping(value = "/register-recruiter", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String registerRecruiter(@Valid Recruiter recruiter, RedirectAttributes redirectAttributes) {
		
		User user_exists = repo.findById(recruiter.getUsername());
		User email_exists = repo.findByEmail(recruiter.getEmail());
		
		if (user_exists == null && email_exists == null) {
			String password = passwordEncoder.encode(recruiter.getPassword());
			recruiter.setPassword(password);
			repo.register(recruiter);
		}
		
		RegisterMessage.registerMessage(redirectAttributes, user_exists, email_exists, "");
		return "redirect:/register-recruiter";
	}
	
	@PostMapping(value = "/register-alumnus", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String registerAlumnus(@Valid Alumnus alumnus, RedirectAttributes redirectAttributes) {
		
		User user_exists = repo.findById(alumnus.getUsername());
		User email_exists = repo.findByEmail(alumnus.getEmail());
		
		if (user_exists == null && email_exists == null) {
			String password = passwordEncoder.encode(alumnus.getPassword());
			alumnus.setPassword(password);
			repo.register(alumnus);
		}
		
		RegisterMessage.registerMessage(redirectAttributes, user_exists, email_exists, "");
		return "redirect:/register-alumnus";
	}
	
	@PostMapping(value = "/register-student", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String registerStudent(@Valid Student student, RedirectAttributes redirectAttributes, Authentication authentication) {
		
		User user_exists = repo.findById(student.getUsername());
		User email_exists = repo.findByEmail(student.getEmail());
		
		if (user_exists == null && email_exists == null) {
			String password = passwordEncoder.encode(student.getPassword());
			student.setPassword(password);
			student.setCollege_id(authentication.getName());
			repo.register(student);
		}
		
		RegisterMessage.registerMessage(redirectAttributes, user_exists, email_exists, "student");
		return "redirect:/college/admin-panel";
	}
}
