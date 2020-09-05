/**
 * 
 */
package com.cirp.app;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.cirp.app.model.Address;
import com.cirp.app.model.Admin;

/**
 * @author Jincy P Janardhanan
 *
 */

@Component
public class AdminInitializer {
	
	@Autowired
	JavaMailSender send_email;
	
	@Autowired
	MongoTemplate template;

	@PostConstruct
	public void init() {

		// Emails of admins added to a List<String>
		List<String> emails = new ArrayList<String>(Arrays.asList("jincyp7@gmail.com", "aleenatreasa90@gmail.com",
				"ameenaamiaan@gmail.com", "alkabhagavaldas000@gmail.com"));
		
		ArrayList<String> passwords = new ArrayList<String>();
		
		SimpleMailMessage msg = new SimpleMailMessage();
		try {
			for (int i = 0; i < 4; i++) {
				// Generate a random password for each admin
				passwords.add(RandomStringUtils.randomAlphanumeric(6, 12));

				// Send email to each admin's email account
				msg.setTo(emails.get(i));
				msg.setSubject("CIRP Login Credentials - "
						+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				msg.setText("Password: " + passwords.get(i));
				send_email.send(msg);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			// Creating 4 admins, BCryptPasswordEncoder() used for encrypting the password
			// before storing to database
			Admin[] admins = {
					new Admin("admin1", new BCryptPasswordEncoder().encode(passwords.get(0)), "Jincy",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "jincyp7@gmail.com"),

					new Admin("admin2", new BCryptPasswordEncoder().encode(passwords.get(1)), "Aleena",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "aleenatreasa90@gmail.com"),

					new Admin("admin3", new BCryptPasswordEncoder().encode(passwords.get(2)), "Ameena",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "ameenaamiaan@gmail.com"),

					new Admin("admin4", new BCryptPasswordEncoder().encode(passwords.get(3)), "Alka",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "alkabhagavaldas000@gmail.com") };

			// Setting up mongodb connection
			//MongoTemplate template = new MongoTemplate(MongoClients.create("mongodb://localhost:27017"), "cirp");
			// Saving all admins
			for (int i = 0; i < 4; i++) {
				template.save(admins[i]);
			}
		} catch (Exception e) {
			System.out.println("\n\n--------------------------------------------------------------------\n" + e + "\n");
			e.printStackTrace();
			System.out.println("--------------------------------------------------------------------\n");
		}
	}
}
