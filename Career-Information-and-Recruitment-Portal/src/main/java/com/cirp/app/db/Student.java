/**
 * 
 */
package com.cirp.app.db;

import java.util.Date;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */
class Student {
	private String register_no;
	private String course;
	private String branch;
	private int sem;
	private int st_year;
	private int end_year;
	private float sgpa;
	private float cgpa;
	private Date dob;
	private String mobile2;
	private ObjectId college;
	
	protected String getRegister_no() {
		return register_no;
	}
	protected String getCourse() {
		return course;
	}
	protected String getBranch() {
		return branch;
	}
	protected int getSem() {
		return sem;
	}
	protected int getSt_year() {
		return st_year;
	}
	protected int getEnd_year() {
		return end_year;
	}
	protected float getSgpa() {
		return sgpa;
	}
	protected float getCgpa() {
		return cgpa;
	}
	protected Date getDob() {
		return dob;
	}
	protected String getMobile2() {
		return mobile2;
	}
	public ObjectId getCollege() {
		return college;
	}
	protected void setRegister_no(String register_no) {
		this.register_no = register_no;
	}
	protected void setCourse(String course) {
		this.course = course;
	}
	protected void setBranch(String branch) {
		this.branch = branch;
	}
	protected void setSem(int sem) {
		this.sem = sem;
	}
	protected void setSt_year(int st_year) {
		this.st_year = st_year;
	}
	protected void setEnd_year(int end_year) {
		this.end_year = end_year;
	}
	protected void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}
	protected void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}
	protected void setDob(Date dob) {
		this.dob = dob;
	}
	protected void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}
	public void setCollege(ObjectId college) {
		this.college = college;
	}
	
	
}
