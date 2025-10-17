package aishe.gov.in.mastersvo;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PersonDetailsEmadedPK implements Serializable {

	@Column(name="survey_year")
	private Integer surveyYear;
	@Column(name="aishe_code")
	private String aisheCode;
	@Column(name="inst_type")
	private String instituteType;
	@Column(name = "officer_type")
	private String officerType;
	public PersonDetailsEmadedPK() {

	}

	public PersonDetailsEmadedPK(String aisheCode, Integer surveyYear,String instituteType,String officerType) {
		this.aisheCode = aisheCode;
		this.surveyYear = surveyYear;
		this.instituteType = instituteType;
		this.officerType = officerType;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}

	public String getInstituteType() {
		return instituteType;
	}

	public void setInstituteType(String instituteType) {
		this.instituteType = instituteType;
	}

	public String getOfficerType() {
		return officerType;
	}

	public void setOfficerType(String officerType) {
		this.officerType = officerType;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof PersonDetailsEmadedPK))
			return false;
		PersonDetailsEmadedPK that = (PersonDetailsEmadedPK) o;
		return Objects.equals(getSurveyYear(), that.getSurveyYear()) && Objects.equals(getAisheCode(), that.getAisheCode()) && Objects.equals(getInstituteType(), that.getInstituteType())
				&& Objects.equals(getOfficerType(), that.getOfficerType());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getSurveyYear(),getAisheCode(),getInstituteType(),getOfficerType());
	}

}
