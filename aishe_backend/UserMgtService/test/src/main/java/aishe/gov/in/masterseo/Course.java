package aishe.gov.in.masterseo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course implements Serializable {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "only_through_colleges")
	private Boolean onlyThroughColleges;	
	@Column(name = "discipline")
	private String discipline;
	@Column(name = "intake")
	private Integer intake;
	@Column(name = "no_of_applicants")
	private Integer noOfApplicants;
	@Column(name = "duration_year")
	private Integer durationYear;
	@Column(name = "duration_month")
	private Integer durationMonth;
	@ManyToOne
	@JoinColumn(name = "mode_id")
	private RefCourseMode modeId;	
	@ManyToOne
	@JoinColumn(name = "level_id")
	private RefCourseLevel levelId;
	@ManyToOne
	@JoinColumn(name = "programme_id")
	private RefProgramme programmeId;	
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_category_id")
	private RefBroadDisciplineGroupCategory broadDisciplineGroupCategoryId;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group")
	private RefBroadDisciplineGroup broadDisciplineGroup;	
	@ManyToOne
	@JoinColumn(name = "admission_criterion_id")
	private RefAdmissionCriteria admissionCriterionId;	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private RefCourseType type;
	@ManyToOne
	@JoinColumn(name = "examination_system_id")
	private RefExaminationSystem examinationSystem;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public RefCourseMode getModeId() {
		return modeId;
	}
	public void setModeId(RefCourseMode modeId) {
		this.modeId = modeId;
	}
	public Boolean getOnlyThroughColleges() {
		return onlyThroughColleges;
	}
	public void setOnlyThroughColleges(Boolean onlyThroughColleges) {
		this.onlyThroughColleges = onlyThroughColleges;
	}

	public RefCourseLevel getLevelId() {
		return levelId;
	}
	public void setLevelId(RefCourseLevel levelId) {
		this.levelId = levelId;
	}
	public RefProgramme getProgrammeId() {
		return programmeId;
	}
	public void setProgrammeId(RefProgramme programmeId) {
		this.programmeId = programmeId;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public RefBroadDisciplineGroupCategory getBroadDisciplineGroupCategoryId() {
		return broadDisciplineGroupCategoryId;
	}
	public void setBroadDisciplineGroupCategoryId(RefBroadDisciplineGroupCategory broadDisciplineGroupCategoryId) {
		this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
	}
	public RefBroadDisciplineGroup getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(RefBroadDisciplineGroup broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	public Integer getIntake() {
		return intake;
	}
	public void setIntake(Integer intake) {
		this.intake = intake;
	}
	public Integer getNoOfApplicants() {
		return noOfApplicants;
	}
	public void setNoOfApplicants(Integer noOfApplicants) {
		this.noOfApplicants = noOfApplicants;
	}
	public RefAdmissionCriteria getAdmissionCriterionId() {
		return admissionCriterionId;
	}
	public void setAdmissionCriterionId(RefAdmissionCriteria admissionCriterionId) {
		this.admissionCriterionId = admissionCriterionId;
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
	public RefCourseType getType() {
		return type;
	}
	public void setType(RefCourseType type) {
		this.type = type;
	}
	public RefExaminationSystem getExaminationSystem() {
		return examinationSystem;
	}
	public void setExaminationSystem(RefExaminationSystem examinationSystem) {
		this.examinationSystem = examinationSystem;
	}
	
	



}
