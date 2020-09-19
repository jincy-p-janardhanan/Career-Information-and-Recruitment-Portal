package com.cirp.app.model;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="college")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class College extends NonAdmin {
	
	@NotBlank
	private String affil_univ;
	
	@NotNull
	private ContactInfo contact;
	
	private List<String> students; 
	private List<String> alumni_pending;
	private List<String> alumni_rejected;
	private List<String> alumni;
	private List<ObjectId> recc_req_recvd; //Recommendation requests received
	private List<ObjectId> reccommeded; //Recommendations made by this user
	private List<ObjectId> recc_rejected; //Recommendation requests rejected by this user
	
	public College(String username, String password, String name, String mobile, String email,
			String affil_univ, ContactInfo contact) {
		super(username, password, name, mobile, email);
		this.affil_univ = affil_univ;
		this.contact = contact;
		this.setRole("ROLE_PENDING");
	}
	
	public College() {
		super();
		this.setRole("ROLE_PENDING");
	}

	public String getAffil_univ() {
		return affil_univ;
	}

	public void setAffil_univ(String affil_univ) {
		this.affil_univ = affil_univ;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}

	public List<String> getStudents() {
		return students;
	}

	public void setStudents(List<String> students) {
		this.students = students;
	}

	public List<String> getAlumni_pending() {
		return alumni_pending;
	}

	public void setAlumni_pending(List<String> alumni_pending) {
		this.alumni_pending = alumni_pending;
	}

	public List<String> getAlumni_rejected() {
		return alumni_rejected;
	}

	public void setAlumni_rejected(List<String> alumni_rejected) {
		this.alumni_rejected = alumni_rejected;
	}

	public List<String> getAlumni() {
		return alumni;
	}

	public void setAlumni(List<String> alumni) {
		this.alumni = alumni;
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
