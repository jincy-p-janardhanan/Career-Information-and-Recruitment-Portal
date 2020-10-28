package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.model.Admin;
import com.cirp.app.model.Alumnus;
import com.cirp.app.model.Application;
import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.College;
import com.cirp.app.model.Job;
import com.cirp.app.model.NonAdmin;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.FindClass;

@Controller
@RequestMapping("/view")
public class ViewProfilePublic {
	
	@Autowired
	CirpRepository repo;
	
	@Autowired
	FindClass find;
	
	@Autowired
	ChatChannelRepo chatChannelRepo;
	
	@GetMapping
	public String view(@RequestParam String id, Model model, Authentication authentication) {
		String username = authentication.getName();
		if(repo.findById(username).getClass() != Admin.class) {
			NonAdmin user = repo.findById(authentication.getName()); //logged in user
			model.addAttribute("profile_pic", user.getProfile_pic());
		}
		
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		model.addAttribute("id", id);
		Class<?> user_class = find.findClass(id);
		
		if(user_class == College.class) {
			College college = repo.findById(id);
			String bg_img = college.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("college_profile_pic", college.getProfile_pic());
			model.addAttribute("college_bg_img", bg_img);
			model.addAttribute("college_desc", college.getDesc());
			model.addAttribute("college_name", college.getName().toUpperCase());
			model.addAttribute("college_univ", college.getAffil_univ().toUpperCase());
			model.addAttribute("college_contact", college.getContact());
			return "view/college";
		} else if(user_class == Recruiter.class) {
			
			Recruiter recruiter = repo.findById(id);
			String bg_img = recruiter.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("recruiter_profile_pic", recruiter.getProfile_pic());
			model.addAttribute("recruiter_bg_img", bg_img);
			model.addAttribute("recruiter_desc", recruiter.getDesc());
			model.addAttribute("recruiter_name", recruiter.getName().toUpperCase());
			model.addAttribute("recruiter_location", recruiter.getContact().getCity_or_town()+", "+recruiter.getContact().getCountry());
			model.addAttribute("recruiter_contact", recruiter.getContact());
			
			return "view/recruiter";
		} else if(user_class == Student.class) {
			Student student = repo.findById(id);
			String bg_img = student.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("student_profile_pic", student.getProfile_pic());
			model.addAttribute("student_bg_img", bg_img);
			model.addAttribute("student_desc", student.getDesc());
			model.addAttribute("student_name", student.getName().toUpperCase());
			College college = repo.findById(student.getCollege_id());
			model.addAttribute("student_course", student.getCourse() + ", " + student.getBranch()+"\n"+college.getName());
			model.addAttribute("personalisation", student.getPersonalisation());
			model.addAttribute("isAlumnus", false);
			model.addAttribute("recommendations", student.getRecommendations());
			return "view/student";
		} else if(user_class == Alumnus.class) {
			Alumnus student = repo.findById(id);
			String bg_img = student.getBg_img();
			if(bg_img == null) {
				bg_img = "default_background.png";
			}
			model.addAttribute("student_profile_pic", student.getProfile_pic());
			model.addAttribute("student_bg_img", bg_img);
			model.addAttribute("student_desc", student.getDesc());
			model.addAttribute("student_name", student.getName().toUpperCase());
			College college = repo.findById(student.getCollege_id());
			model.addAttribute("student_course", student.getCourse() + ", " + student.getBranch()+"\n"+college.getName());
			model.addAttribute("personalisation", student.getPersonalisation());
			model.addAttribute("isAlumnus", true);
			model.addAttribute("recommendations", student.getRecommendations());
			return "view/student";
		} else if(user_class == Job.class) {
			Job job = repo.findById(id);
			job.setApplicants(null);
			model.addAttribute("job", job);
			Application application = new Application();
			List<String> answers = new ArrayList<String>();
			if(job.getQuestions()!= null) {
				for(@SuppressWarnings("unused") String question: job.getQuestions())
					answers.add(new String());
			}
			Recruiter recruiter = repo.findById(job.getRecruiter_id());
			application.setAnswers(answers);
			model.addAttribute("application", application);
			model.addAttribute("recruiter_name", recruiter.getName());
			model.addAttribute("recruiter_desc", recruiter.getDesc());
			return "view/job";
		} else {
			return "redirect:/error";
		}
	}
	
}
