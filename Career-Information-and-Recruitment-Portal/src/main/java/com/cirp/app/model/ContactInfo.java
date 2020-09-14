
package com.cirp.app.model;

/**
 * @author Jincy P Janardhanan
 *
 */
public class ContactInfo extends Address{
	
	private String website;
	private String landph;
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
		this.setPublic_email(public_email);
		
	}

	protected String getWebsite() {
		return website;
	}

	protected void setWebsite(String website) {
		this.website = website;
	}

	protected String getLandph() {
		return landph;
	}

	protected void setLandph(String landph) {
		this.landph = landph;
	}

	protected String getPublic_email() {
		return public_email;
	}

	protected void setPublic_email(String public_email) {
		if(public_email.matches("^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$"))
			this.public_email = public_email;
	}
}
