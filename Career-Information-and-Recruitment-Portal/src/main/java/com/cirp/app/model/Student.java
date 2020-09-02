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
	private String course;
	private String branch;
	private int sem;
	private Date st_date;
	private Date end_date;
	private String mobile2;
	private float sgpa;
	private float cgpa;
	private ObjectId college;
	private List<Education> education;
	private List<WorkExperience> work;
	@TextIndexed
	private List<String> skills;
	@TextIndexed
	private List<String> orgs; //Communities and organizations the student is involved in
	private List<Project> project;
	
	//Setters needed
	private List<ObjectId> recommend_req; //List of recommendation requests
	private List<ObjectId> recommendations; //List of recommendations received

	public Student(String username, String password, String name, Address address, String mobile, String email,
			String reg_no, String course, String branch, int sem, Date st_date, Date end_date, ObjectId college) {
		super(username, password, name, address, mobile, email);
		this.reg_no = reg_no;
		this.course = course;
		this.branch = branch;
		this.sem = sem;
		this.st_date = st_date;
		this.end_date = end_date;
		this.college = college;
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

	protected ObjectId getCollege() {
		return college;
	}

	protected void setCollege(ObjectId college) {
		this.college = college;
	}

	protected List<Education> getEducation() {
		return education;
	}

	protected void setEducation(List<Education> education) {
		this.education = education;
	}

	protected List<WorkExperience> getWork() {
		return work;
	}

	protected void setWork(List<WorkExperience> work) {
		this.work = work;
	}

	protected List<String> getSkills() {
		return skills;
	}

	protected void setSkills(List<String> skills) {
		this.skills = skills;
	}

	protected List<String> getOrgs() {
		return orgs;
	}

	protected void setOrgs(List<String> orgs) {
		this.orgs = orgs;
	}

	protected List<Project> getProject() {
		return project;
	}

	protected void setProject(List<Project> project) {
		this.project = project;
	}

	protected List<ObjectId> getRecommend_req() {
		return recommend_req;
	}

	protected List<ObjectId> getRecommendations() {
		return recommendations;
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
}
