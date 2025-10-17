package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.standalone_institution_request_details")
public class StandaloneInstitutionRequestDetailsEO {

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
	@Column(name = "state_body_id")
	private Integer  stateBodyId;
	@Column(name = "location_id")
	private String  locationId;
	@Column(name = "management_id")
	private String managementId;
	@Column(name = "admission_year")
	private String admissionYear;
	@Column(name = "university_id")
	private String universityId;
	@Column(name = "is_affiliated_to_university")
	private String  isAffiliatedToUniversity;
	@Column(name = "created_standalone_institution_id")
	private Integer  createdStandaloneInstitutionId;
	@Column(name = "created_survey_year")
	private Integer  createdSurveyYear;
	@Column(name = "ministry_id")
	private Integer ministryId;
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
	public Integer getStateBodyId() {
		return stateBodyId;
	}
	public void setStateBodyId(Integer stateBodyId) {
		this.stateBodyId = stateBodyId;
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
	public String getUniversityId() {
		return universityId;
	}
	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
	public String getIsAffiliatedToUniversity() {
		return isAffiliatedToUniversity;
	}
	public void setIsAffiliatedToUniversity(String isAffiliatedToUniversity) {
		this.isAffiliatedToUniversity = isAffiliatedToUniversity;
	}
	public Integer getCreatedStandaloneInstitutionId() {
		return createdStandaloneInstitutionId;
	}
	public void setCreatedStandaloneInstitutionId(Integer createdStandaloneInstitutionId) {
		this.createdStandaloneInstitutionId = createdStandaloneInstitutionId;
	}
	public Integer getCreatedSurveyYear() {
		return createdSurveyYear;
	}
	public void setCreatedSurveyYear(Integer createdSurveyYear) {
		this.createdSurveyYear = createdSurveyYear;
	}
	public Integer getMinistryId() {
		return ministryId;
	}
	public void setMinistryId(Integer ministryId) {
		this.ministryId = ministryId;
	}
	public String getAdmissionProcessCompleted() {
		return admissionProcessCompleted;
	}
	public void setAdmissionProcessCompleted(String admissionProcessCompleted) {
		this.admissionProcessCompleted = admissionProcessCompleted;
	}
	}