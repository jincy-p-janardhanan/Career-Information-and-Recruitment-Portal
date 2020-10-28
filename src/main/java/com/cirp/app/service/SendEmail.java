package com.cirp.app.service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {
	@Autowired
	JavaMailSender send_email;

	public void sendEmail(String to, String sub, String content) {

		try {
			MimeMessage msg = send_email.createMimeMessage();
			msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			msg.setSubject(sub);
			msg.setContent(content, "text/html");
			send_email.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
