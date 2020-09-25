package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.FindClass;

@Controller
@RequestMapping("/view")
public class ViewProfilePublic {
	
	@Autowired
	CirpRepository repo;
	
	@Autowired
	FindClass find;
	
	@GetMapping
	public String view(@RequestParam String id) {
		Class<?> user_class = find.findClass(id);
		if(user_class == College.class) {
			return "view/college";
		} else if(user_class == Recruiter.class) {
			return "view/recruiter";
		} else if(user_class == Student.class) {
			return "view/student";
		} else if(user_class == Alumnus.class) {
			return "view/alumnus";
		} else if(user_class == Job.class) {
			return "view/job";
		} else {
			return "redirect:/error";
		}
	}
	
}
