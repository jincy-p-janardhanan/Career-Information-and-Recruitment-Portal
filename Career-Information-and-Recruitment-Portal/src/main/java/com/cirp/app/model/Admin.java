/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Admin extends User{
	
	private List<ObjectId> college_pending;
	private List<ObjectId> college_approved;
	private List<ObjectId> college_denied;
	
	private List<ObjectId> recruiter_pending;
	private List<ObjectId> recruiter_approved;
	private List<ObjectId> recruiter_denied;
	
	public Admin(String username, String password, String name, Address address, String mobile, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setAddress(address);
		this.setMobile(mobile);
		this.setEmail(email);
	}

	protected List<ObjectId> getCollege_pending() {
		return college_pending;
	}

	protected List<ObjectId> getCollege_approved() {
		return college_approved;
	}

	protected List<ObjectId> getCollege_denied() {
		return college_denied;
	}

	protected List<ObjectId> getRecruiter_pending() {
		return recruiter_pending;
	}

	protected List<ObjectId> getRecruiter_approved() {
		return recruiter_approved;
	}

	protected List<ObjectId> getRecruiter_denied() {
		return recruiter_denied;
	}
	
	
	
}
