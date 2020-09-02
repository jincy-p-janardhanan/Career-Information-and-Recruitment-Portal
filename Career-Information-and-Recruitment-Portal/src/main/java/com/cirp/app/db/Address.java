package com.cirp.app.db;

/**
 * @author Jincy P Janardhanan
 *
 */

class Address {
	String address_line1;
	String address_line2;
	String city_or_town;
	String district;
	String state;
	String country;
	Long pincode;
	
	protected String getAddress_line1() {
		return address_line1;
	}
	protected void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}
	protected String getAddress_line2() {
		return address_line2;
	}
	protected void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}
	protected String getCity_or_town() {
		return city_or_town;
	}
	protected void setCity_or_town(String city_or_town) {
		this.city_or_town = city_or_town;
	}
	protected String getDistrict() {
		return district;
	}
	protected void setDistrict(String district) {
		this.district = district;
	}
	protected String getState() {
		return state;
	}
	protected void setState(String state) {
		this.state = state;
	}
	protected String getCountry() {
		return country;
	}
	protected void setCountry(String country) {
		this.country = country;
	}
	protected Long getPincode() {
		return pincode;
	}
	protected void setPincode(Long pincode) {
		this.pincode = pincode;
	}
}