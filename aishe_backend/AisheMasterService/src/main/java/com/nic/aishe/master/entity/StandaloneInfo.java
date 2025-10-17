package com.nic.aishe.master.entity;



import com.nic.aishe.master.util.StandAloneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "standalone_institution")
@IdClass(StandAloneId.class)
public class StandaloneInfo implements Serializable {

	private static final long serialVersionUID = 8690843058969832724L;

	@Id
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	

	//private Integer aisheCode;

	@Column(name = "address_line1")
	private String addressline1;
	
	@Column(name = "offers_distance_programme")
	private Boolean offerDistanceProgramMode;	

	@Column(name = "address_line2")
	private String addressline2;

	@Column(name = "area")
	private Double area;

	@Column(name = "city")
	private String city;

	@Column(name = "website")
	private String website;

	@Column(name = "name")
	private String name;

	@Column(name = "year_of_establishment")
	private Integer establishmentYear;

	@Column(name = "constructed_area")
	private Double constructedArea;


	@Column(name = "location")
	private String location;
	
	@Column(name = "awards_degree_through_university")
	private Boolean awardsDegreeThroughUniversity;

	@Column(name = "girl_exclusive")
	private Boolean girl_exclusive;

	@Id
	@Column(name = "survey_year")
	private Integer surveyYear;

	@Column(name = "university_id")
	private String universityId;

	@Column(name = "year_of_recognition")
	private Integer recognitionYear;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAddressline1() {
		return addressline1;
	}

	public void setAddressline1(String addressline1) {
		this.addressline1 = addressline1;
	}

	public Boolean getOfferDistanceProgramMode() {
		return offerDistanceProgramMode;
	}

	public void setOfferDistanceProgramMode(Boolean offerDistanceProgramMode) {
		this.offerDistanceProgramMode = offerDistanceProgramMode;
	}

	public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(Integer establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public Double getConstructedArea() {
		return constructedArea;
	}

	public void setConstructedArea(Double constructedArea) {
		this.constructedArea = constructedArea;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getAwardsDegreeThroughUniversity() {
		return awardsDegreeThroughUniversity;
	}

	public void setAwardsDegreeThroughUniversity(Boolean awardsDegreeThroughUniversity) {
		this.awardsDegreeThroughUniversity = awardsDegreeThroughUniversity;
	}

	public Boolean getGirl_exclusive() {
		return girl_exclusive;
	}

	public void setGirl_exclusive(Boolean girl_exclusive) {
		this.girl_exclusive = girl_exclusive;
	}

	public Integer getSurveyYear() {
		return surveyYear;
	}

	public void setSurveyYear(Integer surveyYear) {
		this.surveyYear = surveyYear;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}

	public Integer getRecognitionYear() {
		return recognitionYear;
	}

	public void setRecognitionYear(Integer recognitionYear) {
		this.recognitionYear = recognitionYear;
	}

	@Override
	public String toString() {
		return "StandaloneInfo{" +
				", addressline1='" + addressline1 + '\'' +
				", offerDistanceProgramMode=" + offerDistanceProgramMode +
				", addressline2='" + addressline2 + '\'' +
				", area=" + area +
				", city='" + city + '\'' +
				", website='" + website + '\'' +
				", name='" + name + '\'' +
				", establishmentYear=" + establishmentYear +
				", constructedArea=" + constructedArea +
				", location='" + location + '\'' +
				", awardsDegreeThroughUniversity=" + awardsDegreeThroughUniversity +
				", girl_exclusive=" + girl_exclusive +
				", surveyYear=" + surveyYear +
				", universityId='" + universityId + '\'' +
				", recognitionYear=" + recognitionYear +
				'}';
	}
}