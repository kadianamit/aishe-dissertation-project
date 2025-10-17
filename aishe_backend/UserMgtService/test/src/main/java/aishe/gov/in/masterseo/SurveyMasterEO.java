package aishe.gov.in.masterseo;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.survey_master")
public class SurveyMasterEO {	

	@Id
	@Column(name = "survey_year")
	private Integer surveyYear;
	@Column(name = "start_date")
	private Date startDate;
	@Column(name = "end_date")
	private Date endDate;
	

	public int getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(int surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	

}
