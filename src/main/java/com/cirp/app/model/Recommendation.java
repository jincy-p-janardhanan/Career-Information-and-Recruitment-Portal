package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

public class Recommendation {

	@NotBlank
	private String requester_id;
	@NotBlank
	private String requester_name;
	@NotBlank
	private String recommender_id;
	@NotBlank
	private String recommender_name;
	private int status; // -1 for rejected, 0 for pending (default), 1 for accepted
	private String recc_msg; // Recommendation message

	public Recommendation() {
		this.status = 0;
	}

	public Recommendation(@NotBlank String requester_id, @NotBlank String recommender_id, int status,
			String recc_msg) {
		this.requester_id = requester_id;
		this.recommender_id = recommender_id;
		this.status = status;
		this.recc_msg = recc_msg;
		this.status = 0;
	}

	public String getRequester_id() {
		return requester_id;
	}

	public void setRequester_id(String requester_id) {
		this.requester_id = requester_id;
	}

	public String getRecommender_id() {
		return recommender_id;
	}

	public void setRecommender_id(String recommender_id) {
		this.recommender_id = recommender_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRecc_msg() {
		return recc_msg;
	}

	public void setRecc_msg(String recc_msg) {
		this.recc_msg = recc_msg;
	}

	public String getRequester_name() {
		return requester_name;
	}

	public void setRequester_name(String requester_name) {
		this.requester_name = requester_name;
	}

	public String getRecommender_name() {
		return recommender_name;
	}

	public void setRecommender_name(String recommender_name) {
		this.recommender_name = recommender_name;
	}
}
