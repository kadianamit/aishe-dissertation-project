package aishe.gov.in.mastersvo;

public class AisheInstituteRequestCheckNameVO {

	private String requestIdOrAisheCode;
	private String categoryName;
	private String universityOrStateBodyName;
	private String  instituteName;
	private String statusName;
	public String getRequestIdOrAisheCode() {
		return requestIdOrAisheCode;
	}
	public void setRequestIdOrAisheCode(String requestIdOrAisheCode) {
		this.requestIdOrAisheCode = requestIdOrAisheCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getUniversityOrStateBodyName() {
		return universityOrStateBodyName;
	}
	public void setUniversityOrStateBodyName(String universityOrStateBodyName) {
		this.universityOrStateBodyName = universityOrStateBodyName;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getStatusName() {
		return statusName;
	}
	
	
	@Override
	public String toString() {
		return "AisheInstituteRequestCheckNameVO [requestIdOrAisheCode=" + requestIdOrAisheCode + ", categoryName="
				+ categoryName + ", universityOrStateBodyName=" + universityOrStateBodyName + ", instituteName="
				+ instituteName + ", statusName=" + statusName + "]";
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

}