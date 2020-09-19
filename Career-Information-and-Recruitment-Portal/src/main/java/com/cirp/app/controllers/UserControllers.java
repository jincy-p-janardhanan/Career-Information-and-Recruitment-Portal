package com.cirp.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public void confirmRegistration(@RequestParam String username) {
		acceptReject.acceptRejectRegistration(username, "confirm");
	}

	@GetMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username) {
		acceptReject.acceptRejectRegistration(username, "reject");
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
		String username_or_email = new String();
		model.addAttribute("username_or_email", username_or_email);
		return "reset_password_request";
	}

	@PostMapping("/reset-password-request")
	public String resetPassword(String username_or_email, HttpServletRequest request,
			RedirectAttributes redirectAttributes) {
		reset.resetPasswordRequest(username_or_email, request);
		redirectAttributes.addFlashAttribute("message", "A link to reset your password has been sent to your email.");
		return "redirect:/login";
	}

	@GetMapping("/update-password")
	public String resetPassword(@RequestParam("token") String token, RedirectAttributes redirectAttributes) {
		User user = repo.findByToken(token);
		if (user == null) {
			redirectAttributes.addFlashAttribute("message", "Invalid Request!");
			return "redirect:/login";
		} else {
			redirectAttributes.addAttribute("username", user.getUsername());
			redirectAttributes.addAttribute("password");
			return "redirect:/reset-password";
		}
	}

	@PostMapping("/update-password")
	public String resetPassword(String password, String username, RedirectAttributes redirectAttributes) {
		reset.resetPassword(username, password);
		redirectAttributes.addFlashAttribute("message", "You've successfully reset your password!");
		return "redirect:/login";
	}
}
