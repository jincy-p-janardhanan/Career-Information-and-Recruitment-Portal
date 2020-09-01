/**
 * 
 */
package com.cirp.app.db;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Admin extends User{
	
	private List<ObjectId> college_request_pending;
	private List<ObjectId> college_approved;
	private List<ObjectId> college_denied;
	
	private List<ObjectId> recruiter_request_pending;
	private List<ObjectId> recruiter_approved;
	private List<ObjectId> recruiter_denied;
	
	public Admin() {
		this.setRole(0);
		this.setStatus(1);
	}
	
	protected List<ObjectId> getCollege_request_pending() {
		return college_request_pending;
	}
	protected List<ObjectId> getCollege_approved() {
		return college_approved;
	}
	protected List<ObjectId> getCollege_denied() {
		return college_denied;
	}
	protected List<ObjectId> getRecruiter_request_pending() {
		return recruiter_request_pending;
	}
	protected List<ObjectId> getRecruiter_approved() {
		return recruiter_approved;
	}
	protected List<ObjectId> getRecruiter_denied() {
		return recruiter_denied;
	}
}
