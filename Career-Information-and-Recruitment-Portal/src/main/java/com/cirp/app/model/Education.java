package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Education {
	
	@NotBlank
	private String course;
	@NotBlank
	private String institute; //school or college
	private String board; //board or university
	private String marks; //marks, grade value or percentage
	private int year; //year of completion
	
	public Education() {
		
	}

	public String getCourse() {
		return course;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setCourse(String course) {
		this.course = course;
	}
}
