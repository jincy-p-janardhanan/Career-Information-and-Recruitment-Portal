package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
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

import com.cirp.app.model.Application;
import com.cirp.app.model.Awards;
import com.cirp.app.model.ChatChannel;
import com.cirp.app.model.College;
import com.cirp.app.model.Communities;
import com.cirp.app.model.Education;
import com.cirp.app.model.Personalisation;
import com.cirp.app.model.Project;
import com.cirp.app.model.Student;
import com.cirp.app.model.WorkExperience;
import com.cirp.app.repository.ChatChannelRepo;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.FindClass;
import com.cirp.app.service.StringVal;
import com.fasterxml.jackson.annotation.JsonInclude;

@Controller
@RequestMapping(value = { "/student"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentController {
	@Autowired
	private CirpRepository repo;
	
	@Autowired
	private FindClass find;
	
	@Autowired
	ChatChannelRepo chatChannelRepo;

	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {
		
		String username = authentication.getName();
		
		Student student = repo.findById(username);
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
		Personalisation p = student.getPersonalisation();		
		model.addAttribute("personalisation", p);
		
		List<ChatChannel> channels = chatChannelRepo.findByUser1(username);
		channels.addAll(chatChannelRepo.findByUser2(username));
		model.addAttribute("channels", channels);
		model.addAttribute("username", username);
		
		return "student/home_student";

	}
	
	@PostMapping(value = "/apply-job", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String applyJob(@RequestParam String job_id, Application application, Authentication authentication,
			RedirectAttributes redirectAttributes) {
		application.setApplicant_id(authentication.getName());
		String message = repo.applyJob(application, job_id);
		if(message == null) {
			message = "Application success!";
		}
		redirectAttributes.addFlashAttribute("message", message);
		return "redirect:/view?id="+job_id;
	}
		
	@PostMapping(value="/update-personalisation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updatePersonalisation(Personalisation personalisation, RedirectAttributes redirectAttributes, Authentication authentication,
			HttpServletRequest request) {
		String username = authentication.getName();
		Class<?> user_class = find.findClass(username);
		repo.updatePersonalisation(personalisation, username, user_class);
		return "redirect:/student/home";
	}

	@GetMapping("/update-personalisation")
	public String updatePersonalisation(Model model, Authentication authentication) {

		Student student = repo.findById(authentication.getName());
		Personalisation p = student.getPersonalisation();
		if(p == null) {
			p = new Personalisation();
		}
		List<Awards> a = p.getAwards();
		if(a == null) a = new ArrayList<>();
		a.add(new Awards());
		p.setAwards(a);

		List<Communities> c = p.getCommunities();
		if(c == null) c = new ArrayList<>();
		c.add(new Communities());
		p.setCommunities(c);

		List<String> s = p.getSkills();
		if(s == null) s = new ArrayList<>();
		s.add(new String());
		p.setSkills(s);

		List<Education> e = p.getEducation();
		if(e == null) e = new ArrayList<>();
		e.add(new Education());
		p.setEducation(e);

		List<Project> pr = p.getProject();
		if(pr == null) pr = new ArrayList<>();
		pr.add(new Project());
		p.setProject(pr);

		List<WorkExperience> w = p.getWork();
		if(w == null) w = new ArrayList<>();
		w.add(new WorkExperience());
		p.setWork(w);

		model.addAttribute("profile_pic", student.getProfile_pic());
		model.addAttribute("personalisation", p);

		return "student/edit_personalisation";
	}

	@RequestMapping(value = "/update-personalisation", params = { "add-project" })
	public String addProject(final Personalisation personalisation, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		personalisation.getProject().add(new Project());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}

	@RequestMapping(value = "/update-personalisation", params = { "add-work" })
	public String addWork(final Personalisation personalisation, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		personalisation.getWork().add(new WorkExperience());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "add-community" })
	public String addCommunity(final Personalisation personalisation, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		personalisation.getCommunities().add(new Communities());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "add-award" })
	public String addAward(final Personalisation personalisation, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		personalisation.getAwards().add(new Awards());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "add-education" })
	public String addEducation(final Personalisation personalisation, final BindingResult bindingResult, Model model,
			Authentication authentication) {
		personalisation.getEducation().add(new Education());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "remove-project" })
	public String removeProject(final Personalisation personalisation, final BindingResult bindingResult, Model model, final HttpServletRequest req,
			Authentication authentication) {
		final Integer index = Integer.valueOf(req.getParameter("remove-project"));
		personalisation.getProject().remove(index.intValue());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}

	@RequestMapping(value = "/update-personalisation", params = { "remove-work" })
	public String removeWork(final Personalisation personalisation, final BindingResult bindingResult, Model model,final HttpServletRequest req,
			Authentication authentication) {
		final Integer index = Integer.valueOf(req.getParameter("remove-work"));
		personalisation.getWork().remove(index.intValue());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "remove-community" })
	public String removeCommunity(final Personalisation personalisation, final BindingResult bindingResult, Model model, final HttpServletRequest req,
			Authentication authentication) {
		final Integer index = Integer.valueOf(req.getParameter("remove-community"));
		personalisation.getCommunities().remove(index.intValue());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "remove-award" })
	public String removeAward(final Personalisation personalisation, final BindingResult bindingResult, Model model, final HttpServletRequest req,
			Authentication authentication) {
		final Integer index = Integer.valueOf(req.getParameter("remove-award"));
		personalisation.getAwards().remove(index.intValue());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@RequestMapping(value = "/update-personalisation", params = { "remove-education" })
	public String removeEducation(final Personalisation personalisation, final BindingResult bindingResult, Model model, final HttpServletRequest req,
			Authentication authentication) {
		final Integer index = Integer.valueOf(req.getParameter("remove-education"));
		personalisation.getEducation().remove(index.intValue());
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/edit_personalisation";
	}
	
	@GetMapping(value="/job-suggestions" )
	public String jobSuggestions(Model model, Authentication authentication) {
		
		String aggregatefrom;
		if(find.findClass(authentication.getName()) == Student.class) {
			aggregatefrom = "aggregate: 'student',";
		}
		else {
			aggregatefrom = "aggregate: 'alumnus',";
		}
		
		List<Document> job_suggestions = repo.jobSuggestions(authentication.getName(), aggregatefrom);
		model.addAttribute("job_suggestions", job_suggestions);
		Student student = repo.findById(authentication.getName());
		model.addAttribute("profile_pic", student.getProfile_pic());
		return "student/job_suggestions";
	}
}
