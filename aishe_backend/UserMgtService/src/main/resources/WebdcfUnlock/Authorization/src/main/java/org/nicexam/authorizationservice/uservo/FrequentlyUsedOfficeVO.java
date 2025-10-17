package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class FrequentlyUsedOfficeVO implements Serializable {

	private static final long serialVersionUID = 4196224062019807234L;

	private Integer recordId;
	
	private String name;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}