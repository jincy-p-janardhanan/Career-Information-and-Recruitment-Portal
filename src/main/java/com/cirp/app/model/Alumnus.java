package com.cirp.app.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Document(collection="alumnus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alumnus extends Student{
	
	private List<Recommendation> recc_req_recvd; //Recommendation requests received
	private List<Recommendation> reccommeded; //Recommendations made by this user
	private List<Recommendation> recc_rejected; //Recommendation requests rejected by this user

	public Alumnus(String username, String password, String name, Address address, String mobile, String email,
			String reg_no, String course, String branch, int sem, LocalDate st_date, LocalDate end_date, LocalDate dob,
			String college_id) {
		super(username, password, name, address, mobile, email, reg_no, course, branch, sem, st_date, end_date, dob,
				college_id);
		this.setRole("ROLE_PENDING");
	}

	public Alumnus() {
		this.setRole("ROLE_PENDING");
	}

	public Alumnus(String username, String password, String name, Address address, String mobile, String email) {
		super(username, password, name, address, mobile, email);
	}

	public List<Recommendation> getRecc_req_recvd() {
		return recc_req_recvd;
	}

	public void setRecc_req_recvd(List<Recommendation> recc_req_recvd) {
		this.recc_req_recvd = recc_req_recvd;
	}

	public List<Recommendation> getReccommeded() {
		return reccommeded;
	}

	public void setReccommeded(List<Recommendation> reccommeded) {
		this.reccommeded = reccommeded;
	}

	public List<Recommendation> getRecc_rejected() {
		return recc_rejected;
	}

	public void setRecc_rejected(List<Recommendation> recc_rejected) {
		this.recc_rejected = recc_rejected;
	}	
}
