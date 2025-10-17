package aishe.gov.in.utility;

public class ReturnResponseEnrollment {

	public ReturnResponseEnrollment(int status,String message,Integer enrollmentId,Integer courseEnrollStudentCountId,
			Integer courseId,String universityId,Integer surveyYear,Integer countByCategoryId) {
		super();
		this.message = message;
		this.status = status;
		this.enrollmentId = enrollmentId;
		this.courseEnrollStudentCountId = courseEnrollStudentCountId;
		this.courseId = courseId;
		this.universityId = universityId;
		this.surveyYear= surveyYear;
		this.countByCategoryId= countByCategoryId;
	}
	
	public ReturnResponseEnrollment(int status,String message,Integer enrollmentId,Integer courseEnrollStudentCountId,
			Integer courseId,String universityId,Integer surveyYear,Integer countByCategoryId,Integer facultyId,String facultyName,
     Integer departmentId,String departmentName,Boolean whetherVocationalCourse,String broadDisciplineGroupCategoryId, String broadDisciplineGroupCategoryName
	 ,String levelId,String levelName,String programmeId,String programmeName,String broadDisciplineGroupId,String broadDisciplineGroupName,
	 String typeId, String typeName,String discipline, String year,Integer totalPerson,Integer totalFemale,Integer totalTransgender) {
		super();
		this.message = message;
		this.status = status;
		this.enrollmentId = enrollmentId;
		this.courseEnrollStudentCountId = courseEnrollStudentCountId;
		this.courseId = courseId;
		this.universityId = universityId;
		this.surveyYear= surveyYear;
		this.countByCategoryId= countByCategoryId;
		this.facultyId=facultyId;
		this.facultyName= facultyName;
		this.departmentId = departmentId;
		this.departmentName =departmentName;
		this.whetherVocationalCourse = whetherVocationalCourse;
		this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
		this.broadDisciplineGroupCategoryName = broadDisciplineGroupCategoryName;
		this.levelId = levelId;
		this.levelName =levelName;
		this.programmeId = programmeId;
		this.programmeName = programmeName;
		this.broadDisciplineGroupId = broadDisciplineGroupId;
		this.broadDisciplineGroupName = broadDisciplineGroupName;
		this.typeId = typeId;
		this.typeName =typeName;
		this.discipline=discipline;
		this.year=year;
		this.totalPerson = totalPerson;
		this.totalFemale= totalFemale;
		this.totalTransgender= totalTransgender;
	}
	
	public ReturnResponseEnrollment() {
		
	}

	// General error message about nature of error
	private String message;
	
	private int status;
	
	private Integer enrollmentId;
	
	private Integer courseEnrollStudentCountId;
	
	private Integer courseId;
	
	private String universityId;
	
	private Integer surveyYear;

	private Integer countByCategoryId;
	
    private Integer facultyId;
	
	private String facultyName;
	
	private Integer departmentId;
	
	private String departmentName;
	
	private Boolean whetherVocationalCourse;
	
	private String broadDisciplineGroupCategoryId;
	
	private String broadDisciplineGroupCategoryName;
	
	private String levelId;
	
	private String levelName;
	
	private String programmeId;
	
	private String programmeName;
	
    private String broadDisciplineGroupId;
	
	private String broadDisciplineGroupName;
	
    private String typeId;
	
	private String typeName;
	
	private String discipline;
	
	private Integer totalPerson;
	
	private Integer totalFemale;
	
	private Integer totalTransgender;
	
	private String year;
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Integer getEnrollmentId() {
		return enrollmentId;
	}

	public void setEnrollmentId(Integer enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	public Integer getCourseEnrollStudentCountId() {
		return courseEnrollStudentCountId;
	}

	public void setCourseEnrollStudentCountId(Integer courseEnrollStudentCountId) {
		this.courseEnrollStudentCountId = courseEnrollStudentCountId;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
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

	public Integer getCountByCategoryId() {
		return countByCategoryId;
	}

	public void setCountByCategoryId(Integer countByCategoryId) {
		this.countByCategoryId = countByCategoryId;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Boolean getWhetherVocationalCourse() {
		return whetherVocationalCourse;
	}

	public void setWhetherVocationalCourse(Boolean whetherVocationalCourse) {
		this.whetherVocationalCourse = whetherVocationalCourse;
	}

	public String getBroadDisciplineGroupCategoryId() {
		return broadDisciplineGroupCategoryId;
	}

	public void setBroadDisciplineGroupCategoryId(String broadDisciplineGroupCategoryId) {
		this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
	}

	public String getBroadDisciplineGroupCategoryName() {
		return broadDisciplineGroupCategoryName;
	}

	public void setBroadDisciplineGroupCategoryName(String broadDisciplineGroupCategoryName) {
		this.broadDisciplineGroupCategoryName = broadDisciplineGroupCategoryName;
	}

	public String getLevelId() {
		return levelId;
	}

	public void setLevelId(String levelId) {
		this.levelId = levelId;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(String programmeId) {
		this.programmeId = programmeId;
	}

	public String getProgrammeName() {
		return programmeName;
	}

	public void setProgrammeName(String programmeName) {
		this.programmeName = programmeName;
	}

	public String getBroadDisciplineGroupId() {
		return broadDisciplineGroupId;
	}

	public void setBroadDisciplineGroupId(String broadDisciplineGroupId) {
		this.broadDisciplineGroupId = broadDisciplineGroupId;
	}

	public String getBroadDisciplineGroupName() {
		return broadDisciplineGroupName;
	}

	public void setBroadDisciplineGroupName(String broadDisciplineGroupName) {
		this.broadDisciplineGroupName = broadDisciplineGroupName;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public Integer getTotalPerson() {
		return totalPerson;
	}

	public void setTotalPerson(Integer totalPerson) {
		this.totalPerson = totalPerson;
	}

	public Integer getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	public Integer getTotalTransgender() {
		return totalTransgender;
	}

	public void setTotalTransgender(Integer totalTransgender) {
		this.totalTransgender = totalTransgender;
	}	
	
	
}