package aishe.gov.in.masterseo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "public.activity_master")
public class ActiveInstituteInactiveUser {
    @Column(name = "institution_type")
    private String institutionType;
    @Id
    @Column(name = "aishe_code")
    private String aisheCode;
    @Column(name = "name")
    private String name;
    @Column(name = "state_code")
    private String stateCode;
    @Column(name = "state_name")
    private String stateName;
    @Column(name = "district_code")
    private String districtCode;
    @Column(name = "district_name")
    private String districtName;
    @Column(name = "nodal_officer_name")
    private String nodalOfficerName;
    @Column(name = "nodal_officer_gender")
    private String nodalOfficerGender;
    @Column(name = "nodal_officer_mobile")
    private String nodalOfficerMobile;
    @Column(name = "nodal_officer_email")
    private String nodalOfficerEmail;
    @Column(name = "nodal_officer_telephone")
    private String nodalOfficerTelephone;
    @Column(name = "nodal_officer_gender_name")
    private String nodalOfficerGenderName;
    @Column(name = "nodal_officer_status")
    private String nodalOfficerStatus;
    @Column(name = "type_id")
    private String typeId;
    @Column(name = "type")
    private String type;
    @Column(name = "university_aishe_code")
    private String affiliatingUniversityAisheCode;
    @Column(name = "university_name")
    private String universityName;

}
