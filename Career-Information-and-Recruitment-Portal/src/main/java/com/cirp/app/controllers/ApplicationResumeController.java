package com.cirp.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Application;
import com.cirp.app.model.Job;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;

@Controller
public class ApplicationResumeController {

	@Autowired
	CirpRepository repo;

	@Secured({ "ROLE_RECRUITER", "ROLE_STUDENT", "ROLE_ALUMNUS" })
	@GetMapping("/application")
	public String viewApplication(String application_id, ObjectId job_id, Authentication authentication,
			HttpServletRequest request, Model model) {

		Job job = repo.findById(job_id.toString());
		NonAdmin user = repo.findById(authentication.getName());
		model.addAttribute("questions", job.getQuestions());
		List<Application> applicants = job.getApplicants();
		int found = 0;
		for (Application application : applicants) {
			if (application.getApplicant_id() == application_id) {
				model.addAttribute("application", application);
				found = 1;
			}
		}
		if (found == 0 || ((request.isUserInRole("ROLE_STUDENT") || request.isUserInRole("ROLE_ALUMNUS"))
				&& !authentication.getName().equals(application_id))) {
			return "/error";
		}

		model.addAttribute("profile_pic", user.getProfile_pic());

		return "common/view_application";
	}

	@Secured({ "ROLE_RECRUITER", "ROLE_STUDENT", "ROLE_ALUMNUS" })
	@GetMapping("/resume")
	public String viewResume(@RequestParam String username, Authentication authentication, HttpServletRequest request,
			Model model) {

		Student student = repo.findById(username);
		NonAdmin user = repo.findById(authentication.getName());

		model.addAttribute("name", student.getName());
		model.addAttribute("personalisation", student.getPersonalisation());
		model.addAttribute("address", student.getAddress());
		model.addAttribute("mobile", student.getMobile());
		model.addAttribute("email", student.getEmail());
		model.addAttribute("desc", student.getDesc());

		model.addAttribute("profile_pic", user.getProfile_pic());

		if ((!authentication.getName().equals(username)) && 
				(request.isUserInRole("ROLE_STUDENT") || request.isUserInRole("ROLE_ALUMNUS")) ){

			return "/error";
		}

		return "common/view_resume";
	}

}
