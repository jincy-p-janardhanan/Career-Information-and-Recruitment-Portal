package com.cirp.app.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="job")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	
	@MongoId
	ObjectId Id;
	@NotBlank
	private String recruiter_id;
	private String job_pic; //Recruiter's profile picture; default job icon (if there's no profile picture for recruiter)
	@NotBlank
	@TextIndexed
	private String title;
	@NotBlank
	private String desc;
	@TextIndexed
	private String location;
	private String duration;
	private float stipend;
	@NotBlank
	@Indexed
	private Date last_date;
	private List<String> questions;
	private List<Application> applicants;
	
	@TextIndexed
	private List<String> skills; //job tags
	
	public Job(String recruiter_id, String recruiter_pic,  String title, String desc, String location, String duration, float stipend, Date last_date,
			List<String> skills) {
		this.recruiter_id = recruiter_id;
		this.title = title;
		this.desc = desc;
		this.location = location;
		this.duration = duration;
		this.stipend = stipend;
		this.last_date = last_date;
		this.skills = skills;
		this.job_pic = recruiter_pic;
	}

	public ObjectId getId() {
		return Id;
	}

	public void setId(ObjectId id) {
		Id = id;
	}

	public String getRecruiter_id() {
		return recruiter_id;
	}

	public void setRecruiter_id(String recruiter_id) {
		this.recruiter_id = recruiter_id;
	}

	public String getJob_pic() {
		return job_pic;
	}

	public void setJob_pic(String job_pic) {
		this.job_pic = job_pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
