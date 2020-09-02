/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

import org.bson.types.ObjectId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Application {
	private ObjectId applicant_id;
	private List<String> answers;
	
	public Application(ObjectId applicant_id) {
		this.applicant_id = applicant_id;
	}

	protected ObjectId getApplicant_id() {
		return applicant_id;
	}

	protected void setApplicant_id(ObjectId applicant_id) {
		this.applicant_id = applicant_id;
	}

	protected List<String> getAnswers() {
		return answers;
	}

	protected void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}
