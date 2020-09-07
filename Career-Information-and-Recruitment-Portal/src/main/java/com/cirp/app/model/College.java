package com.cirp.app.model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="college")
@JsonIgnoreProperties(ignoreUnknown = true)
public final class College extends NonAdmin {
	
	private String affil_univ;
	
	private ContactInfo contact;
	
	private List<String> students; 
	private List<String> alumni_pending;
	private List<String> alumni_rejected;
	private List<String> alumni;
	private List<ObjectId> recc_req_recvd; //Recommendation requests received
	private List<ObjectId> reccommeded; //Recommendations made by this user
	private List<ObjectId> recc_rejected; //Recommendation requests rejected by this user
	
	public College(String username, String password, String name, Address address, String mobile, String email,
			String affil_univ) {
		super(username, password, name, address, mobile, email);
		this.affil_univ = affil_univ;
	}
	
	protected String getAffil_univ() {
		return affil_univ;
	}
	protected void setAffil_univ(String affil_univ) {
		this.affil_univ = affil_univ;
	}

	protected List<String> getStudents() {
		return students;
	}

	protected void setStudents(List<String> students) {
		this.students = students;
	}

	protected List<String> getAlumni_pending() {
		return alumni_pending;
	}

	protected void setAlumni_pending(List<String> alumni_pending) {
		this.alumni_pending = alumni_pending;
	}

	protected List<String> getAlumni_rejected() {
		return alumni_rejected;
	}

	protected void setAlumni_rejected(List<String> alumni_rejected) {
		this.alumni_rejected = alumni_rejected;
	}

	protected List<String> getAlumni() {
		return alumni;
	}

	protected void setAlumni(List<String> alumni) {
		this.alumni = alumni;
	}

	protected List<ObjectId> getRecc_req_recvd() {
		return recc_req_recvd;
	}

	protected void setRecc_req_recvd(List<ObjectId> recc_req_recvd) {
		this.recc_req_recvd = recc_req_recvd;
	}

	protected List<ObjectId> getReccommeded() {
		return reccommeded;
	}

	protected void setReccommeded(List<ObjectId> reccommeded) {
		this.reccommeded = reccommeded;
	}

	protected List<ObjectId> getRecc_rejected() {
		return recc_rejected;
	}

	protected void setRecc_rejected(List<ObjectId> recc_rejected) {
		this.recc_rejected = recc_rejected;
	}

	public ContactInfo getContact() {
		return contact;
	}

	public void setContact(ContactInfo contact) {
		this.contact = contact;
	}
}
