package aishe.gov.in.mastersvo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NonTeachingStaffDTO {
	
	@JsonProperty
	private String staffType;
	@JsonProperty
	private String group;
	@JsonProperty
	private String sanctionStrength;
	@JsonProperty
	private List<TypeDTO> type;
	
	public String getStaffType() {
		return staffType;
	}
	public void setStaffType(String staffType) {
		this.staffType = staffType;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getSanctionStrength() {
		return sanctionStrength;
	}
	public void setSanctionStrength(String sanctionStrength) {
		this.sanctionStrength = sanctionStrength;
	}
	public List<TypeDTO> getType() {
		return type;
	}
	public void setType(List<TypeDTO> type) {
		this.type = type;
	}
	
	

}
