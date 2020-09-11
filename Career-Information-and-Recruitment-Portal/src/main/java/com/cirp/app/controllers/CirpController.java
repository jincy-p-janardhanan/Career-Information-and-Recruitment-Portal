package com.cirp.app.controllers;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cirp.app.model.*;

import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 * @author Alka Bhagavaldas
 * @author Aleena Sunny
 * @author Ameena Shirin
 */

@CrossOrigin
@RestController
@RequestMapping("/cirp")
public class CirpController {

	@Autowired
	private CirpRepository repo;

	@RequestMapping("/registerCollege")
	public void registerCollege(@RequestBody College college) {
		String password = new BCryptPasswordEncoder().encode(college.getPassword());
		college.setPassword(password);
		repo.register(college);
	}

	@RequestMapping("/registerStudent")
	public void registerStudent(@RequestBody Student student) {
		String password = new BCryptPasswordEncoder().encode(student.getPassword());
		student.setPassword(password);
		repo.register(student);
	}

	@RequestMapping("/registerRecruiter")
	public void registerRecruiter(@RequestBody Recruiter recruiter) {
		String password = new BCryptPasswordEncoder().encode(recruiter.getPassword());
		recruiter.setPassword(password);
		repo.register(recruiter);
	}

	@RequestMapping("/update-profile-pic")
	public void updateProfilePic(@RequestParam String profile_pic, @RequestParam String username) {
		repo.updateProfilePic(profile_pic, username);
	}

	@RequestMapping("/update-bg-img")
	public void updateBgImg(@RequestParam String bg_img, @RequestParam String username) {
		repo.updateBgImg(bg_img, username);
	}

	@RequestMapping("/update-about")
	public void updateAbout(@RequestParam String desc, @RequestParam String username) {
		repo.updateDesc(desc, username);
	}

	@RequestMapping("/update-contact")
	public void updateContact(@RequestBody ContactInfo contact, @RequestParam String username) {
		repo.updateContact(contact, username);
	}

	@RequestMapping("/update-personalisation")
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
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/filtered-search")
	public <T> List<T> Search(@RequestParam String search_text, @RequestParam String filter) {
		return repo.search(search_text, filter);
	}
	
	@RequestMapping("/applyJob")
	public void applyJob(@RequestBody Application application,@RequestParam ObjectId job_id) {
		repo.applyJob(application,job_id);
	}
	@SuppressWarnings("unchecked")
	@RequestMapping("/viewAllApplications")
	public List<Application> viewAllApplications(@RequestParam String recruiter_id) {
		return repo.viewAllApplications(recruiter_id);
	}
	
	@RequestMapping("/recommend")
	public void recommend(@RequestParam ObjectId reccomendation_id,@RequestParam String recc_msg) {

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
	@RequestMapping
	public <T> T viewProfile(@RequestParam String username) {
		repo.viewProfile(username);
		
	}
	@RequestMapping("/sessionTimeout")
	public void sessionTimeout() {
		repo.sessionTimeout();

	}
	@RequestMapping("/confirmRegistration")
	public void confirmRegistration(@RequestBody User user,@RequestParam Boolean admin ) {
		repo.confirmRegistration(user);
	}
}