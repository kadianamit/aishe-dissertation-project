package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class LoginSliderVO implements Serializable {

	private static final long serialVersionUID = -2030341077752088337L;

	private int recordId;

	private Integer Sequence;
	
	private String bannerDescription;


	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public Integer getSequence() {
		return Sequence;
	}

	public void setSequence(Integer sequence) {
		Sequence = sequence;
	}

	public String getBannerDescription() {
		return bannerDescription;
	}

	public void setBannerDescription(String bannerDescription) {
		this.bannerDescription = bannerDescription;
	}
	
}