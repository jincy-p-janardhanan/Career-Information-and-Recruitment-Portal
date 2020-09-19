package com.cirp.app.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;

@Service
public class ResetPassword {

	@Autowired
	SendEmail sendmails;

	@Autowired
	CirpRepository repo;

	@Autowired
	FindClass find = new FindClass();

	public String resetPasswordRequest(String username_or_email, HttpServletRequest request) {

		User user;
		if (username_or_email.matches(
				"/^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$/")) {
			user = repo.findByEmail(username_or_email);
		} else {
			user = repo.findById(username_or_email);
		}
		if (user == null) {
			return "Invalid username or email!";
		}
		
		String token = token = UUID.randomUUID().toString();
		Class<?> user_class = find.findClass(username_or_email);
		repo.setToken(token, username_or_email, user_class);
		
		String resetUrl = request.getScheme() + "://" + request.getServerName()+ "/update-password?token=" + token;
		String to = user.getEmail();
		String sub = "CIRP | Reset Password";
		String content = "<p>Hi,</p>" + "<p> </p>"
				+ "<p>To reset your password, click <a href="+resetUrl+">here</a>.</p>"
				+ "<p>If you didn't request for opt out, it's possible that your account security is breached.</p>"
				+ "Change your password immediately to secure your account." + "<p></p>" + "<p>Regards,</p>"
				+ "<p>Team CIRP</p>";
		
		sendmails.sendEmail(to, sub, content);
		
		return "";
	}

	public void resetPassword(String username, String new_password) {
		Class<?> user_class = find.findClass(username);
		repo.updatePassword(username, new_password, user_class);
	}
}
