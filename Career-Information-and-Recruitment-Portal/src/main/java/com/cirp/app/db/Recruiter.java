package com.cirp.app.db;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Recruiter extends NonAdmin {
	
	public Recruiter(String username, String password, String name, Address address, String mobile, String email, String license_no, String landph, String public_email) {
		super(username, password, name, address, mobile, email);
		this.setLicense_no(license_no);
		this.setLandph(landph);
		this.setPublic_email(public_email);
	}
	private String license_no;
	private String landph;
	private String public_email;
	private List<ObjectId> jobs;
	
	
	protected String getLicense_no() {
		return license_no;
	}
	protected void setLicense_no(String license_no) {
		this.license_no = license_no;
	}
	protected String getLandph() {
		return landph;
	}
	protected void setLandph(String landph) {
		this.landph = landph;
	}
	protected String getPublic_email() {
		return public_email;
	}
	protected void setPublic_email(String public_email) {
		this.public_email = public_email;
	}
	protected List<ObjectId> getJobs() {
		return jobs;
	}
	protected void setJobs(List<ObjectId> jobs) {
		this.jobs = jobs;
	}	
}
