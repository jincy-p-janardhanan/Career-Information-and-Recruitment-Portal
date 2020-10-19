package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Job;
import com.cirp.app.model.Student;
import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.FindClass;

@Controller
@RequestMapping("/recruiter/application")
public class ApplicationResumeController {

	@Autowired
	CirpRepository repo;
	FindClass find;
	
	@PostMapping("/hire")
	public String HireCandidate(@RequestParam("applicant_id") String applicant_id, @RequestParam("job_id") String job_id, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		
		Class<?> user_class;
		if(repo.findById(applicant_id) instanceof Alumnus) {
			user_class = Alumnus.class;
		}
		else {
			user_class = Student.class;
		}
		
		repo.hire(applicant_id, job_id, user_class);
		
		User user = repo.findById(applicant_id);
		Job job = repo.findById(job_id);
		redirectAttributes.addFlashAttribute("message", user.getName()+" has been hired for "+job.getName() +"!");
		return "redirect:/recruiter/view-all-applications";
	}
	
	@PostMapping("/reject")
	public String RejectApplication(@RequestParam("applicant_id") String applicant_id, @RequestParam("job_id") String job_id, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		
		Class<?> user_class;
		if(repo.findById(applicant_id) instanceof Alumnus) {
			user_class = Alumnus.class;
		}
		else {
			user_class = Student.class;
		}
		repo.rejectApplication(applicant_id, job_id, user_class);
		
		User user = repo.findById(applicant_id);
		Job job = repo.findById(job_id);
		redirectAttributes.addFlashAttribute("message", user.getName()+"'s application for "+job.getName() +" was rejected.");
		return "redirect:/recruiter/view-all-applications";
	}
}
