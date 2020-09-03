/**
 * 
 */
package com.cirp.app;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

/**
 * @author Jincy P Janardhanan
 *
 */
@Component
public class AdminInitializer {
	@PostConstruct
	public void init() {
		//Create admins with the given details
		//Save them to the database
		//Generate a random password to these admins and send it to their emails
		//SMTP reference: https://mkyong.com/spring-boot/spring-boot-how-to-send-email-via-smtp/
		
	}
}
