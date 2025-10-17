package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name="ref_university")
public class UniversityApplicableDCF {
    @Id
    private String id;
    private String name;

    @Column(name = "survey_year")
    private Integer surveyYear;

    @Column(name = "type_id")
    private String typeId;

    @ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "statecode")
	private State stateCode;

  //  @Column(name = "district_code")
  //  private String districtCode;
    
    @ManyToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name = "district_code")
	private District district;
    
    @Transient
    private String universityNameState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSurveyYear() {
        return surveyYear;
    }

    public void setSurveyYear(Integer surveyYear) {
        this.surveyYear = surveyYear;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

	public State getStateCode() {
		return stateCode;
	}

	public void setStateCode(State stateCode) {
		this.stateCode = stateCode;
	}

	public String getUniversityNameState() {
		return universityNameState;
	}

	public void setUniversityNameState(String universityNameState) {
		this.universityNameState = universityNameState;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}
	
	
}
