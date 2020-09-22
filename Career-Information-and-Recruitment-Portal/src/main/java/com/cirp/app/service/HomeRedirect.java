package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;

public class HomeRedirect {
	
	@Autowired
	private FindClass find;
	
	public String getRedirectUrl(String username) {
		Class<?> user_class = find.findClass(username);
		System.out.println("redirecting  to" + user_class.toString());
		if (user_class == College.class)
			return "redirect:/college/home";
		else if (user_class == Recruiter.class)
			return "redirect:/recruiter/home";
		else if (user_class == Student.class)
			return "redirect:/student/home";
		else if (user_class == Alumnus.class)
			return "redirect:/alumnus/home";
		else
			return "redirect:/error";
	}
	
}
