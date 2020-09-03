package com.cirp.app.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="recruiter")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Recruiter extends NonAdmin {
	
	public Recruiter(String username, String password, String name, Address address, String mobile, String email, String license_no, String landph, String public_email) {
		super(username, password, name, address, mobile, email);
		this.setLicense_no(license_no);
		this.setLandph(landph);
		this.setPublic_email(public_email);
	}
	private String license_no;
	private String landph;
	private String public_email;
	//Setter needed (using query)
	private List<ObjectId> jobs;
	private List<ObjectId> recc_req_recvd; //Recommendation requests received
	private List<ObjectId> reccommeded; //Recommendations made by this user
	private List<ObjectId> recc_rejected; //Recommendation requests rejected by this user
	
	
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
		if(public_email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			this.public_email = public_email;
	}
	protected List<ObjectId> getJobs() {
		return jobs;
	}
	protected List<ObjectId> getRecc_req_recvd() {
		return recc_req_recvd;
	}
	protected List<ObjectId> getReccommeded() {
		return reccommeded;
	}
	protected List<ObjectId> getRecc_rejected() {
		return recc_rejected;
	}
}
