package com.cirp.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
public class GetMappingsPublic {
	
	@Autowired
	private CirpRepository repo;
	
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
	
	@GetMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
          
        return "redirect:/login?logout";
    }
	
	@GetMapping("/update-password")
	public String resetPassword(@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {
		System.out.println(token);
		User user = repo.findByToken(token);
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", "Invalid Request!");
			return "redirect:/login";
		} else {
			StringVal password = new StringVal();
			model.addAttribute("username", user.getUsername());
			model.addAttribute("token", token);
			model.addAttribute("password", password);
			return "common/reset_password";
		}
	}
	
	@GetMapping("/pending-approval")
	public String pendingApproval(Model model) {
		model.addAttribute("profile_pic", "default_user.png");
		return "common/registration_pending";
	}
}
