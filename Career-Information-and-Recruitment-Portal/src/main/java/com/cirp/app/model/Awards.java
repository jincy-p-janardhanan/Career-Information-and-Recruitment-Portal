package com.cirp.app.model;

import java.time.Year;

import javax.validation.constraints.NotBlank;

public class Awards {
	
	@NotBlank
	private String name;
	private Year year;
	private String website_of_org;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}
	
	public String getWebsite_of_org() {
		return website_of_org;
	}

	public void setWebsite_of_org(String website_of_org) {
		this.website_of_org = website_of_org;
	}

}
