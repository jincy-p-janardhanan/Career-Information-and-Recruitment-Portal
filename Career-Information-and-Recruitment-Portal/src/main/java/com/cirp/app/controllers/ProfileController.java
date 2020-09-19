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
import org.springframework.web.bind.annotation.RequestBody;

import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Personalisation;
import com.cirp.app.service.EditProfile;

@Controller
public class ProfileController {

	@Autowired
	private EditProfile edit;

	/*References:
	 * https://stackoverflow.com/questions/39866785/i-update-avatar-image-and-display-it-but-the-avatar-does-not-change-in-spring-bo
	 * https://github.com/spring-guides/gs-uploading-files/tree/master/complete
	 * https://spring.io/guides/gs/uploading-files/
	 * 
	 * @PostMapping(value = "/update-profile-pic", consumes =
	 * MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
	 * MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	 * public void updateProfilePic(String image, BindingResult result,
	 * RedirectAttributes redirectAttributes, Authentication authentication) {
	 * repo.updateProfilePic(image, authentication.getName()); }
	 */

	@PostMapping(value = "/update-desc", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updateProfilePic(String desc, Authentication authentication, HttpServletRequest request) {
		edit.updateDesc(desc, authentication.getName());
		return request.getRequestURI().toString();
	}

	@PostMapping(value = "/update-contact", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updateContact(@RequestBody ContactInfo contact, Authentication authentication,
			HttpServletRequest request) {
		edit.updateContact(contact, authentication.getName());
		return request.getRequestURI().toString();
	}

	@PostMapping(value = "/update-personalisation", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String updatePersonalisation(Personalisation personalisation, Authentication authentication,
			HttpServletRequest request) {
		edit.updatePersonalisation(personalisation, authentication.getName());
		return request.getRequestURI().toString();
	}
}