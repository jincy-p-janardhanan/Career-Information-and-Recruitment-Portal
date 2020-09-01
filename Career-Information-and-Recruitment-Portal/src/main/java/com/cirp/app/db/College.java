package com.cirp.app.db;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class College extends User {
	
	private String affiliated_univ;
	private long college_landph;
	private String college_public_email;			//email used by public for support, enquires, etc
	
	private List<ObjectId> students;
	private List<ObjectId> alumni_pending;
	private List<ObjectId> alumni;
	
	
	//Getters and Setters
	protected String getAffiliated_univ() {
		return affiliated_univ;
	}
	protected void setAffiliated_univ(String affiliated_univ) {
		this.affiliated_univ = affiliated_univ;
	}
	protected long getCollege_landph() {
		return college_landph;
	}
	protected void setCollege_landph(long college_landph) {
		this.college_landph = college_landph;
	}
	protected String getCollege_public_email() {
		return college_public_email;
	}
	protected void setCollege_public_email(String college_public_email) {
		this.college_public_email = college_public_email;
	}
	public List<ObjectId> getStudents() {
		return students;
	}
	public List<ObjectId> getAlumni_pending() {
		return alumni_pending;
	}
	public List<ObjectId> getAlumni() {
		return alumni;
	}
	
}
