package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.Job;
import com.cirp.app.model.Recruiter;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping("/recruiter")
public class RecruiterController {
	@Autowired
	private CirpRepository repo;
	@Autowired
	ChatChannelRepo chatChannelRepo;

	private static final Logger logger = LoggerFactory.getLogger(RecruiterController.class);

	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {

		String username = authentication.getName();

		Recruiter recruiter = repo.findById(username);
		StringVal desc = new StringVal();
		desc.setValue(recruiter.getDesc());
		String bg_img = recruiter.getBg_img();
		if (bg_img == null) {
			bg_img = "default_background.png";
		}

		model.addAttribute("bg_img", bg_img);
		model.addAttribute("desc", desc);
		model.addAttribute("name", recruiter.getName().toUpperCase());
		model.addAttribute("location",
				recruiter.getContact().getCity_or_town() + ", " + recruiter.getContact().getCountry());
		model.addAttribute("contact", recruiter.getContact());
		model.addAttribute("requests", recruiter.getRecc_req_recvd());

		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		return "recruiter/home_recruiter";
	}

	@GetMapping("/manage-jobs")
	public String manageJobs(Model model, Authentication authentication) {
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		List<String> jobids = recruiter.getJobs();
		List<Job> jobs = new ArrayList<Job>();
		if (jobids != null) {
			for (String jobid : jobids) {
				Job job = repo.findById(jobid);
				jobs.add(job);
			}
		}

		model.addAttribute("requests", recruiter.getRecc_req_recvd());

		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		model.addAttribute("jobs", jobs);
		model.addAttribute("viewjob", new Job());
		return "recruiter/manage_job";
	}
	
	@GetMapping("/delete-job")
	public String deleteJob(@RequestParam("job_id") String job_id, RedirectAttributes redirectAttributes,
			Authentication authentication) {
		Job job = repo.findById(job_id);
		repo.deleteJob(job, authentication.getName());
		redirectAttributes.addFlashAttribute("message", "Deleted your job opening for " + job.getName() + "!");
		return "redirect:/recruiter/manage-jobs";
	}

	@GetMapping("/view-job")
	public String viewJob(String job_id, Authentication authentication, Model model) {
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("profile_pic", recruiter.getProfile_pic());
		Job job = repo.findById(job_id);
		model.addAttribute("job", job);
		
		model.addAttribute("requests", recruiter.getRecc_req_recvd());
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);

		return "recruiter/view_job";
	}

	@GetMapping("/view-applications")
	public String viewAllApplications(@RequestParam(required = false) String job_id, Authentication authentication,
			Model model) {
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("profile_pic", recruiter.getProfile_pic());
		String matchquery;
		if (job_id == null) {
			matchquery = "{$match: { 'recruiter_id': '" + authentication.getName() + "' }},";
			model.addAttribute("title", "All Applications");
		} else {
			matchquery = "{$match: { 'recruiter_id': '" + authentication.getName() + "', '_id': '" + job_id + "' }},";
			Job job = repo.findById(job_id);
			model.addAttribute("title", "Applications for " + job.getName());
		}
		List<Document> applications = repo.viewApplications(matchquery);
		model.addAttribute("applications", applications);
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		return "recruiter/job_applications";
	}

	@GetMapping("/create-job")
	public String createJob(Model model, RedirectAttributes redirectAttributes, Authentication authentication) {
		Job job = new Job();
		job.setQuestions(Arrays.asList("Maybe some task or scenorio you would like to be solved by the candidate..."));
		model.addAttribute("job", job);
		model.addAttribute("action", "create");
		
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("requests", recruiter.getRecc_req_recvd());
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "recruiter/create_job";
	}
	

	@GetMapping("/edit-job")
	public String editJob(@RequestParam("job_id") String job_id, Model model, Authentication authentication) {
		Job job = repo.findById(job_id);
		if (job.getQuestions() == null) {
			job.setQuestions(
					Arrays.asList("Maybe some task or scenorio you would like to be solved by the candidate..."));
		}
		model.addAttribute("job", job);
		model.addAttribute("action", "update");
		
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("requests", recruiter.getRecc_req_recvd());
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "recruiter/create_job";
	}
	
	@RequestMapping(value = {"/create-job", "/edit-job"}, params = { "add-question" })
	public String addQuestion(@RequestParam(name = "_id", required = false) String id, final Job job, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		if(id == null) {
			model.addAttribute("action", "create");			
		}
		else {
			job.set_id(id);
			model.addAttribute("action", "update");
		}
		job.getQuestions().add(new String());

		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("requests", recruiter.getRecc_req_recvd());
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "recruiter/create_job";
	}

	@RequestMapping(value = {"/create-job", "/edit-job"}, params = { "delete-question" })
	public String deleteQuestion(@RequestParam(name = "_id", required = false) String id, final Job job, Model model, final BindingResult bindingResult,
			final HttpServletRequest req, Authentication authentication) {
		
		final Integer question_no = Integer.valueOf(req.getParameter("delete-question"));
		if(id == null) {
			model.addAttribute("action", "create");			
		}
		else {
			job.set_id(id);
			model.addAttribute("action", "update");
		}
		job.getQuestions().remove(question_no.intValue());
		
		String username = authentication.getName();
		Recruiter recruiter = repo.findById(username);
		model.addAttribute("requests", recruiter.getRecc_req_recvd());
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "recruiter/create_job";
	}	
	
	@PostMapping(
			value = { "/create-job", "/edit-job" }, 
			consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
			produces = {
					MediaType.APPLICATION_ATOM_XML_VALUE, 
					MediaType.APPLICATION_JSON_VALUE })
	public String createJob(@Valid Job job, @RequestParam(name = "_id", required = false) String id, RedirectAttributes redirectAttributes, Authentication authentication) {
		Recruiter recruiter = repo.findById(authentication.getName());
		job.setRecruiter_id(recruiter.getUsername());
		if (!recruiter.getProfile_pic().equals("default_recruiter.png")) {
			job.setProfile_pic(recruiter.getProfile_pic());
		} else {
			job.setProfile_pic("default_job.png");
		}
		logger.info("Received job id : " + id);
		if (id == null || id.equals("")) {
			id = ObjectId.get().toString();
			logger.info("generated new job id: " + id);
		}

		job.set_id(id);
		List<String> job_ids = recruiter.getJobs();
		if (job_ids == null) {
			job_ids = new ArrayList<String>();
			job_ids.add(id);
		} else if (!job_ids.contains(id)) {
			job_ids.add(id);
		}

		recruiter.setJobs(job_ids);
		repo.createJob(job, recruiter);
		redirectAttributes.addFlashAttribute("message",
				"Successfully created new job opening for " + job.getName() + "!");
		return "redirect:/recruiter/manage-jobs";
	}
	
}
