package com.cirp.app.controllers;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
@RequestMapping("/")
public class CirpController implements ErrorController {

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
	public void editProfile(@RequestParam String method, @RequestParam String input, @RequestParam String username) {
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
	
	@RequestMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username) {
		repo.rejectRegistration(username);
	}	

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {

		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

		if (status != null) {
			int statusCode = Integer.parseInt(status.toString());

			if (statusCode == HttpStatus.NOT_FOUND.value()) {
				return "404";
			} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				return "500";
			} else if (statusCode == HttpStatus.FORBIDDEN.value()) {
				return "403";
			}
		}

		return "error";
	}
}