package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "ref_scholarship_scheme")
public class RefScholarshipSchemeEO {

	@Id
	@Column(name = "id")
	private Integer id;
	@OneToOne
	@NotFound(action = NotFoundAction.IGNORE)
	@JoinColumn(name="state_id",referencedColumnName = "st_code")
	private State state;
	//@Column(name = "state_id")
	//private String stateId;
	@Column(name = "name")
	private String schemeName;
	@Column(name = "survey_year")
	private Integer surveyYear;
	@Column(name = "status")
	private boolean status;
	}
