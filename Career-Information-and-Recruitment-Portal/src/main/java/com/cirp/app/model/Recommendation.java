package com.cirp.app.model;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * @author Jincy P Janardhanan
 *
 */

public class Recommendation {
	@Id
	private ObjectId _id;
	private ObjectId requester_id;
	private ObjectId recommender_id;
	private int status; //-1 for rejected, 0 for pending (default), 1 for accepted
	private String recc_msg; //Recommendation message
	
	public Recommendation(ObjectId requester_id, ObjectId recommender_id) {
		this.requester_id = requester_id;
		this.recommender_id = recommender_id;
		if(requester_id != recommender_id) {
			this.status = 0;
		}
		else {
			this.status = -1;
		}
	}

	protected ObjectId getRequester_id() {
		return requester_id;
	}

	protected void setRequester_id(ObjectId requester_id) {
		this.requester_id = requester_id;
	}

	protected ObjectId getRecommender_id() {
		return recommender_id;
	}

	protected void setRecommender_id(ObjectId recommender_id) {
		this.recommender_id = recommender_id;
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
