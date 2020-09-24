package com.cirp.app.model;

import java.util.Date;

public abstract class NonAdmin extends User {
	private String profile_pic;
	private String desc;
	private String bg_img; //background image
	private int approval_count; /*Incremented each time when an admin approves registration request
	User.status changed to approved (1) when all admins approve */
	private Date status_changed; //Date when the user's account was approved or rejected
	
	public NonAdmin(String username, String password, String name, Address address, String mobile, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setAddress(address);
		this.setMobile(mobile);
		this.setEmail(email);
		this.approval_count = 0;
		this.desc = "";
	}
	
	public NonAdmin(String username, String password, String name, String mobile, String email) {
		this.setUsername(username);
		this.setPassword(password);
		this.setName(name);
		this.setMobile(mobile);
		this.setEmail(email);
		this.approval_count = 0;
	}
	
	public NonAdmin() {
		super();
		this.approval_count = 0;
	}

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getBg_img() {
		return bg_img;
	}

	public void setBg_img(String bg_img) {
		this.bg_img = bg_img;
	}

	public int getApproval_count() {
		return approval_count;
	}

	public void setApproval_count(int approval_count) {
		this.approval_count = approval_count;
	}

	public Date getStatus_changed() {
		return status_changed;
	}

	public void setStatus_changed(Date status_changed) {
		this.status_changed = status_changed;
	}
}
