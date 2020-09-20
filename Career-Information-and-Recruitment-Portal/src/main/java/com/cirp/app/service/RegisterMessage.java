/**
 * 
 */
package com.cirp.app.service;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cirp.app.model.User;

/**
 * @author Jincy P Janardhanan
 *
 */
public class RegisterMessage {
	public static void registerMessage(RedirectAttributes redirectAttributes, User user_exists, User email_exists) {
		if (user_exists != null) {
			redirectAttributes.addFlashAttribute("message","Sorry, this username already exists!");
		} else if(email_exists != null) {
			redirectAttributes.addFlashAttribute("message","Sorry, this email already exists!");
		} else {
			redirectAttributes.addFlashAttribute("message","Your registration " + 
					"has been submitted! Please wait until the website administrators " + 
					"approve your account. You will be notified via your registered " + 
					"email.");
		}
	}
}
