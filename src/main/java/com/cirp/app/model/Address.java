package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

public class Address {
	
	@NotBlank private String address_line1;
	private String address_line2;
	@NotBlank private String city_or_town;
	@NotBlank private String district;
	@NotBlank private String state;
	@NotBlank private String country;
	@NotBlank private Long pincode;
	
	public Address(String address_line1, String address_line2, String city_or_town, String district, String state,
			String country, Long pincode) {
		this.address_line1 = address_line1;
		this.address_line2 = address_line2;
		this.city_or_town = city_or_town;
		this.district = district;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}

	public Address() {
		super();
		
	}

	public String getAddress_line1() {
		return address_line1;
	}

	public void setAddress_line1(String address_line1) {
		this.address_line1 = address_line1;
	}

	public String getAddress_line2() {
		return address_line2;
	}

	public void setAddress_line2(String address_line2) {
		this.address_line2 = address_line2;
	}

	public String getCity_or_town() {
		return city_or_town;
	}

	public void setCity_or_town(String city_or_town) {
		this.city_or_town = city_or_town;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
}
