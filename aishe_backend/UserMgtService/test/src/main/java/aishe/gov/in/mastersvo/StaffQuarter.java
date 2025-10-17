package aishe.gov.in.mastersvo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StaffQuarter {

	@JsonProperty
	Integer id;	
	@JsonProperty
	Integer teachingStaff;
	@JsonProperty
	Integer nonTeachingStaff;
	@JsonProperty
	Integer total;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTeachingStaff() {
		return teachingStaff;
	}
	public void setTeachingStaff(Integer teachingStaff) {
		this.teachingStaff = teachingStaff;
	}
	public Integer getNonTeachingStaff() {
		return nonTeachingStaff;
	}
	public void setNonTeachingStaff(Integer nonTeachingStaff) {
		this.nonTeachingStaff = nonTeachingStaff;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
	
	
	
}
