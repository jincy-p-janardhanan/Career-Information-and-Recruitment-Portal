package com.cirp.app.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.MongoId;

public abstract class User {
	
	@MongoId
	private String username;
	@NotBlank
	@Size(min = 6, max = 24)
	private String password;
	@TextIndexed
	private String name;
	
	private Address address;
	
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$")
	private String mobile;
	@NotBlank
	@Indexed(unique = true)
	@Pattern(regexp = "/^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$/")
	private String email;
	private int status; //-1 for rejected, 0 for pending (default), 1 for accepted
	
	private String role;
	
	private String token; //used for deactivation, reset password and any kind of confirmation
	
	public User() {
		this.setStatus(0);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
