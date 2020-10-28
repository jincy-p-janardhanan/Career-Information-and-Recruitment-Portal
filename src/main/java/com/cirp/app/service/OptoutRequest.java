package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.model.User;
import com.cirp.app.repository.CirpRepository;

@Service
public class OptoutRequest {

	@Autowired
	private CirpRepository repo;
	
	@Autowired
	private SendEmail sendmails;

	public void optoutRequest(String username) {

		try {
			//Incomplete add a token for deactivation and send link
			User user = repo.findById(username);
			String to = user.getEmail();
			String sub = "CIRP | Opt Out";
			String content = "<p>Hi,</p>" + "<p> </p>"
					+ "<p>To opt out from Career Information and Recruitment Portal, click <a href=\"localhost:8080/optout.html\">here</a>.</p>"
					+ "<p>If you didn't request for opt out, it's possible that your account security is breached.</p>"
					+ "Change your password immediately to secure your account." + "<p></p>" + "<p>Regards,</p>"
					+ "<p>Team CIRP</p>";
			sendmails.sendEmail(to, sub, content);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
