package aishe.gov.in.mastersvo;

public class RegulatoryInformationVO {

private String universityId;

private Boolean isUniversityUploadStatusOnWebsite;

private Boolean isUniversityComplyingUgcRule;

private Boolean isUniversityMinimumActualTeachingDays;

private Integer surveyYear;

public String getUniversityId() {
	return universityId;
}

public void setUniversityId(String universityId) {
	this.universityId = universityId;
}

public Boolean getIsUniversityUploadStatusOnWebsite() {
	return isUniversityUploadStatusOnWebsite;
}

public void setIsUniversityUploadStatusOnWebsite(Boolean isUniversityUploadStatusOnWebsite) {
	this.isUniversityUploadStatusOnWebsite = isUniversityUploadStatusOnWebsite;
}

public Boolean getIsUniversityComplyingUgcRule() {
	return isUniversityComplyingUgcRule;
}

public void setIsUniversityComplyingUgcRule(Boolean isUniversityComplyingUgcRule) {
	this.isUniversityComplyingUgcRule = isUniversityComplyingUgcRule;
}

public Boolean getIsUniversityMinimumActualTeachingDays() {
	return isUniversityMinimumActualTeachingDays;
}

public void setIsUniversityMinimumActualTeachingDays(Boolean isUniversityMinimumActualTeachingDays) {
	this.isUniversityMinimumActualTeachingDays = isUniversityMinimumActualTeachingDays;
}

public Integer getSurveyYear() {
	return surveyYear;
}

public void setSurveyYear(Integer surveyYear) {
	this.surveyYear = surveyYear;
}
}