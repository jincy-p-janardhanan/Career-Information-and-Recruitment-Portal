package com.cirp.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Personalisation {

	private List<Education> education;
	private List<WorkExperience> work;
	private List<String> skills;
	private List<Project> project;
	private List<Communities> communities;
	private List<Awards> awards;

	public Personalisation() {

	}

	public Personalisation(List<Education> education, List<WorkExperience> work, List<String> skills,
			List<Project> project, List<Communities> communities, List<Awards> awards) {
		this.education = education;
		this.work = work;
		this.skills = skills;
		this.project = project;
		this.communities = communities;
		this.awards = awards;
	}

	public List<Education> getEducation() {
		return education;
	}

	public void setEducation(List<Education> education) {
		this.education = education;
	}

	public List<WorkExperience> getWork() {
		return work;
	}

	public void setWork(List<WorkExperience> work) {
		this.work = work;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	public List<Project> getProject() {
		return project;
	}

	public void setProject(List<Project> project) {
		this.project = project;
	}

	public List<Communities> getCommunities() {
		return communities;
	}

	public void setCommunities(List<Communities> communities) {
		this.communities = communities;
	}

	public List<Awards> getAwards() {
		return awards;
	}

	public void setAwards(List<Awards> awards) {
		this.awards = awards;
	}

}
