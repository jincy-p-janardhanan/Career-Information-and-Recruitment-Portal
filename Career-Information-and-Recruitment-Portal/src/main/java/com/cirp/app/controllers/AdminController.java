package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Admin;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.AcceptReject;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CirpRepository repo;
	
	@Autowired
	private AcceptReject accept_reject;
	
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
		
		model.addAttribute("college_pending", college_pending);
		model.addAttribute("recruiter_pending", recruiter_pending);
		return "admin/server_admin_panel";
	}
	
	@PostMapping(value="/accept-user")
	public String acceptRegistration(@RequestParam String username, Authentication authentication, RedirectAttributes redirectAttributes) {
		accept_reject.acceptRejectRegistration(username, "confirm", authentication.getName());
		return "redirect:/admin/admin-panel";
	}
	
	@PostMapping(value="/reject-user")
	public String rejectRegistration(@RequestParam String username, RedirectAttributes redirectAttributes) {
		
		return "redirect:/admin/admin-panel";
	}
}
