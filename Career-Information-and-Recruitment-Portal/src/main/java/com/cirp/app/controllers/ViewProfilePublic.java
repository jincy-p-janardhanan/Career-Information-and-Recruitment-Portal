package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Admin;
import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.NonAdmin;
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
	public String view(@RequestParam String id, Model model, Authentication authentication) {
		if(repo.findById(authentication.getName()).getClass() != Admin.class) {
			NonAdmin user = repo.findById(authentication.getName()); //logged in user
			model.addAttribute("profile_pic", user.getProfile_pic());
		}
		
		Class<?> user_class = find.findClass(id);
		
		if(user_class == College.class) {
			College college = repo.findById(id);
			String bg_img = college.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("college_profile_pic", college.getProfile_pic());
			model.addAttribute("college_bg_img", bg_img);
			model.addAttribute("college_desc", college.getDesc());
			model.addAttribute("college_name", college.getName().toUpperCase());
			model.addAttribute("college_univ", college.getAffil_univ().toUpperCase());
			model.addAttribute("college_contact", college.getContact());
			return "view/college";
		} else if(user_class == Recruiter.class) {
			
			Recruiter recruiter = repo.findById(id);
			String bg_img = recruiter.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("recruiter_profile_pic", recruiter.getProfile_pic());
			model.addAttribute("recruiter_bg_img", bg_img);
			model.addAttribute("recruiter_desc", recruiter.getDesc());
			model.addAttribute("recruiter_name", recruiter.getName().toUpperCase());
			model.addAttribute("recruiter_location", recruiter.getContact().getCity_or_town()+", "+recruiter.getContact().getCountry());
			model.addAttribute("recruiter_contact", recruiter.getContact());
			
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
