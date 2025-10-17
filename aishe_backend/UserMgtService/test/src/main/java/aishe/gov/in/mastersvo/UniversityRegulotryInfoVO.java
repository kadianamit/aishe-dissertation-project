package aishe.gov.in.mastersvo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "")
public class UniversityRegulotryInfoVO {
	
	private Boolean isUniversityUploadedActStatues;
	private Boolean isUniversityComplyingRules;
	private Boolean isUniversity180ActualTeachingDays;
	public Boolean getIsUniversityUploadedActStatues() {
		return isUniversityUploadedActStatues;
	}
	public void setIsUniversityUploadedActStatues(Boolean isUniversityUploadedActStatues) {
		this.isUniversityUploadedActStatues = isUniversityUploadedActStatues;
	}
	public Boolean getIsUniversityComplyingRules() {
		return isUniversityComplyingRules;
	}
	public void setIsUniversityComplyingRules(Boolean isUniversityComplyingRules) {
		this.isUniversityComplyingRules = isUniversityComplyingRules;
	}
	public Boolean getIsUniversity180ActualTeachingDays() {
		return isUniversity180ActualTeachingDays;
	}
	public void setIsUniversity180ActualTeachingDays(Boolean isUniversity180ActualTeachingDays) {
		this.isUniversity180ActualTeachingDays = isUniversity180ActualTeachingDays;
	}
	
	

}
