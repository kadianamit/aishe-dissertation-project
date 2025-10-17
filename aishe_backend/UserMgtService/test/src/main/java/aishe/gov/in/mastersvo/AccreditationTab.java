package aishe.gov.in.mastersvo;

public class AccreditationTab {
	
private String universityId;

private Long Id;

private Boolean isInstitutionAccredited;

private String accrediationBodyId;

private String name;

private Boolean isScoreProvided;

private Integer maxScore;

private Double score;

public String getUniversityId() {
	return universityId;
}

public void setUniversityId(String universityId) {
	this.universityId = universityId;
}

public Long getId() {
	return Id;
}

public void setId(Long id) {
	Id = id;
}

public Boolean getIsInstitutionAccredited() {
	return isInstitutionAccredited;
}

public void setIsInstitutionAccredited(Boolean isInstitutionAccredited) {
	this.isInstitutionAccredited = isInstitutionAccredited;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}



public Boolean getIsScoreProvided() {
	return isScoreProvided;
}

public void setIsScoreProvided(Boolean isScoreProvided) {
	this.isScoreProvided = isScoreProvided;
}

public Integer getMaxScore() {
	return maxScore;
}

public void setMaxScore(Integer maxScore) {
	this.maxScore = maxScore;
}

public String getAccrediationBodyId() {
	return accrediationBodyId;
}

public void setAccrediationBodyId(String accrediationBodyId) {
	this.accrediationBodyId = accrediationBodyId;
}

public Double getScore() {
	return score;
}

public void setScore(Double score) {
	this.score = score;
}
	}