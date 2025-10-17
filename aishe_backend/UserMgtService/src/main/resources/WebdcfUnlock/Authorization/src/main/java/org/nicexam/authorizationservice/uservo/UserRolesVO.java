package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
public class UserRolesVO implements Serializable {

	private static final long serialVersionUID = 7410551910588605891L;

	private Integer recordId;

	private String roleName;

	private String roleDescription;
	
	private Integer userOfficeId;
	
	private String userOfficeValue;

	private Integer status;
	
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public Integer getUserOfficeId() {
		return userOfficeId;
	}

	public void setUserOfficeId(Integer userOfficeId) {
		this.userOfficeId = userOfficeId;
	}

	public String getUserOfficeValue() {
		return userOfficeValue;
	}

	public void setUserOfficeValue(String userOfficeValue) {
		this.userOfficeValue = userOfficeValue;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	}