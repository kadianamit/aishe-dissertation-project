package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class MenuSubMenuVO implements Serializable {

	private static final long serialVersionUID = -6626189581596580876L;
	
	private Integer menuRecordId;
	
	private Integer submenuRecordId;
	
	private String menu;
	
	private String submenu;
	
	private Integer menudisplaySequence;
	
	private Integer submenudisplaySequence;
	
	private String submenuUrl;
	
	private List<MenuPrivilegesListVO> menuprivileges ;

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getSubmenu() {
		return submenu;
	}

	public void setSubmenu(String submenu) {
		this.submenu = submenu;
	}

	public Integer getMenudisplaySequence() {
		return menudisplaySequence;
	}

	public void setMenudisplaySequence(Integer menudisplaySequence) {
		this.menudisplaySequence = menudisplaySequence;
	}

	public Integer getSubmenudisplaySequence() {
		return submenudisplaySequence;
	}

	public void setSubmenudisplaySequence(Integer submenudisplaySequence) {
		this.submenudisplaySequence = submenudisplaySequence;
	}

	public String getSubmenuUrl() {
		return submenuUrl;
	}

	public void setSubmenuUrl(String submenuUrl) {
		this.submenuUrl = submenuUrl;
	}

	public Integer getMenuRecordId() {
		return menuRecordId;
	}

	public void setMenuRecordId(Integer menuRecordId) {
		this.menuRecordId = menuRecordId;
	}

	public Integer getSubmenuRecordId() {
		return submenuRecordId;
	}

	public void setSubmenuRecordId(Integer submenuRecordId) {
		this.submenuRecordId = submenuRecordId;
	}

	public List<MenuPrivilegesListVO> getMenuprivileges() {
		return menuprivileges;
	}

	public void setMenuprivileges(List<MenuPrivilegesListVO> menuprivileges) {
		this.menuprivileges = menuprivileges;
	}	
}
