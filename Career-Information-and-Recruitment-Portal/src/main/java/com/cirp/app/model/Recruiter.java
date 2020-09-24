package com.cirp.app.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="recruiter")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class Recruiter extends NonAdmin {
	
	@NotBlank
	private String license_no;
	@NotNull
	private ContactInfo contact;
	private List<ObjectId> jobs;
	private List<ObjectId> recc_req_recvd; //Recommendation requests received
	private List<ObjectId> reccommeded; //Recommendations made by this user
	private List<ObjectId> recc_rejected; //Recommendation requests rejected by this user
	
	public Recruiter(String username, String password, String name, ContactInfo contact, String mobile, String email, String license_no) {
		super(username, password, name, mobile, email);
		this.setLicense_no(license_no);
		this.setContact(contact);
		this.setRole("ROLE_PENDING");
		this.setProfile_pic("default_recruiter.png");
	}
		
	public Recruiter() {
		super();
		this.setRole("ROLE_PENDING");
		this.setProfile_pic("default_recruiter.png");
	}

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public List<ObjectId> getJobs() {
		return jobs;
	}

	public void setJobs(List<ObjectId> jobs) {
		this.jobs = jobs;
	}

	public List<ObjectId> getRecc_req_recvd() {
		return recc_req_recvd;
	}

	public void setRecc_req_recvd(List<ObjectId> recc_req_recvd) {
		this.recc_req_recvd = recc_req_recvd;
	}

	public List<ObjectId> getReccommeded() {
		return reccommeded;
	}

	public void setReccommeded(List<ObjectId> reccommeded) {
		this.reccommeded = reccommeded;
	}

	public List<ObjectId> getRecc_rejected() {
		return recc_rejected;
	}

	public void setRecc_rejected(List<ObjectId> recc_rejected) {
		this.recc_rejected = recc_rejected;
	}
}
