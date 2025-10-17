package org.nicexam.authorizationservice.uservo;

import java.io.Serializable;
import java.util.List;

public class UserRoleMappingVO implements Serializable {

	private static final long serialVersionUID = -8908060648119464799L;

	private Integer recordId;
	//change needed
	private Long userRecordId;

	private List<Integer> rolesRecordId;
	
	private String roleName;
	
	private String loginId;
	
	private Integer userOfficeId;
	
	private String userOfficeValue;
	
	private Integer status;


	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Long getUserRecordId() {
		return userRecordId;
	}

	public void setUserRecordId(Long userRecordId) {
		this.userRecordId = userRecordId;
	}

	public List<Integer> getRolesRecordId() {
		return rolesRecordId;
	}

	public void setRolesRecordId(List<Integer> rolesRecordId) {
		this.rolesRecordId = rolesRecordId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
