package com.cirp.app.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Student;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping("/college")
public class CollegeController {
	@Autowired
	private CirpRepository repo;
	
	@GetMapping("/home")
	public String profile(Model model, Authentication authentication) {
		String username = authentication.getName();
		College college = repo.findById(username);
		StringVal desc = new StringVal();
		desc.setValue(college.getDesc());
		String bg_img = college.getBg_img();
		
		if(bg_img == null) {
			bg_img = "default_background.png";
		}
		
		model.addAttribute("username", username);
		model.addAttribute("bg_img", bg_img);
		model.addAttribute("desc", desc);
		model.addAttribute("name", college.getName().toUpperCase());
		model.addAttribute("univ", college.getAffil_univ().toUpperCase());
		model.addAttribute("contact", college.getContact());
		model.addAttribute("requests", college.getRecc_req_recvd());
		return "college/home_college";		
	}
	
	@GetMapping("/admin-panel")
	public String home(Model model, Authentication authentication) {
		String username = authentication.getName();
		College college = repo.findById(username);
		List<Alumnus> alumni_pending = new ArrayList<Alumnus>();
		if(college.getAlumni_pending() !=null) {
			for(String alumnus : college.getAlumni_pending()) {
				alumni_pending.add(repo.findById(alumnus));
			}
		}
		model.addAttribute("username", username);

		model.addAttribute("alumni_pending", alumni_pending);
		model.addAttribute("student", new Student());
		return "college/college_admin_panel";
	}
	
	@PostMapping(value="/update-student", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updateStudent(Student student, RedirectAttributes redirectAttributes) {
		repo.updateStudent(student);
		redirectAttributes.addFlashAttribute("message","Student details updated successfully!");
		return "redirect:/college/admin-panel";
	}
}
