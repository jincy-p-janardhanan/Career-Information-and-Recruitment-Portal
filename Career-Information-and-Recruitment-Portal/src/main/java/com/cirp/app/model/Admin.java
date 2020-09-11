/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection = "admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Admin extends User {

	private List<String> college_pending;
	private List<String> college_approved;
	private List<String> college_denied;

	private List<String> recruiter_pending;
	private List<String> recruiter_approved;
	private List<String> recruiter_denied;

	public Admin(String username, String password, String name, Address address, String mobile, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setAddress(address);
		this.setMobile(mobile);
		this.setEmail(email);
		this.setAdmin(true);
		this.setRole("admin");
	}

	public List<String> getCollege_pending() {
		return college_pending;
	}

	protected void setCollege_pending(List<String> college_pending) {
		this.college_pending = college_pending;
	}

	protected List<String> getCollege_approved() {
		return college_approved;
	}

	protected void setCollege_approved(List<String> college_approved) {
		this.college_approved = college_approved;
	}

	protected List<String> getCollege_denied() {
		return college_denied;
	}

	protected void setCollege_denied(List<String> college_denied) {
		this.college_denied = college_denied;
	}

	public List<String> getRecruiter_pending() {
		return recruiter_pending;
	}

	protected void setRecruiter_pending(List<String> recruiter_pending) {
		this.recruiter_pending = recruiter_pending;
	}

	protected List<String> getRecruiter_approved() {
		return recruiter_approved;
	}

	protected void setRecruiter_approved(List<String> recruiter_approved) {
		this.recruiter_approved = recruiter_approved;
	}

	protected List<String> getRecruiter_denied() {
		return recruiter_denied;
	}

	protected void setRecruiter_denied(List<String> recruiter_denied) {
		this.recruiter_denied = recruiter_denied;
	}

}
