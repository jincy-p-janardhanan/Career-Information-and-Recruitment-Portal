/**
 * 
 */
package com.cirp.app;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.cirp.app.model.Address;
import com.cirp.app.model.Admin;

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
		
		MongoTemplate template;
		List<String> passwords = new ArrayList<String>();
		for(int i = 0; i < 4; i++)
			passwords.add(RandomStringUtils.randomAlphanumeric(6,12));
		Admin admin1 = new Admin("admin1", passwords.get(0), "Jincy", new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522), "9400546404", "jincyp7@gmail.com");
		Admin admin2 = new Admin("admin2", passwords.get(1), "Aleena", new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522), "9400546404", "aleenatreasa90@gmail.com");
		Admin admin3 = new Admin("admin3", passwords.get(2), "Ameena", new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522), "9400546404", "ameenaamiaan@gmail.com");
		Admin admin4 = new Admin("admin4", passwords.get(3), "Alka", new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522), "9400546404", "alkabhagavaldas000@gmail.com");
		template.save(admin1);
		template.save(admin2);
		
	}
}
