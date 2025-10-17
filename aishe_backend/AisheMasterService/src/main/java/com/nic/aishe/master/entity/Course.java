package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="course")
public class Course {
	@Id	
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Transient
	private Integer surveyYear;
	@Transient
	private Integer aisheCode;
	
	
	   
//    @Column(name="mode_id")
//    private String modeId;
    
    @ManyToOne
    
	@JoinColumn(name="mode_id",referencedColumnName = "id")
	private CourseMode modeId;
	
//	@Column(name="programme_id")
//    private String programmeId;
    
    @ManyToOne
    
	@JoinColumn(name="programme_id",referencedColumnName = "id")
	private ProgramName programmeId;
	
//	@Column(name="level_id")
//    private String levelId;
    
	@ManyToOne
	
	@JoinColumn(name="level_id",referencedColumnName = "id")
	private CourseLevel levelId;
    
    @Column(name="only_through_colleges")
    private Boolean onlyByColleges;
    
    @Column(name="discipline")
    private String discipline;
    
    @OneToOne
    @JoinColumn(name="broad_discipline_group")
    private BroadName broadDisciplineGroup;
    
    @Column(name="intake")
    private Integer intake;
    
    @Column(name="no_of_applicants")
    private Integer applicants;
    
    @Column(name="duration_year")
    private Integer durationYear;
    
    @Column(name="duration_month")
    private Integer durationMonth;
    
//    @Column(name="type_id")
//    private String typeId;
    
    @ManyToOne
    
	@JoinColumn(name="type_id",referencedColumnName = "id")
	private CourseType typeId;
    
//    @Column(name="examination_system_id")
//    private String examinationSystemId;
    
    @ManyToOne
    
   	@JoinColumn(name="examination_system_id",referencedColumnName = "id")
   	private ExamSystem examSystemId;
    
//    @Column(name="approving_statutory_body_id")
//    private String approvingStatutoryBodyId;
    
    @ManyToOne
    
	@JoinColumn(name="approving_statutory_body_id",referencedColumnName = "id")
	private ProgramStatutoryBody statutoryBodyId;
    

    @Column(name="approving_university_id")
    private String approvingUniversityId;
    
//	@ManyToOne
//	@JoinColumn(name="approving_university_id",referencedColumnName = "id")
//	private University universityId;
    
//    @Column(name="broad_discipline_group_category_id")
//    private String broadDisciplineGroupCategoryId;
    
    @OneToOne
    
	@JoinColumn(name="broad_discipline_group_category_id",referencedColumnName = "id")
	private BroadCategory broadCategory;
    
//    @Column(name="admission_criterion_id")
//    private Integer admissionCriterionId;
    
    @ManyToOne
    
	@JoinColumn(name="admission_criterion_id",referencedColumnName = "id")
	private AdmissionCriterion admissionCriterionId;
    
	@OneToOne	
	@JoinTable(name = "department_course",
	joinColumns = @JoinColumn(name="course_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="department_id",referencedColumnName = "id"))
	private Department department;

	@OneToOne	
	@JoinTable(name = "faculty_course",
	joinColumns = @JoinColumn(name="course_id",referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name="faculty_id",referencedColumnName = "id"))
	private Faculty faculty;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(name = "college_institution_course", joinColumns = {
			@JoinColumn(name = "course_id", referencedColumnName = "id")}, 
	inverseJoinColumns = {
	@JoinColumn(name = "college_institution_id", referencedColumnName = "id"),
	@JoinColumn(name = "survey_year", referencedColumnName = "survey_year") })
	private CollegeInfo collegeInfo;

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CourseMode getModeId() {
		return modeId;
	}

	public void setModeId(CourseMode modeId) {
		this.modeId = modeId;
	}

	public ProgramName getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(ProgramName programmeId) {
		this.programmeId = programmeId;
	}

	public CourseLevel getLevelId() {
		return levelId;
	}

	public void setLevelId(CourseLevel levelId) {
		this.levelId = levelId;
	}

	public Boolean getOnlyByColleges() {
		return onlyByColleges;
	}

	public void setOnlyByColleges(Boolean onlyByColleges) {
		this.onlyByColleges = onlyByColleges;
	}

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public BroadName getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}

	public void setBroadDisciplineGroup(BroadName broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}

	public Integer getIntake() {
		return intake;
	}

	public void setIntake(Integer intake) {
		this.intake = intake;
	}

	public Integer getApplicants() {
		return applicants;
	}

	public void setApplicants(Integer applicants) {
		this.applicants = applicants;
	}

	public Integer getDurationYear() {
		return durationYear;
	}

	public void setDurationYear(Integer durationYear) {
		this.durationYear = durationYear;
	}

	public Integer getDurationMonth() {
		return durationMonth;
	}

	public void setDurationMonth(Integer durationMonth) {
		this.durationMonth = durationMonth;
	}

	public CourseType getTypeId() {
		return typeId;
	}

	public void setTypeId(CourseType typeId) {
		this.typeId = typeId;
	}

	public ExamSystem getExamSystemId() {
		return examSystemId;
	}

	public void setExamSystemId(ExamSystem examSystemId) {
		this.examSystemId = examSystemId;
	}

	public ProgramStatutoryBody getStatutoryBodyId() {
		return statutoryBodyId;
	}

	public void setStatutoryBodyId(ProgramStatutoryBody statutoryBodyId) {
		this.statutoryBodyId = statutoryBodyId;
	}

	public String getApprovingUniversityId() {
		return approvingUniversityId;
	}

	public void setApprovingUniversityId(String approvingUniversityId) {
		this.approvingUniversityId = approvingUniversityId;
	}

//	public String getBroadDisciplineGroupCategoryId() {
//		return broadDisciplineGroupCategoryId;
//	}
//
//	public void setBroadDisciplineGroupCategoryId(String broadDisciplineGroupCategoryId) {
//		this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
//	}
	
	public AdmissionCriterion getAdmissionCriterionId() {
		return admissionCriterionId;
	}

	public BroadCategory getBroadCategory() {
		return broadCategory;
	}

	public void setBroadCategory(BroadCategory broadCategory) {
		this.broadCategory = broadCategory;
	}

	public void setAdmissionCriterionId(AdmissionCriterion admissionCriterionId) {
		this.admissionCriterionId = admissionCriterionId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public void addCollegeInfo(CollegeInfo collegeInfo) {
		this.collegeInfo = collegeInfo;
	}


	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public Integer getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(Integer aisheCode) {
		this.aisheCode = aisheCode;
	}
	
	

	public CollegeInfo findCollegeInfo() {
		return collegeInfo;
	}

	

	@Override
	public String toString() {
		return "Course [id=" + id + ", surveyYear=" + surveyYear + ", aisheCode=" + aisheCode + ", modeId=" + modeId
				+ ", programmeId=" + programmeId + ", levelId=" + levelId + ", onlyByColleges=" + onlyByColleges
				+ ", discipline=" + discipline + ", broadDisciplineGroup=" + broadDisciplineGroup + ", intake=" + intake
				+ ", applicants=" + applicants + ", durationYear=" + durationYear + ", durationMonth=" + durationMonth
				+ ", typeId=" + typeId + ", examSystemId=" + examSystemId + ", statutoryBodyId=" + statutoryBodyId
				+ ", approvingUniversityId=" + approvingUniversityId + ", broadCategory=" + broadCategory
				+ ", admissionCriterionId=" + admissionCriterionId + ", department=" + department + ", faculty="
				+ faculty + ", collegeInfo=" + collegeInfo + "]";
	}
    

    
    
}