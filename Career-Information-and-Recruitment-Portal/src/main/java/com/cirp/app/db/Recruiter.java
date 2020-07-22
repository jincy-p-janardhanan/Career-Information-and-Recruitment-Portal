package com.cirp.app.db;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="Recruiter")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Recruiter extends User {
	
	String recruiter_license_no;
	long recruiter_landph;
	Email recruiter_public_email;
	
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
	protected Email getRecruiter_public_email() {
		return recruiter_public_email;
	}
	protected void setRecruiter_public_email(Email recruiter_public_email) {
		this.recruiter_public_email = recruiter_public_email;
	}

}
