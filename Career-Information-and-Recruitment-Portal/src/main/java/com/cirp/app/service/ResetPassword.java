package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.repository.CirpRepository;

@Service
public class ResetPassword {

	@Autowired
	SendEmail sendmails;

	@Autowired
	CirpRepository repo;

	@Autowired
	FindClass find = new FindClass();

	public void resetPasswordRequest(String username_or_email) {
		// TODO Send password reset email with token
	}

	public void resetPassword(String username_or_email, String new_password) {
		Class<?> user_class = find.findClass(username_or_email);
		repo.updatePassword(username_or_email, new_password, user_class);
	}
}
