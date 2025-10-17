package aishe.gov.in.mastersvo;

import java.util.List;

public class AccreditationVO {
	
	private String universityId;
	
	private Integer surveyYear;

	private List<AccreditationTab> accreditationTab;

	public List<AccreditationTab> getAccreditationTab() {
		return accreditationTab;
	}

	public void setAccreditationTab(List<AccreditationTab> accreditationTab) {
		this.accreditationTab = accreditationTab;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	}