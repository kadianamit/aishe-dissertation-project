package com.nic.aishe.master.util;

import java.io.Serializable;


public class CollegeId implements Serializable{
	
	private static final long serialVersionUID = 4199807624447134775L;
	
	private Integer id;
	private Integer surveyYear;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	
	
	
	

}
