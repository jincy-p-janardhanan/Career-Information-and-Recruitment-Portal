package com.cirp.app.model;

import javax.validation.constraints.NotBlank;

class Education {
	@NotBlank
	private String course;
	@NotBlank
	private String institute; //school or college
	private String board; //board or university
	private float marks; //marks, grade value or percentage
	private int year; //year of completion
	
	public Education(String course, String institute, String board, float marks, int year) {
		this.course = course;
		this.institute = institute;
		this.board = board;
		this.marks = marks;
		this.year = year;
	}

	protected String getCourse() {
		return course;
	}

	protected void setCourse(String course) {
		this.course = course;
	}

	protected String getInstitute() {
		return institute;
	}

	protected void setInstitute(String institute) {
		this.institute = institute;
	}

	protected String getBoard() {
		return board;
	}

	protected void setBoard(String board) {
		this.board = board;
	}

	protected float getMarks() {
		return marks;
	}

	protected void setMarks(float marks) {
		this.marks = marks;
	}

	protected int getYear() {
		return year;
	}

	protected void setYear(int year) {
		this.year = year;
	}
}
