package com.cirp.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ChatChannel {
	@Id
	private String id;
	private String user1;
	private String nameUser1;
	private String profilePicUser1;
	private String user2;
	private String nameUser2;
	private String profilePicUser2;
	public ChatChannel() {
		
	}
	public ChatChannel(String id, String user1, String nameUser1, String profilePicUser1, String user2,
			String nameUser2, String profilePicUser2) {
		this.id = id;
		this.user1 = user1;
		this.nameUser1 = nameUser1;
		this.profilePicUser1 = profilePicUser1;
		this.user2 = user2;
		this.nameUser2 = nameUser2;
		this.profilePicUser2 = profilePicUser2;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getNameUser1() {
		return nameUser1;
	}
	public void setNameUser1(String nameUser1) {
		this.nameUser1 = nameUser1;
	}
	public String getProfilePicUser1() {
		return profilePicUser1;
	}
	public void setProfilePicUser1(String profilePicUser1) {
		this.profilePicUser1 = profilePicUser1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public String getNameUser2() {
		return nameUser2;
	}
	public void setNameUser2(String nameUser2) {
		this.nameUser2 = nameUser2;
	}
	public String getProfilePicUser2() {
		return profilePicUser2;
	}
	public void setProfilePicUser2(String profilePicUser2) {
		this.profilePicUser2 = profilePicUser2;
	}
	
}
