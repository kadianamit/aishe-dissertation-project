package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "university_non_teaching_staff_count")
public class UniversityNonTeachingStaffCount {
	
	@Id
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@ManyToOne
	@JoinColumn(name = "non_teaching_staff_count_id", insertable = false, updatable = false)
	private NonTeachingStaffCount nonTeachingStaffCount;
	
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
	public NonTeachingStaffCount getNonTeachingStaffCount() {
		return nonTeachingStaffCount;
	}
	public void setNonTeachingStaffCount(NonTeachingStaffCount nonTeachingStaffCount) {
		this.nonTeachingStaffCount = nonTeachingStaffCount;
	}
	

}
