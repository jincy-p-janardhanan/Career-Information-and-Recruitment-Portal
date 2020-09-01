package com.cirp.app.db;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */


public class Recruiter extends User {
	
	private String recruiter_license_no;
	private long recruiter_landph;
	private String recruiter_public_email;
	public List<ObjectId> Jobs;
		
	protected String getRecruiter_license_no() {
		return recruiter_license_no;
	}
	protected void setRecruiter_license_no(String recruiter_license_no) {
		this.recruiter_license_no = recruiter_license_no;
	}
	protected long getRecruiter_landph() {
		return recruiter_landph;
	}
	protected void setRecruiter_landph(long recruiter_landph) {
		this.recruiter_landph = recruiter_landph;
	}
	protected String getRecruiter_public_email() {
		return recruiter_public_email;
	}
	protected void setRecruiter_public_email(String recruiter_public_email) {
		this.recruiter_public_email = recruiter_public_email;
	}

}
