/**
 * 
 */
package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.repository.CirpRepository;

/**
 * @author Jincy P Janardhanan
 *
 */
@Controller
public class UserControllers {
	
	@Autowired
	private CirpRepository repo;
	
	@RequestMapping("/confirmRegistration")
	public void confirmRegistration(@RequestParam String username) {
		repo.confirmRegistration(username);
	}

	@RequestMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username) {
		repo.rejectRegistration(username);
	}

	@RequestMapping("/optout-request")
	public void optoutRequests(@RequestParam String username) {
		repo.optoutRequest(username);
	}

	@RequestMapping("/deactivate-user")
	public void deactivateUser(@RequestParam String username) {
		repo.deleteUser(username);
	}
}
