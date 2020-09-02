/**
 * 
 */
package com.cirp.app.model;

import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */

@Document(collection="job")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Job {
	private ObjectId recruiter_id;
	private String job_pic; //Recruiter's profile picture; default job icon (if there's no profile picture for recruiter)
	@TextIndexed
	private String title;
	private String desc;
	@TextIndexed
	private String location;
	private String duration;
	private float stipend;
	@Indexed
	private Date last_date;
	private List<String> questions;
	private List<Application> applicants;
	
	@TextIndexed
	private List<String> skills; //job tags

	public Job(ObjectId recruiter_id, String title, String desc, String location, String duration, float stipend, Date last_date,
			List<String> skills) {
		this.recruiter_id = recruiter_id;
		this.title = title;
		this.desc = desc;
		this.location = location;
		this.duration = duration;
		this.stipend = stipend;
		this.last_date = last_date;
		this.skills = skills;
		
		/* 
		 * if (Query to get recruiter's profile picture)
		 * null if non-existent
		 * this.job_pic = query result
		 * 
		 */
	}

	protected String getTitle() {
		return title;
	}

	protected void setTitle(String title) {
		this.title = title;
	}

	protected String getDesc() {
		return desc;
	}

	protected void setDesc(String desc) {
		this.desc = desc;
	}

	protected String getLocation() {
		return location;
	}

	protected void setLocation(String location) {
		this.location = location;
	}

	protected String getDuration() {
		return duration;
	}

	protected void setDuration(String duration) {
		this.duration = duration;
	}

	protected float getStipend() {
		return stipend;
	}

	protected void setStipend(float stipend) {
		this.stipend = stipend;
	}

	protected Date getLast_date() {
		return last_date;
	}

	protected void setLast_date(Date last_date) {
		this.last_date = last_date;
	}

	protected List<String> getQuestions() {
		return questions;
	}

	protected void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	protected List<Application> getApplicants() {
		return applicants;
	}

	protected void setApplicants(List<Application> applicants) {
		this.applicants = applicants;
	}

	protected List<String> getSkills() {
		return skills;
	}

	protected void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public ObjectId getRecruiter_id() {
		return recruiter_id;
	}

	public String getJob_pic() {
		return job_pic;
	}	
}
