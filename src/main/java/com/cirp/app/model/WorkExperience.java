package com.cirp.app.model;


import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkExperience {
	@NotBlank
	private String job;
	@NotBlank
	private String company;
	private List<String> skills;
	private String desc;
	@NotBlank
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date st_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date end_date;
	private String status; // "current" or "former" employment

	public WorkExperience() {
	}

	public WorkExperience(String job, String company, String desc, Date st_date, Date end_date) {
		this.job = job;
		this.company = company;
		this.desc = desc;
		this.st_date = st_date;
		this.end_date = end_date;
		this.status = "Former";
	}

	public WorkExperience(String job, String company, String desc, Date st_date) {
		this.job = job;
		this.company = company;
		this.desc = desc;
		this.st_date = st_date;
		this.status = "Current";
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getSt_date() {
		return st_date;
	}

	public void setSt_date(Date st_date) {
		this.st_date = st_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
