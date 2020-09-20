package com.cirp.app.model;
import javax.validation.constraints.NotBlank;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.MongoId;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Recommendation {
	
	@MongoId
	private ObjectId _id;
	@NotBlank
	private String requester_id;
	@NotBlank
	private String recommender_id;
	private int status; //-1 for rejected, 0 for pending (default), 1 for accepted
	private String recc_msg; //Recommendation message 

	public Recommendation(String requester_id, String recommender_id) {
		this.requester_id = requester_id;
		this.recommender_id = recommender_id;
		if(requester_id != recommender_id) {
			this.status = 0;
		}
		else {
			this.status = -1;
		}
	}
	
	protected String getRequester_id() {
		return requester_id;
	}

	protected String getRecommender_id() {
		return recommender_id;
	}

	protected int getStatus() {
		return status;
	}

	protected void setStatus(int status) {
		if(this.requester_id != this.recommender_id && status >= -1 && status <= 1)
			this.status = status;
		else
			this.status = -1;
	}

	protected String getRecc_msg() {
		return recc_msg;
	}

	protected void setRecc_msg(String recc_msg) {
		this.recc_msg = recc_msg;
	}

	protected ObjectId get_id() {
		return _id;
	}
	
	
}
