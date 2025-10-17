package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProgramDTO {
	
	
	@JsonProperty
	private String facultyName;
	@JsonProperty
	private String departmentName;
	@JsonProperty
	private String level;
	@JsonProperty
	private String nameOfProgram;
	@JsonProperty
	private String disciplineSubject;
	@JsonProperty
	private String broadDisciplineGroupCategory;
	@JsonProperty
	private String broadDisciplineGroupName;
	@JsonProperty
	private Integer approvedIntake;
	@JsonProperty
	private String admissionCriteria;
	@JsonProperty
	private Integer courseDurationYear;
	@JsonProperty
	private Integer courseDurationMonth;
	@JsonProperty
	private String type;
	@JsonProperty
	private String examinationSystem;
	@JsonProperty
	private String courseMode;	
	
	public String getCourseMode() {
		return courseMode;
	}
	public void setCourseMode(String courseMode) {
		this.courseMode = courseMode;
	}
	public String getFacultyName() {
		return facultyName;
	}
	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getNameOfProgram() {
		return nameOfProgram;
	}
	public void setNameOfProgram(String nameOfProgram) {
		this.nameOfProgram = nameOfProgram;
	}
	public String getDisciplineSubject() {
		return disciplineSubject;
	}
	public void setDisciplineSubject(String disciplineSubject) {
		this.disciplineSubject = disciplineSubject;
	}
	public String getBroadDisciplineGroupCategory() {
		return broadDisciplineGroupCategory;
	}
	public void setBroadDisciplineGroupCategory(String broadDisciplineGroupCategory) {
		this.broadDisciplineGroupCategory = broadDisciplineGroupCategory;
	}
	public String getBroadDisciplineGroupName() {
		return broadDisciplineGroupName;
	}
	public void setBroadDisciplineGroupName(String broadDisciplineGroupName) {
		this.broadDisciplineGroupName = broadDisciplineGroupName;
	}
	public Integer getApprovedIntake() {
		return approvedIntake;
	}
	public void setApprovedIntake(Integer approvedIntake) {
		this.approvedIntake = approvedIntake;
	}
	public String getAdmissionCriteria() {
		return admissionCriteria;
	}
	public void setAdmissionCriteria(String admissionCriteria) {
		this.admissionCriteria = admissionCriteria;
	}
	
	public Integer getCourseDurationYear() {
		return courseDurationYear;
	}
	public void setCourseDurationYear(Integer courseDurationYear) {
		this.courseDurationYear = courseDurationYear;
	}
	public Integer getCourseDurationMonth() {
		return courseDurationMonth;
	}
	public void setCourseDurationMonth(Integer courseDurationMonth) {
		this.courseDurationMonth = courseDurationMonth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getExaminationSystem() {
		return examinationSystem;
	}
	public void setExaminationSystem(String examinationSystem) {
		this.examinationSystem = examinationSystem;
	}

	
	
}
