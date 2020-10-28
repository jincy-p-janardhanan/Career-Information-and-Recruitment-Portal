package com.cirp.app.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "message")
public class Message {
	
	@Id
	private String id;
	private String message;
	private String sender;
	private String receiver;
	private String channelid;
	private String timestamp;
	public Message(String id, String message, String sender, String receiver, String channelid,
			String timestamp) {
		this.id = id;
		this.message = message;
		this.sender = sender;
		this.receiver = receiver;
		this.channelid = channelid;
		this.timestamp = timestamp;
	}
	public Message() {
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getChannelid() {
		return channelid;
	}
	public void setChannelid(String channelid) {
		this.channelid = channelid;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
}
