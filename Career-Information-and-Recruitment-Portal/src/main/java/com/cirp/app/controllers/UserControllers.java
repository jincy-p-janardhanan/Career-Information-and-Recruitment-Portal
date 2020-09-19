package com.cirp.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cirp.app.repository.CirpRepository;
import com.cirp.app.service.AcceptReject;
import com.cirp.app.service.OptoutRequest;

@Controller
public class UserControllers {
	
	@Autowired
	private CirpRepository repo;
	
	@Autowired
	private OptoutRequest optout;
	
	@Autowired
	private AcceptReject acceptReject;
	
	@RequestMapping("/confirmRegistration")
	public void confirmRegistration(@RequestParam String username) {
		acceptReject.acceptRejectRegistration(username, "confirm");
	}

	@RequestMapping("/rejectRegistration")
	public void rejectRegistration(@RequestParam String username) {
		acceptReject.acceptRejectRegistration(username, "reject");
	}

	@RequestMapping("/optout-request")
	public void optoutRequests(@RequestParam String username) {
		optout.optoutRequest(username);
	}

	@RequestMapping("/deactivate-user")
	public void deactivateUser(@RequestParam String username) {
		repo.deleteUser(username);
	}
}
