package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class EbaVernacularAllVO implements Serializable {

	private static final long serialVersionUID = -3164582375093439027L;

	private Integer recordId;
	
	private String languageId;
	
	private Integer type;

    private String vernacularName;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVernacularName() {
		return vernacularName;
	}

	public void setVernacularName(String vernacularName) {
		this.vernacularName = vernacularName;
	}
}