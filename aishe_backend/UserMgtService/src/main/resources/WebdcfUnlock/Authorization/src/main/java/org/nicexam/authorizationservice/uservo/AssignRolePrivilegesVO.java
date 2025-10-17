package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class AssignRolePrivilegesVO implements Serializable
{
	private static final long serialVersionUID = -3202478408391862L;

	private Integer displaySequence;
	
	private Integer roleId;

	private List<MenuSubMenuVO> menuDetails ;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public List<MenuSubMenuVO> getMenuDetails() {
		return menuDetails;
	}

	public void setMenuDetails(List<MenuSubMenuVO> menuDetails) {
		this.menuDetails = menuDetails;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}		
}
