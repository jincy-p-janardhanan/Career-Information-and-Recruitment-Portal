package com.cirp.app.controllers;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Application;
import com.cirp.app.model.College;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping(value = { "/student", "/alumnus" })
public class StudentController {
	@Autowired
	private CirpRepository repo;

	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {
		Student student = repo.findById(authentication.getName());
		College college = repo.findById(student.getCollege_id());
		StringVal desc = new StringVal();
		desc.setValue(student.getDesc());
		String bg_img = student.getBg_img();
		if (bg_img == null) {
			bg_img = "default_background.png";
		}
		model.addAttribute("profile_pic", student.getProfile_pic());
		model.addAttribute("bg_img", bg_img);
		model.addAttribute("desc", desc);
		model.addAttribute("name", student.getName().toUpperCase());
		model.addAttribute("course_and_branch",
				student.getCourse() + ". " + student.getBranch()+"\n"+ college.getName());
		model.addAttribute("personalisation", student.getPersonalisation());
		return "student/home_student";

	}

	@PostMapping(value = "/apply-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String applyJob(@RequestParam ObjectId job_id, Application application, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		
		application.setApplicant_id(authentication.getName());
		repo.applyJob(application, job_id);
		redirectAttributes.addFlashAttribute("message", "Application success!");
		return "common/view_job";
	}
}
