/**
 * 
 */
package com.cirp.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;

/**
 * @author Jincy P Janardhanan
 *
 */
@Controller
@RequestMapping
public class GetMappings {
	
	@GetMapping("/register-college")
	public String registerCollege(Model model) {
		College college = new College();
		model.addAttribute("college", college);
		return "register_college";
	}
	
	@GetMapping("/register-recruiter")
	public String registerRecruiter(Model model) {
		Recruiter recruiter = new Recruiter();
		model.addAttribute("recruiter", recruiter);
		return "register_recruiter";
	}
	
	@GetMapping("/register-alumnus")
	public String registerAlumnus(Model model) {
		Alumnus alumnus = new Alumnus();
		model.addAttribute("alumnus", alumnus);
		return "register_alumnus";
	}
}
