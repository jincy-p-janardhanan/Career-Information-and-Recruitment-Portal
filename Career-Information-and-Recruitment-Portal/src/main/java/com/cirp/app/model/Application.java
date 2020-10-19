package com.cirp.app.model;

import java.util.Date;
import java.util.List;

public class Application {

	private String applicant_id;
	private Date timestamp;
	private List<String> answers;

	public Application() {
		this.timestamp = new Date();
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
