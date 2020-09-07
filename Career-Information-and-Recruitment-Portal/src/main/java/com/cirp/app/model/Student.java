/**
 * 
 */
package com.cirp.app.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends NonAdmin{

	private String reg_no;
	@TextIndexed
	private String course;
	@TextIndexed
	private String branch;
	private int sem;
	private Date st_date;
	private Date end_date;
	private String mobile2;
	private float sgpa;
	private float cgpa;
	private String college_id;
	private Personalisation personalisation;
	private List<ObjectId> recommend_req; //List of recommendation requests
	private List<ObjectId> recommendations; //List of recommendations received

	public Student(String username, String password, String name, Address address, String mobile, String email,
			String reg_no, String course, String branch, int sem, Date st_date, Date end_date, String college_id) {
		super(username, password, name, address, mobile, email);
		this.reg_no = reg_no;
		this.course = course;
		this.branch = branch;
		this.sem = sem;
		this.st_date = st_date;
		this.end_date = end_date;
		this.college_id = college_id;
		this.setStatus(1);
		this.setStatus_changed(new Date()); //Sets current date value to status changed
	}

	protected String getReg_no() {
		return reg_no;
	}

	protected void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	protected String getCourse() {
		return course;
	}

	protected void setCourse(String course) {
		this.course = course;
	}

	protected String getBranch() {
		return branch;
	}

	protected void setBranch(String branch) {
		this.branch = branch;
	}

	protected int getSem() {
		return sem;
	}

	protected void setSem(int sem) {
		this.sem = sem;
	}

	
	protected String getMobile2() {
		return mobile2;
	}

	protected void setMobile2(String mobile2) {
		if(mobile2.matches("^\\+(?:[0-9] ?){6,14}[0-9]$"))
			this.mobile2 = mobile2;
	}

	protected float getSgpa() {
		return sgpa;
	}

	protected void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}

	protected float getCgpa() {
		return cgpa;
	}

	protected void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	protected String getCollege_id() {
		return college_id;
	}

	protected void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	protected Date getSt_date() {
		return st_date;
	}

	protected void setSt_date(Date st_date) {
		this.st_date = st_date;
	}

	protected Date getEnd_date() {
		return end_date;
	}

	protected void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	protected Personalisation getPersonalisation() {
		return personalisation;
	}

	protected void setPersonalisation(Personalisation personalisation) {
		this.personalisation = personalisation;
	}

	protected List<ObjectId> getRecommend_req() {
		return recommend_req;
	}

	protected void setRecommend_req(List<ObjectId> recommend_req) {
		this.recommend_req = recommend_req;
	}

	protected List<ObjectId> getRecommendations() {
		return recommendations;
	}

	protected void setRecommendations(List<ObjectId> recommendations) {
		this.recommendations = recommendations;
	}	
}
