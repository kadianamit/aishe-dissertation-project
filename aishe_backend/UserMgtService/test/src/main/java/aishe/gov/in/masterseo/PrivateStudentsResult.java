package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "private_students_result")
public class PrivateStudentsResult {

	@Id
	@Column(name = "id")
	private Integer id;
	@Column(name = "discipline")
	private String discipline;	
	@Column(name = "appeared_total")
	private Integer	appearedTotal;
	@Column(name = "appeared_female")
	private Integer appearedFemale;
	@Column(name = "passed_total")
	private Integer passedTotal;
	@Column(name = "passed_female")
	private Integer passedFemale;
	@Column(name = "first_class_passed_total")
	private Integer firstClassPassedTotal;
	@Column(name = "first_class_passed_female")
	private Integer firstClassPassedFemale;	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "count_by_category_id", insertable = false, updatable =
	 * false) private RefCourseMode modeId;
	 */
	@ManyToOne
	@JoinColumn(name = "level_id", insertable = false, updatable = false)
	private RefCourseLevel levelId;
	@ManyToOne
	@JoinColumn(name = "programme_id", insertable = false, updatable = false)
	private RefProgramme programmeId;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroup broadDisciplineGroup;
	@ManyToOne
	@JoinColumn(name = "broad_discipline_group_category_id", insertable = false, updatable = false)
	private RefBroadDisciplineGroupCategory refBroadDisciplineGroupCategory;
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
	public Integer getPassedTotal() {
		return passedTotal;
	}
	public void setPassedTotal(Integer passedTotal) {
		this.passedTotal = passedTotal;
	}
	public Integer getPassedFemale() {
		return passedFemale;
	}
	public void setPassedFemale(Integer passedFemale) {
		this.passedFemale = passedFemale;
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
	public RefBroadDisciplineGroup getBroadDisciplineGroup() {
		return broadDisciplineGroup;
	}
	public void setBroadDisciplineGroup(RefBroadDisciplineGroup broadDisciplineGroup) {
		this.broadDisciplineGroup = broadDisciplineGroup;
	}
	public RefBroadDisciplineGroupCategory getRefBroadDisciplineGroupCategory() {
		return refBroadDisciplineGroupCategory;
	}
	public void setRefBroadDisciplineGroupCategory(RefBroadDisciplineGroupCategory refBroadDisciplineGroupCategory) {
		this.refBroadDisciplineGroupCategory = refBroadDisciplineGroupCategory;
	}
	
	
	
}
