package aishe.gov.in.mastersvo;

public class UnlockVO {
	private String  institutionType;
	private Integer surveyYear;
	private String aishecode;
	private String userKey;
	private String userName;
	private String remarks;
	public String getInstitutionType() {
		return institutionType;
	}
	public void setInstitutionType(String institutionType) {
		this.institutionType = institutionType;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getAishecode() {
		return aishecode;
	}
	public void setAishecode(String aishecode) {
		this.aishecode = aishecode;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}