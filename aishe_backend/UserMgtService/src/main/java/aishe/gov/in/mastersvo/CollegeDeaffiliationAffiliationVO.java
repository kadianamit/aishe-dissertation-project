package aishe.gov.in.mastersvo;

public class CollegeDeaffiliationAffiliationVO {
    private String collegeAisheCode;
    private String universityAisheCode;
    private Integer surveyYear;
    private Integer reasonId;
   // private String username = "";
    private String ipAddress = "";
    private String secretKey;
    private Integer roleId;
    public CollegeDeaffiliationAffiliationVO() {
    }

    public String getCollegeAisheCode() {
        return collegeAisheCode;
    }

    public void setCollegeAisheCode(String collegeAisheCode) {
        this.collegeAisheCode = collegeAisheCode;
    }

    public String getUniversityAisheCode() {
        return universityAisheCode;
    }

    public void setUniversityAisheCode(String universityAisheCode) {
        this.universityAisheCode = universityAisheCode;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public Integer getReasonId() {
        return reasonId;
    }

    public void setReasonId(Integer reasonId) {
        this.reasonId = reasonId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public static CollegeDeaffiliationAffiliationVO bindSurveyYear(CollegeDeaffiliationAffiliationVO affiliationVO, Integer surveyYear) {
        affiliationVO.setSurveyYear(surveyYear);
        return affiliationVO;
    }

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}