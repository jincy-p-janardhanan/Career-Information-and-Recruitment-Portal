/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * @author Jincy P Janardhanan
 *
 */
public class Personalisation {

	private List<Education> education;
	private List<WorkExperience> work;
	@TextIndexed
	private List<String> skills;
	@TextIndexed
	private List<String> orgs; // Communities and organizations the student is involved in
	private List<Project> project;

	public Personalisation() {

	}

	public Personalisation(List<Education> education, List<WorkExperience> work, List<String> skills,
			List<String> orgs, List<Project> project) {
		this.education = education;
		this.work = work;
		this.skills = skills;
		this.orgs = orgs;
		this.project = project;
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
}
