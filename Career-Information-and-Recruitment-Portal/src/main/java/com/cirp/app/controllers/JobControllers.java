package com.cirp.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Application;
import com.cirp.app.model.Job;
import com.cirp.app.repository.CirpRepository;

@Controller
public class JobControllers {
	
	@Autowired
	private CirpRepository repo;
	
	@PostMapping(value = "/create-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public void createJob(Job job, Authentication authentication, HttpServletRequest request) {
		repo.createJob(job, authentication.getName());
	}

	@RequestMapping(value = "/view-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public void viewJob(Model model, ObjectId job_id) {
		repo.viewJob(job_id);
	}
	
	@RequestMapping("/deleteJob")
	public void deleteJob(Job job) {
		repo.deleteJob(job);
	}

	@RequestMapping("/view-job-applications")
	public void viewJobApplications(@RequestParam ObjectId job_id) {
		repo.viewJobApplications(job_id);
	}

	@RequestMapping("/applyJob")
	public void applyJob(Application application,  ObjectId job_id) {
		repo.applyJob(application, job_id);
	}

	@RequestMapping("/viewAllApplications")
	public List<Application> viewAllApplications(@RequestParam String recruiter_id) {
		return repo.viewAllApplications(recruiter_id);
	}	
}
