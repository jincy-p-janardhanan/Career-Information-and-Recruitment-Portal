package com.cirp.app.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.AcceptReject;
import com.cirp.app.service.FindClass;
import com.cirp.app.service.OptoutRequest;
import com.cirp.app.service.ResetPassword;
import com.cirp.app.service.StringVal;

@Controller
public class UserControllers {

	@Autowired
	private CirpRepository repo;

	@Autowired
	private OptoutRequest optout;

	@Autowired
	private AcceptReject acceptReject;

	@Autowired
	private ResetPassword reset;

	@Autowired
	FindClass find = new FindClass();

	@GetMapping("/confirmRegistration")
	public void confirmRegistration(@RequestParam String username, Authentication authentication) {
		acceptReject.acceptRejectRegistration(username, "confirm", authentication.getName());
	}

	@GetMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username, Authentication authentication) {
		acceptReject.acceptRejectRegistration(username, "reject", authentication.getName());
	}

	@GetMapping("/optout-request")
	public void optoutRequests(@RequestParam String username) {
		optout.optoutRequest(username);
	}

	@GetMapping("/deactivate-user")
	public void deactivateUser(@RequestParam String username) {
		repo.deleteUser(username);
	}

	@GetMapping("/reset-password-request")
	public String resetPassword(Model model) {
		StringVal username_or_email = new StringVal();
		model.addAttribute("username_or_email", username_or_email);
		return "reset_password_request";
	}

	@PostMapping(value = "/reset-password-request", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String resetPassword(StringVal username_or_email, HttpServletRequest request,
			RedirectAttributes redirectAttributes, HttpSession session) {
		if (reset.resetPasswordRequest(username_or_email.getValue(), request) == "Invalid username or email!") {
			redirectAttributes.addFlashAttribute("message", "Invalid username or email!");
		} else {
			redirectAttributes.addFlashAttribute("message", "A link to reset your password has been sent to your email.");
		}
		return "redirect:/login";
	}

	@PostMapping(value = "/update-password", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String resetPassword(@RequestParam("token") String token, @RequestParam("username") String username,
			StringVal password, RedirectAttributes redirectAttributes) {
		User user = repo.findByToken(token);
		System.out.println(token);
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", "Invalid Request!");
			return "redirect:/login";
		} else {
			reset.resetPassword(username, password.getValue());
			redirectAttributes.addFlashAttribute("message", "You've successfully reset your password!");
			return "redirect:/login";
		}
	}
	
	@PostMapping(value="/accept-user")
	public String acceptRegistration(@RequestParam String username, Authentication authentication, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		acceptReject.acceptRejectRegistration(username, "confirm", authentication.getName());
		if(request.isUserInRole("ROLE_ADMIN"))
			return "redirect:/admin/admin-panel";
		else if(request.isUserInRole("ROLE_COLLEGE"))
			return "redirect:/college/admin-panel";
		else
			return "/error";
	}
	
	@PostMapping(value="/reject-user")
	public String rejectRegistration(@RequestParam String username, Authentication authentication, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		acceptReject.acceptRejectRegistration(username, "reject", authentication.getName());
		
		if(request.isUserInRole("ROLE_ADMIN"))
			return "redirect:/admin/admin-panel";
		else if(request.isUserInRole("ROLE_COLLEGE"))
			return "redirect:/college/admin-panel";
		else
			return "/error";
	}
}
