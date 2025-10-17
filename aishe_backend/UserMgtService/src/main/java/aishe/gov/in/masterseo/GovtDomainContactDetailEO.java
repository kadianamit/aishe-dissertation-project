package aishe.gov.in.masterseo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "ciso.govt_domain_contact_details")
public class GovtDomainContactDetailEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "owner_name")
    private String ownerName;
    @Column(name = "domain_name")
    private String domainName;
    @Column(name = "organisational_name")
    private String organisationalName;
    @Column(name = "organisational_designation")
    private String organisationalDesignation;
    @Column(name = "organisational_email_id")
    private String organisationalEmailId;
    @Column(name = "organisational_mobile")
    private String organisationalMobile;
    @Column(name = "administrative_name")
    private String administrativeName;
    @Column(name = "administrative_designation")
    private String administrativeDesignation;
    @Column(name = "administrative_email_id")
    private String administrativeEmailId;
    @Column(name = "administrative_mobile")
    private String administrativeMobile;
    @Column(name = "technical_name")
    private String technicalName;
    @Column(name = "technical_designation")
    private String technicalDesignation;
    @Column(name = "technical_email_id")
    private String technicalEmailId;
    @Column(name = "technical_mobile")
    private String technicalMobile;
    @Column(name = "login_name")
    private String loginName;
    @Column(name = "login_designation")
    private String loginDesignation;
    @Column(name = "login_email_id")
    private String loginEmailId;
    @Column(name = "login_mobile")
    private String loginMobile;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "department_name")
    private String departmentName;

}
