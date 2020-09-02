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
	private String landph;
	private String public_email;			//email used by public for support, enquires, etc
	
	//Setters needed for below members (using query)
	private List<ObjectId> students;
	private List<ObjectId> alumni_pending;
	private List<ObjectId> alumni_rejected;
	private List<ObjectId> alumni;
	private ObjectId recc_req_recvd; //Recommendation requests received
	private ObjectId reccommeded; //Recommendations made by this user
	private ObjectId recc_rejected; //Recommendation requets rejected by this user
	
	public College(String username, String password, String name, Address address, String mobile, String email,
			String affil_univ, String landph, String public_email) {
		super(username, password, name, address, mobile, email);
		this.affil_univ = affil_univ;
		this.landph = landph;
		this.public_email = public_email;
	}
	
	protected String getAffil_univ() {
		return affil_univ;
	}
	protected void setAffil_univ(String affil_univ) {
		this.affil_univ = affil_univ;
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
	protected List<ObjectId> getStudents() {
		return students;
	}
	protected void setStudents(List<ObjectId> students) {
		this.students = students;
	}
	
	protected List<ObjectId> getAlumni_pending() {
		return alumni_pending;
	}
	protected List<ObjectId> getAlumni_rejected() {
		return alumni_rejected;
	}
	protected List<ObjectId> getAlumni() {
		return alumni;
	}

	public ObjectId getRecc_req_recvd() {
		return recc_req_recvd;
	}

	public ObjectId getReccommeded() {
		return reccommeded;
	}

	public ObjectId getRecc_rejected() {
		return recc_rejected;
	}
}
