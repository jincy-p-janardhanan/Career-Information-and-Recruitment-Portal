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
	
	public Recruiter(String username, String password, String name, Address address, String mobile, String email, String license_no) {
		super(username, password, name, address, mobile, email);
		this.setLicense_no(license_no);
	}
	private String license_no;
	private ContactInfo contact;
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
	
	protected ContactInfo getContact() {
		return contact;
	}
	
	protected void setContact(ContactInfo contact) {
		this.contact = contact;
	}
	
	protected List<ObjectId> getJobs() {
		return jobs;
	}
	protected void setJobs(List<ObjectId> jobs) {
		this.jobs = jobs;
	}
	protected void setRecc_req_recvd(List<ObjectId> recc_req_recvd) {
		this.recc_req_recvd = recc_req_recvd;
	}
	protected void setReccommeded(List<ObjectId> reccommeded) {
		this.reccommeded = reccommeded;
	}
	protected void setRecc_rejected(List<ObjectId> recc_rejected) {
		this.recc_rejected = recc_rejected;
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
