package com.cirp.app.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.TextIndexed;

public class Project {
	
	@NotBlank
	private String name;
	@NotBlank
	private String desc;
	@TextIndexed
	private List<String> tech; //Technologies used for developing the project
	
	public Project(String name, String desc, List<String> tech) {
		this.name = name;
		this.desc = desc;
		this.tech = tech;
	}

	public Project() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getTech() {
		return tech;
	}

	public void setTech(List<String> tech) {
		this.tech = tech;
	}
	
}
