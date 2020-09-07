/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Application {
	
	private String applicant_id;
	private List<String> answers;
	
	public Application(String applicant_id) {
		this.setApplicant_id(applicant_id);
	}

	public String getApplicant_id() {
		return applicant_id;
	}

	public void setApplicant_id(String applicant_id) {
		this.applicant_id = applicant_id;
	}

	protected List<String> getAnswers() {
		return answers;
	}

	protected void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
