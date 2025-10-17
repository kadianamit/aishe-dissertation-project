package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.college_institution_request_details")
public class CollegeInstitutionRequestDetailsEO {

	@Id
	@Column(name = "request_id")
	private Integer  requestId;
	@Column(name = "survey_year")
	private Integer  surveyYear;
	@Column(name = "name")
	private String  Name ;
	@Column(name = "state_code")
	private String stateCode;
	@Column(name = "district_code")
	private String districtCode;
	@Column(name = "college_type_id")
	private String  collegeTypeId;
	@Column(name = "university_id")
	private String  universityId;
	@Column(name = "location_id")
	private String  locationId;
	@Column(name = "management_id")
	private String managementId;
	@Column(name = "admission_year")
	private String admissionYear;
	@Column(name = "created_college_institution_id")
	private Integer  createdCollegeInstitutionId;
	@Column(name = "created_survey_year")
	private Integer  createdSurveyYear;
	@Column(name = "is_affiliated_to_other_university")
	private String  isAffiliatedToOtherUniversity;
	@Column(name = "other_affiliated_university_name")
	private String otherAffiliatedUniversityName;
	@Column(name = "admission_process_completed")
	private String admissionProcessCompleted;
	public Integer getRequestId() {
		return requestId;
	}
	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getDistrictCode() {
		return districtCode;
	}
	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}
	public String getCollegeTypeId() {
		return collegeTypeId;
	}
	public void setCollegeTypeId(String collegeTypeId) {
		this.collegeTypeId = collegeTypeId;
	}
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getLocationId() {
		return locationId;
	}
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}
	public String getManagementId() {
		return managementId;
	}
	public void setManagementId(String managementId) {
		this.managementId = managementId;
	}
	public String getAdmissionYear() {
		return admissionYear;
	}
	public void setAdmissionYear(String admissionYear) {
		this.admissionYear = admissionYear;
	}
	public Integer getCreatedCollegeInstitutionId() {
		return createdCollegeInstitutionId;
	}
	public void setCreatedCollegeInstitutionId(Integer createdCollegeInstitutionId) {
		this.createdCollegeInstitutionId = createdCollegeInstitutionId;
	}
	public Integer getCreatedSurveyYear() {
		return createdSurveyYear;
	}
	public void setCreatedSurveyYear(Integer createdSurveyYear) {
		this.createdSurveyYear = createdSurveyYear;
	}
	public String getIsAffiliatedToOtherUniversity() {
		return isAffiliatedToOtherUniversity;
	}
	public void setIsAffiliatedToOtherUniversity(String isAffiliatedToOtherUniversity) {
		this.isAffiliatedToOtherUniversity = isAffiliatedToOtherUniversity;
	}
	public String getOtherAffiliatedUniversityName() {
		return otherAffiliatedUniversityName;
	}
	public void setOtherAffiliatedUniversityName(String otherAffiliatedUniversityName) {
		this.otherAffiliatedUniversityName = otherAffiliatedUniversityName;
	}
	public String getAdmissionProcessCompleted() {
		return admissionProcessCompleted;
	}
	public void setAdmissionProcessCompleted(String admissionProcessCompleted) {
		this.admissionProcessCompleted = admissionProcessCompleted;
	}
	}