package com.cirp.app.db;

import java.util.ArrayList;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="College")
@JsonIgnoreProperties(ignoreUnknown=true)
public class College extends User {
	
	private String affiliated_univ;
	private long college_landph;
	private Email college_public_email;			//email used by public for support, enquires, etc
	
	private ArrayList<Student> students;
	private ArrayList<Alumnus> alumni;
	
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
	protected Email getCollege_public_email() {
		return college_public_email;
	}
	protected void setCollege_public_email(Email college_public_email) {
		this.college_public_email = college_public_email;
	}
}
