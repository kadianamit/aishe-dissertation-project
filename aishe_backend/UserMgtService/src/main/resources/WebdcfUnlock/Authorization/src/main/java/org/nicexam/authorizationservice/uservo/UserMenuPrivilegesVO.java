package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class UserMenuPrivilegesVO implements Serializable {

	private static final long serialVersionUID = -8982952068263980823L;

	private Integer recordId;
	
	private String actionName;
	
	private String idValue;
	
	private String actionClass;
	
	private String actionUrl;


	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}
	
	public String getActionClass() {
		return actionClass;
	}

	public void setActionClass(String actionClass) {
		this.actionClass = actionClass;
	}

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}
}
