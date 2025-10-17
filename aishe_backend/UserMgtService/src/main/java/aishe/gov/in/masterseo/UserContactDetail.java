package aishe.gov.in.masterseo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "public.user_master")
@Setter
@Getter
public class UserContactDetail {
    @Id
    @Column(name = "aishe_code")
    private String aisheCode;
    private String name;
    @Column(name = "nodal_Officer_Name")
    private String nodalOfficerName;
    @Column(name = "nodal_Officer_Mobile")
    private String nodalOfficerMobile;
    @Column(name = "nodal_Officer_Email")
    private String nodalOfficerEmail;
    @Column(name = "institution_Head_Name")
    private String institutionHeadName;
    @Column(name = "institution_Head_Mobile")
    private String institutionHeadMobile;
    @Column(name = "institution_Head_Email")
    private String institutionHeadEmail;
}
