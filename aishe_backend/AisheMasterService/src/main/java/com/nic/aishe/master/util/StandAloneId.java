package com.nic.aishe.master.util;

import java.io.Serializable;

public class StandAloneId implements Serializable {

	private static final long serialVersionUID = 5930186436196130035L;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((surveyYear == null) ? 0 : surveyYear.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StandAloneId other = (StandAloneId) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (surveyYear == null) {
			return other.surveyYear == null;
		} else return surveyYear.equals(other.surveyYear);
	}
	
	
}
