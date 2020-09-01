/**
 * 
 */
package com.cirp.app.db;

/**
 * @author Jincy P Janardhanan
 *
 */

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public abstract class User {
	
	@Id
	private ObjectId _id;
	private String username;
	private String password;
	private String name;
	private Address address;
	private String mobile;
	private String email;
	private String profile_pic;
	private int role;
	private int status;
	
	public User() {
		this.setRole(1); // 0 for admin, 1 for others
		this.setStatus(-1); //-1 for pending, 0 for rejected, 1 for accepted
	}
	
	//Getters
	protected ObjectId get_id() {
		return _id;
	}

	protected String getUsername() {
		return username;
	}

	protected String getPassword() {
		return password;
	}

	protected String getName() {
		return name;
	}

	protected Address getAddress() {
		return address;
	}

	protected String getMobile() {
		return mobile;
	}

	protected String getEmail() {
		return email;
	}

	protected String getProfile_pic() {
		return profile_pic;
	}

	protected int getRole() {
		return role;
	}

	protected int getStatus() {
		return status;
	}

	//Setters
	protected void set_id(ObjectId _id) {
		this._id = _id;
	}

	protected void setUsername(String username) {
		this.username = username;
	}

	protected void setPassword(String password) {
		this.password = password;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected void setAddress(Address address) {
		this.address = address;
	}

	protected void setMobile(String mobile) {
		this.mobile = mobile;
	}

	protected void setEmail(String email) {
		this.email = email;
	}

	protected void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	protected void setRole(int role) {
		this.role = role;
	}

	protected void setStatus(int status) {
		this.status = status;
	}
	
	
}
