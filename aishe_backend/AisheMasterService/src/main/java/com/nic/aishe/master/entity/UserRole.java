package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name="ref_user_management_role")
public class UserRole {
	
	@Id
	@Column(name="role_id")
	private Integer roleId;
	
	@Column(name = "role_name")
	private String roleName;
	
	@Column(name = "level")
	private String level;

	@Column(name = "level_id")
	private Integer levelId;

	@Column(name = "is_deo_applicable")
	private Boolean isDeoApplicable;


	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}



	public Integer getLevelId() {
		return levelId;
	}

	public void setLevelId(Integer levelId) {
		this.levelId = levelId;
	}

	public Boolean getDeoApplicable() {
		return isDeoApplicable;
	}

	public void setDeoApplicable(Boolean deoApplicable) {
		isDeoApplicable = deoApplicable;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
