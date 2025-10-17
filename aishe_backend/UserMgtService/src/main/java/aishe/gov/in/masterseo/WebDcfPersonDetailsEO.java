package aishe.gov.in.masterseo;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import aishe.gov.in.mastersvo.PersonDetailsEmadedPK;

@Entity
@Table(name = "webdcf.person_details_survey")
public class WebDcfPersonDetailsEO {
	@EmbeddedId
	private PersonDetailsEmadedPK universityPk;
	@Column(name = "officer_name")
	private String officerName;
	@Column(name = "officer_designation")
	private String officerDesignation;
	@Column(name = "officer_email")
	private String officerEmail;
	@Column(name = "officer_mobile")
	private String officerMobile;
	@Column(name = "officer_telephone")
	private String officerTelephone;
	public PersonDetailsEmadedPK getUniversityPk() {
		return universityPk;
	}
	public void setUniversityPk(PersonDetailsEmadedPK universityPk) {
		this.universityPk = universityPk;
	}
	public String getOfficerName() {
		return officerName;
	}
	public void setOfficerName(String officerName) {
		this.officerName = officerName;
	}
	public String getOfficerDesignation() {
		return officerDesignation;
	}
	public void setOfficerDesignation(String officerDesignation) {
		this.officerDesignation = officerDesignation;
	}
	public String getOfficerEmail() {
		return officerEmail;
	}
	public void setOfficerEmail(String officerEmail) {
		this.officerEmail = officerEmail;
	}
	public String getOfficerMobile() {
		return officerMobile;
	}
	public void setOfficerMobile(String officerMobile) {
		this.officerMobile = officerMobile;
	}
	public String getOfficerTelephone() {
		return officerTelephone;
	}
	public void setOfficerTelephone(String officerTelephone) {
		this.officerTelephone = officerTelephone;
	}
}