/**
 * 
 */
package com.cirp.app.model;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

public abstract class User {
	
	@MongoId
	private String username;
	private String password;
	@TextIndexed
	private String name;
	private Address address;
	private String mobile;
	@Indexed(unique = true)
	private String email;
	private Boolean admin; //false, by default (not admin); true for admin users
	private int status; //-1 for rejected, 0 for pending (default), 1 for accepted
	
	public User() {
		this.setAdmin(false);
		this.setStatus(0);
	}
	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected String getPassword() {
		return password;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected Address getAddress() {
		return address;
	}

	protected void setAddress(Address address) {
		this.address = address;
	}

	protected String getMobile() {
		return mobile;
	}

	protected void setMobile(String mobile) {
		//Checking whether its a valid international mobile number
		if(mobile.matches("^\\+(?:[0-9] ?){6,14}[0-9]$"))
			this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	protected void setEmail(String email) {
		//Checking whether its a valid email
		if(email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			this.email = email;

	}

	protected Boolean getAdmin() {
		return admin;
	}

	protected void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	protected int getStatus() {
		return status;
	}

	protected void setStatus(int status) {
		//value of status should be either -1, 0 or 1
		if(status >= -1 && status <= 1)
			this.status = status;
	}
	
	
	
}
