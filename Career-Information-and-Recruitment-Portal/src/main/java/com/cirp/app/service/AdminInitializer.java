/**
 * This class creates (or updates) four admins, generates a random password for them and sends it to their respective emails.
 */
package com.cirp.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.PostConstruct;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cirp.app.model.Address;
import com.cirp.app.model.Admin;

@Service
public class AdminInitializer {

	@Autowired
	MongoTemplate template;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	SendEmail sendmails;

	@PostConstruct
	public void init() {

		// Emails of admins added to a List<String>
		List<String> emails = new ArrayList<String>(Arrays.asList("jincyp7@gmail.com", "aleenatreasa90@gmail.com",
				"ameenaamiaan@gmail.com", "alkabhagavaldas000@gmail.com"));

		ArrayList<String> passwords = new ArrayList<String>();
		try {
			for (int i = 0; i < 4; i++) {
				// Generate a random password for each admin
				passwords.add(RandomStringUtils.randomAlphanumeric(6, 12));

				// Send email to each admin's email account
				String to = emails.get(i);
				String sub = "CIRP Login Credentials - "
						+ LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
				String content = "<p> Password: " + passwords.get(i)+"</p>";
				sendmails.sendEmail(to, sub, content);
			}
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}

		try {
			// Creating 4 admins, passwordEncoder.encode() used for encrypting the password
			// before storing to database
			Admin[] admins = {
					new Admin("admin1", passwordEncoder.encode(passwords.get(0)), "Jincy",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "jincyp7@gmail.com"),

					new Admin("admin2", passwordEncoder.encode(passwords.get(1)), "Aleena",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "aleenatreasa90@gmail.com"),

					new Admin("admin3", passwordEncoder.encode(passwords.get(2)), "Ameena",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "ameenaamiaan@gmail.com"),

					new Admin("admin4", passwordEncoder.encode(passwords.get(3)), "Alka",
							new Address("line1", "line2", "city", "dist", "Kerala", "India", (long) 679522),
							"9400546404", "alkabhagavaldas000@gmail.com") };

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
