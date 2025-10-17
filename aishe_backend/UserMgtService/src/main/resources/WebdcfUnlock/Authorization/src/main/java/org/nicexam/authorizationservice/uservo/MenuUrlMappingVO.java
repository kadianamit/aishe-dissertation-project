package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class MenuUrlMappingVO implements Serializable {

	private static final long serialVersionUID = -2985428250609561507L;

	private int recordId;

	private String menuName;

	private String menuUrl;
	
	private Integer status=1;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
