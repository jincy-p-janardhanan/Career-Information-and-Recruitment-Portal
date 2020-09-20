/**
 * 
 */
package com.cirp.app.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Application;
import com.cirp.app.model.Job;
import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 *
 */

@Controller
public class JobControllers {
	
	@Autowired
	private CirpRepository repo;
	
	@PostMapping(value = "/create-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public void createJob(Job job, Authentication authentication, HttpServletRequest request) {
		repo.createJob(job);
	}

	@RequestMapping("/viewJob")
	public void viewJob(@RequestParam ObjectId job_id) {
		repo.viewJob(job_id);
	}

	@RequestMapping("/view-job-applications")
	public void viewJobApplications(@RequestParam ObjectId job_id) {
		repo.viewJobApplications(job_id);
	}

	@RequestMapping("/filtered-search")
	public <T> List<T> Search(@RequestParam String search_text, @RequestParam String filter) {
		return repo.search(search_text, filter);
	}

	@RequestMapping("/applyJob")
	public void applyJob(@RequestBody Application application, @RequestParam ObjectId job_id) {
		repo.applyJob(application, job_id);
	}

	@RequestMapping("/viewAllApplications")
	public List<Application> viewAllApplications(@RequestParam String recruiter_id) {
		return repo.viewAllApplications(recruiter_id);
	}

	@RequestMapping("/recommend")
	public void recommend(@RequestParam ObjectId recommendation_id, @RequestParam String recc_msg) {
		repo.recommend(recommendation_id, recc_msg);

	}

	@RequestMapping("/deleteJob")
	public void deleteJob(Job job) {

		repo.deleteJob(job);

	}

	@RequestMapping("/updateDesc")
	public void updateDesc(@RequestParam String desc, @RequestParam String username) {
		repo.updateProfilePic(desc, username);
	}

	@RequestMapping("/view-profile")
	public <T> T viewProfile(@RequestParam String username) {
		return repo.viewProfile(username);

	}

	
}
