/**
 * 
 */
package com.cirp.app.model;

import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.mongodb.core.index.TextIndexed;

/**
 * @author Jincy P Janardhanan
 *
 */
class Project {
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

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected String getDesc() {
		return desc;
	}

	protected void setDesc(String desc) {
		this.desc = desc;
	}

	protected List<String> getTech() {
		return tech;
	}

	protected void setTech(List<String> tech) {
		this.tech = tech;
	}
	
	
}
