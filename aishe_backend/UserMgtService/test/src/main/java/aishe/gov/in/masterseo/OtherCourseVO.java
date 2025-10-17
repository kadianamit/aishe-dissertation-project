package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name = "course")
public class OtherCourseVO {	
	
	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "discipline")
	private String discipline;
	@Column(name = "intake")
	private Integer intake;
	@Column(name = "duration_year")
	private Integer durationYear;
	@Column(name = "duration_month")
	private Integer durationMonth;
	@Transient
	private String aisheCode;
	@Transient
	private Integer surveyYear;
	@ManyToOne
	@JoinColumn(name = "mode_id", insertable = false, updatable = false)
	private RefCourseMode modeId;	
	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private RefCourseLevel levelId;
	@ManyToOne
	@JoinColumn(name = "programme_id", insertable = false, updatable = false)
	private RefProgramme programmeId;	
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_category_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroupCategory broadCategory;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group", insertable = false, updatable = false)
	private RefBroadDisciplineGroup broadDisciplineGroup;	
	@ManyToOne
	@JoinColumn(name = "admission_criterion_id", insertable = false, updatable = false)
	private RefAdmissionCriteria admissionCriterionId;	
	@ManyToOne
	@JoinColumn(name = "type_id", insertable = false, updatable = false)
	private RefCourseType typeId;
	@ManyToOne
	@JoinColumn(name = "examination_system_id", insertable = false, updatable = false)
	private RefExaminationSystem examSystemId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDiscipline() {
		return discipline;
	}
	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}
	public Integer getIntake() {
		return intake;
	}
	public void setIntake(Integer intake) {
		this.intake = intake;
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
	public RefCourseMode getModeId() {
		return modeId;
	}
	public void setModeId(RefCourseMode modeId) {
		this.modeId = modeId;
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
	public RefBroadDisciplineGroupCategory getBroadCategory() {
		return broadCategory;
	}
	public void setBroadCategory(RefBroadDisciplineGroupCategory broadCategory) {
		this.broadCategory = broadCategory;
	}
	public RefBroadDisciplineGroup getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(RefBroadDisciplineGroup broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	public RefAdmissionCriteria getAdmissionCriterionId() {
		return admissionCriterionId;
	}
	public void setAdmissionCriterionId(RefAdmissionCriteria admissionCriterionId) {
		this.admissionCriterionId = admissionCriterionId;
	}
	public RefCourseType getTypeId() {
		return typeId;
	}
	public void setTypeId(RefCourseType typeId) {
		this.typeId = typeId;
	}
	public RefExaminationSystem getExamSystemId() {
		return examSystemId;
	}
	public void setExamSystemId(RefExaminationSystem examSystemId) {
		this.examSystemId = examSystemId;
	}
	public String getAisheCode() {
		return aisheCode;
	}
	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}
	public Integer getSurveyYear() {
		return surveyYear;
	}
	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}
		
}
