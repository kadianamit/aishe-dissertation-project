package aishe.gov.in.mastersvo;

public class AisheCodeDetailsVo {
	private Integer lsy;
	
	private String elligible;
	
	private Boolean specialPermission=false;
	
	private String stateCode;
	
	private String universityId;

	public Integer getLsy() {
		return lsy;
	}

	public void setLsy(Integer lsy) {
		this.lsy = lsy;
	}

	public String getElligible() {
		return elligible;
	}

	public void setElligible(String elligible) {
		this.elligible = elligible;
	}

	public Boolean getSpecialPermission() {
		return specialPermission;
	}

	public void setSpecialPermission(Boolean specialPermission) {
		this.specialPermission = specialPermission;
	}

	public String getStateCode() {
		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}

	public String getUniversityId() {
		return universityId;
	}

	public void setUniversityId(String universityId) {
		this.universityId = universityId;
	}
}