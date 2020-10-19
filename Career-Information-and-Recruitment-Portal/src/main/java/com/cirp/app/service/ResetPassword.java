package com.cirp.app.service;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public String resetPasswordRequest(String username_or_email, HttpServletRequest request) {

		User user = repo.findById(username_or_email);
		if (user == null) {
			user = repo.findByEmail(username_or_email);
		}
		if (user == null) {
			return "Invalid username or email!";
		}
		
		String token = UUID.randomUUID().toString();
		
		Class<?> user_class = find.findClass(username_or_email);
		repo.setToken(token, user.getUsername(), user_class);
		
		String resetUrl = request.getScheme() + "://" + request.getServerName()+":8080/update-password?token=" + token;
		String to = user.getEmail();
		String sub = "CIRP | Reset Password";
		String content = "<p>Hi,</p>" + "<p> </p>"
				+ "<p>To reset your password, click the following link: </p>"+ 
				"<a href = "+ resetUrl + ">here</a>"+"</p>"
				+ "<p>If you didn't request to reset password, you can safely ignore this email." + "<p></p>" + "<p>Regards,</p>"
				+ "<p>Team CIRP</p>";
		
		sendmails.sendEmail(to, sub, content);
		return "";
	}

	public void resetPassword(String username, String new_password) {
		Class<?> user_class = find.findClass(username);
		repo.updatePassword(username, passwordEncoder.encode(new_password), user_class);
		repo.setToken("", username, user_class);
	}
}
