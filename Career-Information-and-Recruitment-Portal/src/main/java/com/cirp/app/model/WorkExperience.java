package com.cirp.app.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

class WorkExperience {
	@NotBlank
	private String job;
	@NotBlank
	private String company;
	private List<String> skills;
	private String desc;
	@NotBlank
	private Date st_date;
	private Date end_date;
	private String status; //"current" or "former" employment
	
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

	protected String getJob() {
		return job;
	}

	protected void setJob(String job) {
		this.job = job;
	}

	protected String getCompany() {
		return company;
	}

	protected void setCompany(String company) {
		this.company = company;
	}

	protected String getDesc() {
		return desc;
	}

	protected void setDesc(String desc) {
		this.desc = desc;
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

	protected String getStatus() {
		return status;
	}

	protected void setStatus(String status) {
		this.status = status;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}
	
	
	
	
	
}
