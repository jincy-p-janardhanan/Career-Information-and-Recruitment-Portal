package com.cirp.app.model;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection = "student")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student extends NonAdmin {

	@NotBlank
	private String reg_no;
	@NotBlank
	private String course;
	@NotBlank
	private String branch;

	private int sem;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate st_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate end_date;
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;
	private String mobile2;
	private float sgpa;
	private float cgpa;
	@NotBlank
	private String college_id;
	private Personalisation personalisation;
	private List<Recommendation> recommend_req; // List of recommendation requests
	private List<Recommendation> recommendations; // List of recommendations received

	private List<String> applied_jobs;
	private List<String> hired_jobs;
	private List<String> rejected_jobs;

	public Student(String username, String password, String name, Address address, String mobile, String email,
			String reg_no, String course, String branch, int sem, LocalDate st_date, LocalDate end_date, LocalDate dob,
			String college_id) {
		super(username, password, name, address, mobile, email);
		this.reg_no = reg_no;
		this.course = course;
		this.branch = branch;
		this.sem = sem;
		this.st_date = st_date;
		this.end_date = end_date;
		this.college_id = college_id;
		this.dob = dob;
		this.setStatus(1);
		this.setRole("ROLE_STUDENT");
		this.setProfile_pic("default_student.png");
	}

	public Student() {
		super();
		this.setStatus(1);
		this.setRole("ROLE_STUDENT");
		this.setProfile_pic("default_student.png");
	}

	public Student(String username, String password, String name, Address address, String mobile, String email) {
		super(username, password, name, address, mobile, email);
		this.setStatus(1);
		this.setRole("ROLE_STUDENT");
	}

	public String getReg_no() {
		return reg_no;
	}

	public void setReg_no(String reg_no) {
		this.reg_no = reg_no;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public int getSem() {
		return sem;
	}

	public void setSem(int sem) {
		this.sem = sem;
	}

	public LocalDate getSt_date() {
		return st_date;
	}

	public void setSt_date(LocalDate st_date) {
		this.st_date = st_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public float getSgpa() {
		return sgpa;
	}

	public void setSgpa(float sgpa) {
		this.sgpa = sgpa;
	}

	public float getCgpa() {
		return cgpa;
	}

	public void setCgpa(float cgpa) {
		this.cgpa = cgpa;
	}

	public String getCollege_id() {
		return college_id;
	}

	public void setCollege_id(String college_id) {
		this.college_id = college_id;
	}

	public Personalisation getPersonalisation() {
		return personalisation;
	}

	public void setPersonalisation(Personalisation personalisation) {
		this.personalisation = personalisation;
	}

	public List<String> getApplied_jobs() {
		return applied_jobs;
	}

	public void setApplied_jobs(List<String> applied_jobs) {
		this.applied_jobs = applied_jobs;
	}

	public List<String> getHired_jobs() {
		return hired_jobs;
	}

	public void setHired_jobs(List<String> hired_jobs) {
		this.hired_jobs = hired_jobs;
	}

	public List<String> getRejected_jobs() {
		return rejected_jobs;
	}

	public void setRejected_jobs(List<String> rejected_jobs) {
		this.rejected_jobs = rejected_jobs;
	}

	public List<Recommendation> getRecommend_req() {
		return recommend_req;
	}

	public void setRecommend_req(List<Recommendation> recommend_req) {
		this.recommend_req = recommend_req;
	}

	public List<Recommendation> getRecommendations() {
		return recommendations;
	}

	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
}
