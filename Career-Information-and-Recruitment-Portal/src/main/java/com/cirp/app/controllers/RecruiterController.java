package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Job;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {
	@Autowired
	private CirpRepository repo;

	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {
		Recruiter recruiter = repo.findById(authentication.getName());
		StringVal desc = new StringVal();
		desc.setValue(recruiter.getDesc());
		String bg_img = recruiter.getBg_img();
		if (bg_img == null) {
			bg_img = "default_background.png";
		}

		model.addAttribute("profile_pic", recruiter.getProfile_pic());
		model.addAttribute("bg_img", bg_img);
		model.addAttribute("desc", desc);
		model.addAttribute("name", recruiter.getName().toUpperCase());
		model.addAttribute("location",
				recruiter.getContact().getCity_or_town() + ", " + recruiter.getContact().getCountry());
		model.addAttribute("contact", recruiter.getContact());
		return "recruiter/home_recruiter";
	}

	@GetMapping("/manage-jobs")
	public String mangeJobs(Model model, Authentication authentication) {
		Recruiter recruiter = repo.findById(authentication.getName());
		List<ObjectId> jobids = recruiter.getJobs();
		List<Job> jobs = new ArrayList<Job>();
		for(ObjectId jobid: jobids) {
			jobs.add(repo.findById(jobid.toString()));
		}
		model.addAttribute("profile_pic", recruiter.getProfile_pic());
		model.addAttribute("jobs", jobs);
		return "recruiter/manage_job";
	}
	
	@PostMapping(value = "/create-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String createJob(@Valid Job job, RedirectAttributes redirectAttributes, Authentication authentication) {	
		repo.createJob(job, authentication.getName());
		redirectAttributes.addFlashAttribute("message", "Successfully created new job opening for "+job.getName()+"!");
		return "redirect:/create-job";
	}
	
	@GetMapping("/create-job")
	public String createJob(Model model) {
		model.addAttribute("job", new Job());				
		return "recruiter/create_job";
	}
	
	@PostMapping(value="/delete-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String deleteJob(String job_id, RedirectAttributes redirectAttributes, Authentication authentication) {	
		Job job = repo.findById(job_id);
		repo.deleteJob(job, authentication.getName());
		redirectAttributes.addFlashAttribute("message", "Successfully created new job opening for "+job.getName()+"!");
		return "redirect:/create-job";
	}
	
	@PostMapping(value = "/edit-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String editJob(@Valid Job job, RedirectAttributes redirectAttributes, Authentication authentication) {	
		repo.editJob(job);
		repo.deleteJob(job, authentication.getName());
		redirectAttributes.addFlashAttribute("message", "Successfully created new job opening for "+job.getName()+"!");
		return "redirect:/create-job";
	}
}
