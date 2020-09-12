package com.cirp.app.controllers;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/cirp")
public class CirpController {

	@Autowired
	private CirpRepository repo;

	@RequestMapping("/register_college")
	public void registerCollege(@RequestBody College college) {
		String password = new BCryptPasswordEncoder().encode(college.getPassword());
		college.setPassword(password);
		repo.register(college);
	}

	@RequestMapping("/register_student")
	public void registerStudent(@RequestBody Student student) {
		String password = new BCryptPasswordEncoder().encode(student.getPassword());
		student.setPassword(password);
		repo.register(student);
	}

	@RequestMapping("/register_recruiter")
	public void registerRecruiter(@RequestBody Recruiter recruiter) {
		String password = new BCryptPasswordEncoder().encode(recruiter.getPassword());
		recruiter.setPassword(password);
		repo.register(recruiter);
	}

	@RequestMapping(value = { "/home_college", "/home_recruiter", "/home_alumnus",
			"/home_student" }, method = RequestMethod.POST)
	public void editProfile(@RequestParam String method, @RequestParam String input,
			@RequestParam String username) {
		if (method == "profile-pic") {
			repo.updateProfilePic(input, username);
		} else if (method == "bg-img") {
			repo.updateBgImg(input, username);
		} else if (method == "about") {
			repo.updateDesc(input, username);
		}
	}

	@RequestMapping(value = { "/home_college", "/home_recruiter" }, method = RequestMethod.POST)
	public void updateContact(@RequestBody ContactInfo contact, @RequestParam String username) {
		repo.updateContact(contact, username);
	}

	@RequestMapping(value = { "/home_alumnus", "/home_student" }, method = RequestMethod.POST)
	public void updatePersonalisation(@RequestBody Personalisation personalisation, @RequestParam String username) {
		repo.updatePersonalisation(personalisation, username);
	}

	@RequestMapping("/optout-request")
	public void optoutRequests(@RequestParam String username) {
		repo.optoutRequest(username);
	}

	@RequestMapping("/deactivate-user")
	public void deactivateUser(@RequestParam String username) {
		repo.deleteUser(username);
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

	@RequestMapping(value = { "/", "/index" })
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home");
		return modelAndView;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/home_college", method = RequestMethod.GET)
	public ModelAndView HomeCollege() {
	    ModelAndView modelAndView = new ModelAndView();
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    College college = repo.findById(auth.getName());
	    modelAndView.addObject("currentUser", college);
	    modelAndView.setViewName("home_college");
	    return modelAndView;
	}
}