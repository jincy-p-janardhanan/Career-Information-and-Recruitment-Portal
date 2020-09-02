/**
 * 
 */
package com.cirp.app.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Jincy P Janardhanan
 *
 */
@Document(collection="alumnus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alumnus extends Student{

	//Setters needed
	private ObjectId recc_req_recvd; //Recommendation requests received
	private ObjectId reccommeded; //Recommendations made by this user
	private ObjectId recc_rejected; //Recommendation requests rejected by this user
	
	public Alumnus(String username, String password, String name, Address address, String mobile, String email,
			String reg_no, String course, String branch, int sem, Date st_date, Date end_date, ObjectId college) {
		super(username, password, name, address, mobile, email, reg_no, course, branch, sem, st_date, end_date,
				college);
	}
	
	protected ObjectId getRecc_req_recvd() {
		return recc_req_recvd;
	}
	protected void setRecc_req_recvd(ObjectId recc_req_recvd) {
		this.recc_req_recvd = recc_req_recvd;
	}
	protected ObjectId getReccommeded() {
		return reccommeded;
	}
	protected void setReccommeded(ObjectId reccommeded) {
		this.reccommeded = reccommeded;
	}
	protected ObjectId getRecc_rejected() {
		return recc_rejected;
	}
	protected void setRecc_rejected(ObjectId recc_rejected) {
		this.recc_rejected = recc_rejected;
	}
	
}