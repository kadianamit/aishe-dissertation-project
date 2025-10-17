package com.nic.aishe.master.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;



/**
 * The persistent class for the ref_institution_management_role database table.
 * 
 */
@Entity
@Table(name="ref_institution_management_role")
public class RefInstitutionManagementRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;

	@Column(name="c_affiliate")
	private Boolean cAffiliate;

	@Column(name="c_approve_reject")
	private Boolean cApproveReject;

	@Column(name="c_deaffiliate")
	private Boolean cDeaffiliate;

	@Column(name="c_delete")
	private Boolean cDelete;

	@Column(name="c_edit")
	private Boolean cEdit;

	@Column(name="c_merge")
	private Boolean cMerge;

	@Column(name="c_module")
	private Boolean cModule;

	@Column(name="c_to_u")
	private Boolean cToU;

	private Boolean report;

	@Column(name="s_approve_reject")
	private Boolean sApproveReject;

	@Column(name="s_delete")
	private Boolean sDelete;

	@Column(name="s_edit")
	private Boolean sEdit;

	@Column(name="s_inactive")
	private Boolean sInactive;

	@Column(name="s_module")
	private Boolean sModule;

	@Column(name="s_to_c")
	private Boolean sToC;

	@Column(name="s_to_u")
	private Boolean sToU;

	@Column(name="search_request")
	private Boolean searchRequest;

	@Column(name="u_add")
	private Boolean uAdd;

	@Column(name="u_approve_reject")
	private Boolean uApproveReject;

	@Column(name="u_delete")
	private Boolean uDelete;

	@Column(name="u_edit")
	private Boolean uEdit;

	@Column(name="u_inactive")
	private Boolean uInactive;

	@Column(name="u_module")
	private Boolean uModule;

	@Column(name="u_to_c")
	private Boolean uToC;

	@Column(name="shift_university")
	private Boolean shiftUniversity;

	public RefInstitutionManagementRole() {
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Boolean getCAffiliate() {
		return this.cAffiliate;
	}

	public void setCAffiliate(Boolean cAffiliate) {
		this.cAffiliate = cAffiliate;
	}

	public Boolean getCApproveReject() {
		return this.cApproveReject;
	}

	public void setCApproveReject(Boolean cApproveReject) {
		this.cApproveReject = cApproveReject;
	}

	public Boolean getCDeaffiliate() {
		return this.cDeaffiliate;
	}

	public void setCDeaffiliate(Boolean cDeaffiliate) {
		this.cDeaffiliate = cDeaffiliate;
	}

	public Boolean getCDelete() {
		return this.cDelete;
	}

	public void setCDelete(Boolean cDelete) {
		this.cDelete = cDelete;
	}

	public Boolean getCEdit() {
		return this.cEdit;
	}

	public void setCEdit(Boolean cEdit) {
		this.cEdit = cEdit;
	}

	public Boolean getCMerge() {
		return this.cMerge;
	}

	public void setCMerge(Boolean cMerge) {
		this.cMerge = cMerge;
	}

	public Boolean getCModule() {
		return this.cModule;
	}

	public void setCModule(Boolean cModule) {
		this.cModule = cModule;
	}

	public Boolean getCToU() {
		return this.cToU;
	}

	public void setCToU(Boolean cToU) {
		this.cToU = cToU;
	}

	public Boolean getReport() {
		return this.report;
	}

	public void setReport(Boolean report) {
		this.report = report;
	}

	public Boolean getSApproveReject() {
		return this.sApproveReject;
	}

	public void setSApproveReject(Boolean sApproveReject) {
		this.sApproveReject = sApproveReject;
	}

	public Boolean getSDelete() {
		return this.sDelete;
	}

	public void setSDelete(Boolean sDelete) {
		this.sDelete = sDelete;
	}

	public Boolean getSEdit() {
		return this.sEdit;
	}

	public void setSEdit(Boolean sEdit) {
		this.sEdit = sEdit;
	}

	public Boolean getSInactive() {
		return this.sInactive;
	}

	public void setSInactive(Boolean sInactive) {
		this.sInactive = sInactive;
	}

	public Boolean getSModule() {
		return this.sModule;
	}

	public void setSModule(Boolean sModule) {
		this.sModule = sModule;
	}

	public Boolean getSToC() {
		return this.sToC;
	}

	public void setSToC(Boolean sToC) {
		this.sToC = sToC;
	}

	public Boolean getSToU() {
		return this.sToU;
	}

	public void setSToU(Boolean sToU) {
		this.sToU = sToU;
	}

	public Boolean getSearchRequest() {
		return this.searchRequest;
	}

	public void setSearchRequest(Boolean searchRequest) {
		this.searchRequest = searchRequest;
	}

	public Boolean getUAdd() {
		return this.uAdd;
	}

	public void setUAdd(Boolean uAdd) {
		this.uAdd = uAdd;
	}

	public Boolean getUApproveReject() {
		return this.uApproveReject;
	}

	public void setUApproveReject(Boolean uApproveReject) {
		this.uApproveReject = uApproveReject;
	}

	public Boolean getUDelete() {
		return this.uDelete;
	}

	public void setUDelete(Boolean uDelete) {
		this.uDelete = uDelete;
	}

	public Boolean getUEdit() {
		return this.uEdit;
	}

	public void setUEdit(Boolean uEdit) {
		this.uEdit = uEdit;
	}

	public Boolean getUInactive() {
		return this.uInactive;
	}

	public void setUInactive(Boolean uInactive) {
		this.uInactive = uInactive;
	}

	public Boolean getUModule() {
		return this.uModule;
	}

	public void setUModule(Boolean uModule) {
		this.uModule = uModule;
	}

	public Boolean getUToC() {
		return this.uToC;
	}

	public void setUToC(Boolean uToC) {
		this.uToC = uToC;
	}

	public Boolean getShiftUniversity() {
		return shiftUniversity;
	}

	public void setShiftUniversity(Boolean shiftUniversity) {
		this.shiftUniversity = shiftUniversity;
	}
}