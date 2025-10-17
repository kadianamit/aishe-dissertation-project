package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.nicexam.authorizationservice.jsonbutility.MenuPrivilegesVOType;
import org.nicexam.authorizationservice.uservo.MenuPrivilegesVO;
@Entity
@Table(name = "user_roles_menu_privileges_mapping")
@TypeDef(name = "MenuPrivilegesVOType", typeClass = MenuPrivilegesVOType.class) 
public class UserRoleMenuPrivilegesEO implements Serializable {


	private static final long serialVersionUID = 3682460944686411067L;

	@Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordId;
	
	@Column(name = "fk_role_id")
	private Integer roleId;
	
	@Column(name = "fk_menu_id")
	private Integer menuId;
	
	@Column(name = "fk_submenu_id")
	private Integer subMenuId;
	
	@Column(name = "display_sequence")
	private Integer displaySequence;
	
	@Column(name = "data_json")
	@Type(type = "MenuPrivilegesVOType")
	private MenuPrivilegesVO menuprivilegesVo;

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_on")
	private LocalDateTime createdOn;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "updated_on")
	private LocalDateTime updatedOn;
	
	@Column(name = "status")
	private Integer status;

	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getSubMenuId() {
		return subMenuId;
	}

	public void setSubMenuId(Integer subMenuId) {
		this.subMenuId = subMenuId;
	}
	
	public MenuPrivilegesVO getMenuprivilegesVo() {
		return menuprivilegesVo;
	}

	public void setMenuprivilegesVo(MenuPrivilegesVO menuprivilegesVo) {
		this.menuprivilegesVo = menuprivilegesVo;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Integer getDisplaySequence() {
		return displaySequence;
	}

	public void setDisplaySequence(Integer displaySequence) {
		this.displaySequence = displaySequence;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
