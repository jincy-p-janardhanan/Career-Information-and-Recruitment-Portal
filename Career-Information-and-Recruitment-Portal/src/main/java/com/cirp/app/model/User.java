/**
 * 
 */
package com.cirp.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.MongoId;

public abstract class User {
	
	@MongoId
	private String username;
	@NotBlank
	@Size(min = 6, max = 24)
	private String password;
	@TextIndexed
	private String name;
	@NotNull
	private Address address;
	@NotBlank
	private String mobile;
	@NotBlank
	@Indexed(unique = true)
	private String email;
	private int status; //-1 for rejected, 0 for pending (default), 1 for accepted
	
	private Set<Role> roles = new HashSet<>();
	
	public User() {
		this.setStatus(0);
	}
	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
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

	protected int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		//value of status should be either -1, 0 or 1
		if(status >= -1 && status <= 1)
			this.status = status;
	}
	
	public Set<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}	
	
	
}
