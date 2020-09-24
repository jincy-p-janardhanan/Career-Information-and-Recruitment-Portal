package com.cirp.app.model;

import java.util.List;

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

	protected List<Project> getProject() {
		return project;
	}

	protected void setProject(List<Project> project) {
		this.project = project;
	}
}
