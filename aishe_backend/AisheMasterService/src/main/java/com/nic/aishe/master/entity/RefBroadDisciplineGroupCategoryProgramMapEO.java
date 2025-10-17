package com.nic.aishe.master.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nic.aishe.master.util.CollegeId;
import com.nic.aishe.master.util.ProgrammeGroupCategoryId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ref_programme_broad_discipline_group_and_category")
@IdClass(ProgrammeGroupCategoryId.class)
public class RefBroadDisciplineGroupCategoryProgramMapEO {

	@Id
	@OneToOne
	@JoinColumn(name="programme_id",referencedColumnName = "id")
	//@Column(name = "programme_id")
	private ProgramName programmeId;
	@OneToOne
	@Id
	@JoinColumn(name="broad_discipline_group_category_id",referencedColumnName = "id")
	//@Column(name = "broad_discipline_group_category_id")
	private BroadCategory broadDisciplineGroupCategoryId;
	@Id
	@OneToOne
	@JoinColumn(name="broad_discipline_group_id",referencedColumnName = "id")
	//@Column(name = "broad_discipline_group_id")
	private BroadName broadDisciplineGroupId;
	@Column(name = "is_applicable")
	@JsonIgnore
	private Boolean isApplicable;
	
	@OneToOne
	@JoinColumn(name="nirf_discipline_category_id",referencedColumnName = "id")
	private RefNirfDisciplineCategoryEO refNirfDisciplineCategory;


	public ProgramName getProgrammeId() {
		return programmeId;
	}

	public void setProgrammeId(ProgramName programmeId) {
		this.programmeId = programmeId;
	}

	public BroadCategory getBroadDisciplineGroupCategoryId() {
		return broadDisciplineGroupCategoryId;
	}

	public void setBroadDisciplineGroupCategoryId(BroadCategory broadDisciplineGroupCategoryId) {
		this.broadDisciplineGroupCategoryId = broadDisciplineGroupCategoryId;
	}

	public BroadName getBroadDisciplineGroupId() {
		return broadDisciplineGroupId;
	}

	public void setBroadDisciplineGroupId(BroadName broadDisciplineGroupId) {
		this.broadDisciplineGroupId = broadDisciplineGroupId;
	}

	public Boolean getApplicable() {
		return isApplicable;
	}

	public void setApplicable(Boolean applicable) {
		isApplicable = applicable;
	}

	public Boolean getIsApplicable() {
		return isApplicable;
	}

	public void setIsApplicable(Boolean isApplicable) {
		this.isApplicable = isApplicable;
	}

	public RefNirfDisciplineCategoryEO getRefNirfDisciplineCategory() {
		return refNirfDisciplineCategory;
	}

	public void setRefNirfDisciplineCategory(RefNirfDisciplineCategoryEO refNirfDisciplineCategory) {
		this.refNirfDisciplineCategory = refNirfDisciplineCategory;
	}
	
}
