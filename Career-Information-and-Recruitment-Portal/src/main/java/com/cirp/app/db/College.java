package com.cirp.app.db;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class College extends NonAdmin {
	
	private String affil_univ;
	private String landph;
	private String public_email;			//email used by public for support, enquires, etc
	
	private List<ObjectId> students;
	private List<ObjectId> alumni_pending;
	private List<ObjectId> alumni_rejected;
	private List<ObjectId> alumni;
	
	public College(String username, String password, String name, Address address, String mobile, String email, String affil_univ, String landph, String public_email) {
		super(username, password, name, address, mobile, email);
		this.setAffil_univ(affil_univ);
		this.setLandph(landph);
		this.setPublic_email(public_email);
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
	
	

}
