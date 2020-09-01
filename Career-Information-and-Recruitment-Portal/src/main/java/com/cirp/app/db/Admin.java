/**
 * 
 */
package com.cirp.app.db;

import java.util.ArrayList;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */
@Document(collection="Admin")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Admin extends User{
	
	private ArrayList<College> college_request_pending;
	private ArrayList<ObjectId> college_approved;
	private ArrayList<College> college_denied;
	
	private ArrayList<Recruiter> recruiter_request_pending;
	private ArrayList<ObjectId> recruiter_approved;
	private ArrayList<Recruiter> recruiter_denied;
	
	//Receive College Registration Request
	protected String receiveCollegeRegitrationRequest(College college) {
		String msg;
		try {
			this.college_request_pending.add(college);
			if(this.college_request_pending.contains(college)) {
				msg = "Registration request created successfully!";
			}
			else {
				msg = "Registration request was unsuccessful, please try again!";
			}
		}
		catch (Exception e) {
			msg  = "Registration request was unsuccessful, please try again! \nError: " + e.toString();
		}
		return msg;		
	}

	
	//Getters and Setters
	
}
