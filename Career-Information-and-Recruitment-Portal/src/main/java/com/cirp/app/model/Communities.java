package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

public class Communities {

	@NotBlank
	private String name;
	private String website;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
}
