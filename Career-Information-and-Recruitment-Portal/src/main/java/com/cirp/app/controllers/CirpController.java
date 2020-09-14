/**
 * This class defines all the view controllers used.
 */

package com.cirp.app.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cirp.app.model.*;
import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 * @author Alka Bhagavaldas K
 * @author Aleena Sunny
 * @author Ameena Shirin
 */

@CrossOrigin
@RestController
@RequestMapping("/")
public class CirpController {

	@Autowired
	private CirpRepository repo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@RequestMapping("/register-college")
	public void registerCollege(@RequestBody(required = false) College college) {
		if(college != null) {
			String password = passwordEncoder.encode(college.getPassword());
			college.setPassword(password);
			repo.register(college);
		}
	}

	@RequestMapping("/add-student")
	public void registerStudent(@RequestBody Student student) {
		String password = passwordEncoder.encode(student.getPassword());
		student.setPassword(password);
		repo.register(student);
	}

	@RequestMapping("/register-recruiter")
	public void registerRecruiter(@RequestBody Recruiter recruiter) {
		String password = passwordEncoder.encode(recruiter.getPassword());
		recruiter.setPassword(password);
		repo.register(recruiter);
	}

	@RequestMapping(value = { "/college/home", "/recruiter/home", "/alumnus/home",
			"/student/home" }, method = RequestMethod.POST)
	public void editProfile(@RequestParam String method, @RequestParam String input, @RequestParam String username) {
		if (method == "profile-pic") {
			repo.updateProfilePic(input, username);
		} else if (method == "bg-img") {
			repo.updateBgImg(input, username);
		} else if (method == "about") {
			repo.updateDesc(input, username);
		}
	}

	@RequestMapping(value = { "/college/home", "/recruiter/home" }, method = RequestMethod.POST)
	public void updateContact(@RequestBody ContactInfo contact, @RequestParam String username) {
		repo.updateContact(contact, username);
	}

	@RequestMapping(value = { "/alumnus/home", "/student/home" }, method = RequestMethod.POST)
	public void updatePersonalisation(@RequestBody Personalisation personalisation, @RequestParam String username) {
		repo.updatePersonalisation(personalisation, username);
	}

	@RequestMapping("/createJob")
	public void createJob(@RequestBody Job job) {
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

	@RequestMapping("/confirmRegistration")
	public void confirmRegistration(@RequestParam String username) {
		repo.confirmRegistration(username);
	}

	@RequestMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username) {
		repo.rejectRegistration(username);
	}
	
	@RequestMapping("/optout-request")
	public void optoutRequests(@RequestParam String username) {
		repo.optoutRequest(username);
	}

	@RequestMapping("/deactivate-user")
	public void deactivateUser(@RequestParam String username) {
		repo.deleteUser(username);
	}
}