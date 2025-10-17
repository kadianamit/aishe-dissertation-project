package com.nic.aishe.master.entity;

import com.nic.aishe.master.util.CollegeId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "college_institution")
@IdClass(CollegeId.class)
public class CollegeInstitution {
    private static final long serialVersionUID = 8690843058969832724L;

    @Id
    private Integer id;

    @Transient
    private Integer aisheCode;

    @Column(name = "address_line1")
    private String addressline1;

    @Column(name = "address_line2")
    private String addressline2;

    @Column(name = "area")
    private Double area;

    @Column(name = "city")
    private String city;

    @Column(name = "website")
    private String website;

    @OneToOne
    @JoinColumn(name = "state_code")
    private State stateCode;

    @OneToOne
    @JoinColumn(name = "district_code")
    private District districtCode;

    @Column(name = "name")
    private String name;

    @Column(name = "year_of_establishment")
    private Integer establishmentYear;

    @Column(name = "constructed_area")
    private Double constructedArea;

    @OneToOne

    @JoinColumn(name = "college_type_id", referencedColumnName = "id")
    private RefUniversityCollegeType instituteType;

    @Column(name = "location")
    private String location;

    @Id
    @Column(name = "survey_year")
    private Integer surveyYear;

    @Column(name = "university_id")
    private String universityId;


    @Column(name = "other_statutory_body")
    private String otherStatutoryBody;

    @Column(name = "year_of_affiliation")
    private Integer affiliationYear;

//	@Column(name = "management_id")
//	private String management;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "management_id", referencedColumnName = "id")
    private InstituteManagement instituteManagement;
}
