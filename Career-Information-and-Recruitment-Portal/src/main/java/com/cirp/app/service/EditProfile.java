package com.cirp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cirp.app.model.ContactInfo;
import com.cirp.app.model.Personalisation;
import com.cirp.app.repository.CirpRepository;

@Service
public class EditProfile {
	@Autowired
	CirpRepository repo;

	@Autowired
	FindClass find = new FindClass();

	public void updateProfilePic(String profile_pic, String username) {
		Class<?> user_class = repo.findById(username);
		repo.updateProfilePic(profile_pic, username, user_class);		
	}

	public void updateBgImg(String bg_img, String username) {
		Class<?> user_class = repo.findById(username);
		repo.updateBgImg(bg_img, username, user_class);
	}

	public void updateDesc(String desc, String username) {
		Class<?> user_class = repo.findById(username).getClass();
		repo.updateDesc(desc, username, user_class);
	}
	
	public void updateContact(ContactInfo contact, String username) {
		Class<?> user_class = repo.findById(username).getClass();
		repo.updateContact(contact, username, user_class);
	}
	
	public void updatePersonalisation(Personalisation personalisation, String username) {
		Class<?> user_class = repo.findById(username).getClass();
		repo.updatePersonalisation(personalisation, username, user_class);
	}
}
