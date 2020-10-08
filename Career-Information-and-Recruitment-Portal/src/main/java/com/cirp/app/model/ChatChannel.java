package com.cirp.app.model;

import org.springframework.data.annotation.Id;

public class ChatChannel {
	@Id
	private String id;
	private String firstUser;
	private String secondUser;
	public ChatChannel() {
		
	}
	public ChatChannel(String firstUser, String secondUser) {
		super();
		this.firstUser = firstUser;
		this.secondUser = secondUser;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstUser() {
		return firstUser;
	}
	public void setFirstUser(String firstUser) {
		this.firstUser = firstUser;
	}
	public String getSecondUser() {
		return secondUser;
	}
	public void setSecondUser(String secondUser) {
		this.secondUser = secondUser;
	}
}
