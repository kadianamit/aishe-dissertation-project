package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class UserMenuOtherLangVO implements Serializable{

	private Integer recordId;

	private Integer userMenuRecordId;
	
	private String langCode;
	
	private String menuName;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getUserMenuRecordId() {
		return userMenuRecordId;
	}

	public void setUserMenuRecordId(Integer userMenuRecordId) {
		this.userMenuRecordId = userMenuRecordId;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
}
