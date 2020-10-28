package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Awards {

	@NotBlank
	private String name;
	private int year;
	private String website_of_org;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getWebsite_of_org() {
		return website_of_org;
	}

	public void setWebsite_of_org(String website_of_org) {
		this.website_of_org = website_of_org;
	}

}
