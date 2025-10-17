package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class MenuSubmenuMappingVO implements Serializable {

	private static final long serialVersionUID = -1192662271949786610L;

	private Integer menuId;
	
	private Integer submenuId;
	
	private Integer displaySequence;
	
	private String officeLevel;
		
	private String ebaName;
	
    private String menuName;
	
	private String submenuName;
	
	private boolean active;
	
	private Integer status;

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getSubmenuId() {
		return submenuId;
	}

	public void setSubmenuId(Integer submenuId) {
		this.submenuId = submenuId;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

	
	public String getOfficeLevel() {
		return officeLevel;
	}

	public void setOfficeLevel(String officeLevel) {
		this.officeLevel = officeLevel;
	}

	public String getEbaName() {
		return ebaName;
	}

	public void setEbaName(String ebaName) {
		this.ebaName = ebaName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getSubmenuName() {
		return submenuName;
	}

	public void setSubmenuName(String submenuName) {
		this.submenuName = submenuName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}