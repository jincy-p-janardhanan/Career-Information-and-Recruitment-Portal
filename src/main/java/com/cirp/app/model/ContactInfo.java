
package com.cirp.app.model;

import javax.validation.constraints.Pattern;

//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document
public class ContactInfo extends Address{
	
	private String website;
	private String landph;
	@Pattern(regexp = "/^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$/")
	private String public_email;			//email used by public for support, enquires, etc
	
	public ContactInfo(String address_line1, String address_line2, String city_or_town, String district, String state,
			String country, Long pincode) {
		super(address_line1, address_line2, city_or_town, district, state, country, pincode);
	}

	public ContactInfo(String address_line1, String address_line2, String city_or_town, String district, String state,
			String country, Long pincode, String website, String landph, String public_email) {
		super(address_line1, address_line2, city_or_town, district, state, country, pincode);
		this.website = website;
		this.landph = landph;
		this.public_email = public_email;
		
	}

	
	public ContactInfo() {
		
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getLandph() {
		return landph;
	}

	public void setLandph(String landph) {
		this.landph = landph;
	}

	public String getPublic_email() {
		return public_email;
	}

	public void setPublic_email(String public_email) {
		this.public_email = public_email;
	}	

	
}
