package aishe.gov.in.utility;

import javax.persistence.Column;

public class ReturnResponseExamResult {

	public ReturnResponseExamResult(int status,String message,Integer examResultId,Integer appearedCountId,Integer passedCountId,
			Integer firstClassPassedId,Integer courseExamResultId,String instituteId,Integer surveyYear) {
		super();
		this.message = message;
		this.status = status;
		this.examResultId = examResultId;
		this.appearedCountId = appearedCountId;
		this.passedCountId = passedCountId;
		this.firstClassPassedId = firstClassPassedId;
		this.courseExamResultId = courseExamResultId;
		this.instituteId = instituteId;
		this.surveyYear= surveyYear;
	}
	
	public ReturnResponseExamResult(int status,String message,Integer examResultId,Integer appearedCountId,Integer passedCountId,
			Integer firstClassPassedId,Integer courseExamResultId,String instituteId,Integer surveyYear,Integer facultyId,String facultyName,
            Integer departmentId,String departmentName,Boolean whetherVocationalCourse,String broadDisciplineGroupCategoryId,
	         String broadDisciplineGroupCategoryName,String levelId,String levelName,String programmeId,String programmeName,String broadDisciplineGroupId,
	        String broadDisciplineGroupName,String typeId,String typeName,String discipline,Integer	appearedTotal,Integer appearedFemale
	        ,Integer passed_total,Integer passed_female,Integer firstClassPassedTotal,Integer firstClassPassedFemale,
	        Integer passedTransgender,Integer courseId) {
		super();
		this.message = message;
		this.status = status;
		this.examResultId = examResultId;
		this.appearedCountId = appearedCountId;
		this.passedCountId = passedCountId;
		this.firstClassPassedId = firstClassPassedId;
		this.courseExamResultId = courseExamResultId;
		this.instituteId = instituteId;
		this.surveyYear= surveyYear;
		this.facultyId=facultyId;
		this.facultyName=facultyName;
		this.departmentId=departmentId;
		this.departmentName=departmentName;
		this.whetherVocationalCourse=whetherVocationalCourse;
		this.broadDisciplineGroupCategoryId=broadDisciplineGroupCategoryId;
		this.broadDisciplineGroupCategoryName=broadDisciplineGroupCategoryName;
		this.levelId=levelId;
		this.levelName=levelName;
		this.programmeId=programmeId;
		this.programmeName=programmeName;
		this.broadDisciplineGroupId=broadDisciplineGroupId;
		this.broadDisciplineGroupName=broadDisciplineGroupName;
		this.typeId=typeId;
		this.typeName=typeName;
		this.discipline = discipline;
		this.appearedTotal= appearedTotal;		
		this.appearedFemale= appearedFemale;		
		this.passed_total= passed_total;		
		this.passed_female= passed_female;		
		this.firstClassPassedTotal= firstClassPassedTotal;		
		this.firstClassPassedFemale= firstClassPassedFemale;			
		this.passedTransgender= passedTransgender;	
		this.courseId= courseId;
		
	}

	public ReturnResponseExamResult() {
	}
	// General error message about nature of error
	private String message;
	
	private int status;
	
	private Integer examResultId;
	
	private Integer appearedCountId;
	
	private Integer passedCountId;
	
	private Integer firstClassPassedId;
	
	private Integer courseExamResultId;
	
	private String instituteId;
	
	private Integer surveyYear;
	
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
/////////////////////////////////////////////////
	private Integer	appearedTotal;
	
	private Integer appearedFemale;
	
	private Integer passed_total;
	
	private Integer passed_female;
	
	private Integer firstClassPassedTotal;
	
	private Integer firstClassPassedFemale;	
	
	private Integer passedTransgender;
	
	private Integer courseId;
	
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

	public Integer getExamResultId() {
		return examResultId;
	}

	public void setExamResultId(Integer examResultId) {
		this.examResultId = examResultId;
	}

	public Integer getAppearedCountId() {
		return appearedCountId;
	}

	public void setAppearedCountId(Integer appearedCountId) {
		this.appearedCountId = appearedCountId;
	}

	public Integer getPassedCountId() {
		return passedCountId;
	}

	public void setPassedCountId(Integer passedCountId) {
		this.passedCountId = passedCountId;
	}

	public Integer getFirstClassPassedId() {
		return firstClassPassedId;
	}

	public void setFirstClassPassedId(Integer firstClassPassedId) {
		this.firstClassPassedId = firstClassPassedId;
	}

	public Integer getCourseExamResultId() {
		return courseExamResultId;
	}

	public void setCourseExamResultId(Integer courseExamResultId) {
		this.courseExamResultId = courseExamResultId;
	}

	public String getInstituteId() {
		return instituteId;
	}

	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
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

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public Integer getAppearedTotal() {
		return appearedTotal;
	}

	public void setAppearedTotal(Integer appearedTotal) {
		this.appearedTotal = appearedTotal;
	}

	public Integer getAppearedFemale() {
		return appearedFemale;
	}

	public void setAppearedFemale(Integer appearedFemale) {
		this.appearedFemale = appearedFemale;
	}

	public Integer getPassed_total() {
		return passed_total;
	}

	public void setPassed_total(Integer passed_total) {
		this.passed_total = passed_total;
	}

	public Integer getPassed_female() {
		return passed_female;
	}

	public void setPassed_female(Integer passed_female) {
		this.passed_female = passed_female;
	}

	public Integer getFirstClassPassedTotal() {
		return firstClassPassedTotal;
	}

	public void setFirstClassPassedTotal(Integer firstClassPassedTotal) {
		this.firstClassPassedTotal = firstClassPassedTotal;
	}

	public Integer getFirstClassPassedFemale() {
		return firstClassPassedFemale;
	}

	public void setFirstClassPassedFemale(Integer firstClassPassedFemale) {
		this.firstClassPassedFemale = firstClassPassedFemale;
	}

	public Integer getPassedTransgender() {
		return passedTransgender;
	}

	public void setPassedTransgender(Integer passedTransgender) {
		this.passedTransgender = passedTransgender;
	}

	public Integer getCourseId() {
		return courseId;
	}

	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}	
}