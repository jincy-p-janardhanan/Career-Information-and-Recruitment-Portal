/**
 * 
 */
package com.cirp.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;

@Controller
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
	
	@GetMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response) {        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
          
        return "redirect:/login?logout";
    }
}
