package aishe.gov.in.mastersvo;

public class UniversityOffShoreCenterVO {
	private String universityId;
	private Integer surveyYear;
	private Boolean isOffShoreCentreAvail;
	private Integer noOffOffShoreCentre;
	private Integer Id;
	private String name;
	private Integer countryId;
	private Integer studyModeId;
	private Integer totalNoOfStudent;
	private Integer totalGirl;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	public Boolean getIsOffShoreCentreAvail() {
		return isOffShoreCentreAvail;
	}
	public void setIsOffShoreCentreAvail(Boolean isOffShoreCentreAvail) {
		this.isOffShoreCentreAvail = isOffShoreCentreAvail;
	}
	public Integer getNoOffOffShoreCentre() {
		return noOffOffShoreCentre;
	}
	public void setNoOffOffShoreCentre(Integer noOffOffShoreCentre) {
		this.noOffOffShoreCentre = noOffOffShoreCentre;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStudyModeId() {
		return studyModeId;
	}
	public void setStudyModeId(Integer studyModeId) {
		this.studyModeId = studyModeId;
	}
	public Integer getTotalNoOfStudent() {
		return totalNoOfStudent;
	}
	public void setTotalNoOfStudent(Integer totalNoOfStudent) {
		this.totalNoOfStudent = totalNoOfStudent;
	}
	public Integer getTotalGirl() {
		return totalGirl;
	}
	public void setTotalGirl(Integer totalGirl) {
		this.totalGirl = totalGirl;
	}
}