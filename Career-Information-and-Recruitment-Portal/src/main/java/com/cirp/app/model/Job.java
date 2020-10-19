package com.cirp.app.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="job")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	
	@MongoId
	String _id;
	@NotBlank
	private String recruiter_id;
	private String profile_pic; //Recruiter's profile picture; default job icon (if there's no profile picture for recruiter)
	@NotBlank
	private String name;
	@NotBlank
	private String desc;
	private String location;
	private String duration;
	private float stipend;
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date last_date;
	private List<String> questions;
	private List<Application> applicants;
	private List<Application> hired;
	private List<Application> rejected;
	private List<String> skills; //job tags
	
	public Job(String recruiter_id, String recruiter_pic,  String name, String desc, String location, String duration, float stipend, Date last_date,
			List<String> skills) {
		this.recruiter_id = recruiter_id;
		this.name = name;
		this.desc = desc;
		this.location = location;
		this.duration = duration;
		this.stipend = stipend;
		this.last_date = last_date;
		this.skills = skills;
		if(recruiter_pic != "default_recruiter.png") {
			this.profile_pic = recruiter_pic;
		} else {
			this.profile_pic = "default_job.png";
		}
		
	}

	public Job() {
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String id) {
		_id = id;
	}

	public String getRecruiter_id() {
		return recruiter_id;
	}

	public void setRecruiter_id(String recruiter_id) {
		this.recruiter_id = recruiter_id;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public float getStipend() {
		return stipend;
	}

	public void setStipend(float stipend) {
		this.stipend = stipend;
	}

	public Date getLast_date() {
		return last_date;
	}

	public void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<Application> getApplicants() {
		return applicants;
	}

	public void setApplicants(List<Application> applicants) {
		this.applicants = applicants;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<Application> getHired() {
		return hired;
	}

	public void setHired(List<Application> hired) {
		this.hired = hired;
	}

	public List<Application> getRejected() {
		return rejected;
	}

	public void setRejected(List<Application> rejected) {
		this.rejected = rejected;
	}
}
