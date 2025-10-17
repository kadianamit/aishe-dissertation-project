package aishe.gov.in.mastersvo;

public class NewInstituteRequestCheckNameVO {

	private String requestIdOrAisheCode;
	private Integer surveyYear;
	private String  instituteName;
	private String universityOrStateBodyName;
	private String categoryName;
	private String userId;
	private String roleName;
	private String statusName;
	public String getRequestIdOrAisheCode() {
		return requestIdOrAisheCode;
	}
	public void setRequestIdOrAisheCode(String requestIdOrAisheCode) {
		this.requestIdOrAisheCode = requestIdOrAisheCode;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getUniversityOrStateBodyName() {
		return universityOrStateBodyName;
	}
	public void setUniversityOrStateBodyName(String universityOrStateBodyName) {
		this.universityOrStateBodyName = universityOrStateBodyName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}	
}