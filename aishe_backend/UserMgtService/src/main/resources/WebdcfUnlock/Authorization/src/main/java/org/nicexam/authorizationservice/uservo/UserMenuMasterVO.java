package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;

public class UserMenuMasterVO implements Serializable{

	private static final long serialVersionUID = 2881281559345327471L;

	private int recordId;

	private boolean hasChild;
	
	private String idValue;
	
	private Integer menuBlock;
	
	private String menuClass;
	
	private String menuIcon;
	
	private String menuLocation;
	
	private String langCode;
	
	private String menuName;
	
	private Integer displaySequence;	
	
	private Integer parentId;
	
	private Integer status;
	
	private boolean Active;

    private String officeLevel;
		
	private String ebaName;
	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public boolean isHasChild() {
		return hasChild;
	}

	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}

	public String getIdValue() {
		return idValue;
	}

	public void setIdValue(String idValue) {
		this.idValue = idValue;
	}

	public Integer getMenuBlock() {
		return menuBlock;
	}

	public void setMenuBlock(Integer menuBlock) {
		this.menuBlock = menuBlock;
	}

	public String getMenuClass() {
		return menuClass;
	}

	public void setMenuClass(String menuClass) {
		this.menuClass = menuClass;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuLocation() {
		return menuLocation;
	}

	public void setMenuLocation(String menuLocation) {
		this.menuLocation = menuLocation;
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

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public boolean isActive() {
		return Active;
	}

	public void setActive(boolean active) {
		Active = active;
	}

	
}
