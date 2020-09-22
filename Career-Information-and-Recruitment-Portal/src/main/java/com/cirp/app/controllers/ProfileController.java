/**
 * This class defines all the view controllers used.
 */

package com.cirp.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cirp.app.model.Alumnus;
import com.cirp.app.model.College;
import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Personalisation;
import com.cirp.app.model.Recruiter;
import com.cirp.app.model.Student;
import com.cirp.app.service.EditProfile;
import com.cirp.app.service.FindClass;
import com.cirp.app.service.StringVal;

@Controller
@RequestMapping("/common")
public class ProfileController {

	@Autowired
	private EditProfile edit;
	
	@Autowired
	private FindClass find;

	@PostMapping(value = "/update-desc", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updateProfilePic(StringVal desc, Authentication authentication) {
		edit.updateDesc(desc.getValue(), authentication.getName());
		return getRedirectUrl(authentication.getName());
	}

	@PostMapping(value = "/update-contact", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updateContact(ContactInfo contact, Authentication authentication, HttpServletRequest request) {
		edit.updateContact(contact, authentication.getName());
		return getRedirectUrl(authentication.getName());
	}

	@PostMapping(value = "/update-personalisation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updatePersonalisation(Personalisation personalisation, Authentication authentication,
			HttpServletRequest request) {
		return getRedirectUrl(authentication.getName());
	}
	
	public String getRedirectUrl(String username) {
		Class<?> user_class = find.findClass(username);
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