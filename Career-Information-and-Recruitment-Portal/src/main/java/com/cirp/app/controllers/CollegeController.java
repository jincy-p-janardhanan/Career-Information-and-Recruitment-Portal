package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cirp.app.model.College;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping("/college")
public class CollegeController {
	@Autowired
	private CirpRepository repo;
	
	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {
		College college = repo.findById(authentication.getName());
		StringVal desc = new StringVal();
		desc.setValue(college.getDesc());
		String profile_pic = college.getProfile_pic();
		String bg_img = college.getBg_img();
		
		if(desc.getValue() == "")
			desc.setValue("Add your profile description here... ");
		if(bg_img == null) {
			bg_img = "default_background.png";
		}
		
		model.addAttribute("profile_pic", profile_pic);
		model.addAttribute("bg_img", bg_img);
		model.addAttribute("desc", desc);
		model.addAttribute("name", college.getName().toUpperCase());
		model.addAttribute("univ", college.getAffil_univ().toUpperCase());
		model.addAttribute("contact", college.getContact());
		return "college/home_college";		
	}
	
	@GetMapping("/admin-panel")
	public String home(Model model, Authentication authentication) {
		College college = repo.findById(authentication.getName());
		
		return "college/college_admin_panel";
	}
	
	
}
