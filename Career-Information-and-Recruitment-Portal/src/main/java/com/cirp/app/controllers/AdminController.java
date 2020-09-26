package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.cirp.app.model.Admin;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.CirpRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CirpRepository repo;
	
	@GetMapping("/admin-panel")
	public String home(Model model, Authentication authentication) {
		Admin admin = repo.findById(authentication.getName());
		
		List<College> college_pending = new ArrayList<College>();
		if(admin.getCollege_pending()!=null) {
			for(String college : admin.getCollege_pending())
				college_pending.add(repo.findById(college));
		}
		
		List<Recruiter> recruiter_pending = new ArrayList<Recruiter>();
		if(admin.getRecruiter_pending() != null) {
			for(String recruiter : admin.getRecruiter_pending())
				recruiter_pending.add(repo.findById(recruiter));
		}
		
		model.addAttribute("profile_pic", "default_user.png");
		model.addAttribute("college_pending", college_pending);
		model.addAttribute("recruiter_pending", recruiter_pending);
		return "admin/server_admin_panel";
	}
}
