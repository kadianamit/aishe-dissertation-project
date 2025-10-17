package com.nic.aishe.master.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "non_teaching_staff_position", schema = "institutionprofile")
public class NonTeachingPosition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "non_teaching_staff_position_id")
	private int id;
	@Column(name = "aishe_code")
	private String aisheCode;
	
	
	@OneToOne
	@JoinColumn(name="staff_type_id",referencedColumnName = "id")
	private StaffTypes staffTypeId;
	
	@OneToOne
	@JoinColumn(name="group_id",referencedColumnName = "id")
	private StaffGroups groupdId;
	
	@Column(name = "sanctioned_strength")
	private int sanctionedStrength;
	@Column(name = "total")
	private int total;
	@Column(name = "total_female")
	private int totalFemale;
		
	@OneToOne
	@JoinColumn(name="remarks_id",referencedColumnName = "id")
	private Remarks remarksId;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "STAFF_POSITION_SOCIAL_CATEGORY_INFO", schema = "institutionprofile", joinColumns = @JoinColumn(name = "POSITION_ID"), inverseJoinColumns = @JoinColumn(name = "SOCIAL_CATEGORY_INFO_DETAILS_ID"))
	private Set<SocialCategoryInfo> socialCategoryInfo = new HashSet<SocialCategoryInfo>(0);

	@OneToMany(cascade = CascadeType.ALL)

	@JoinTable(name = "STAFF_POSITION_SPECIAL_CATEGORY_INFO", schema = "institutionprofile", joinColumns = @JoinColumn(name = "POSITION_ID"), inverseJoinColumns = @JoinColumn(name = "SPECIAL_CATEGORY_INFO_DETAILS_ID"))
	private Set<SpecialCategoryInfo> specialCategoryInfo = new HashSet<SpecialCategoryInfo>(0);

	public String getAisheCode() {
		return aisheCode;
	}

	public void setAisheCode(String aisheCode) {
		this.aisheCode = aisheCode;
	}

	public StaffTypes getStaffTypeId() {
		return staffTypeId;
	}

	public void setStaffTypeId(StaffTypes staffTypeId) {
		this.staffTypeId = staffTypeId;
	}

	public StaffGroups getGroupdId() {
		return groupdId;
	}

	public void setGroupdId(StaffGroups groupdId) {
		this.groupdId = groupdId;
	}

	public int getSanctionedStrength() {
		return sanctionedStrength;
	}

	public void setSanctionedStrength(int sanctionedStrength) {
		this.sanctionedStrength = sanctionedStrength;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(int totalFemale) {
		this.totalFemale = totalFemale;
	}

	public Remarks getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(Remarks remarksId) {
		this.remarksId = remarksId;
	}

	public Set<SocialCategoryInfo> getSocialCategoryInfo() {
		return socialCategoryInfo;
	}

	public void setSocialCategoryInfo(Set<SocialCategoryInfo> socialCategoryInfo) {
		this.socialCategoryInfo = socialCategoryInfo;
	}

	public Set<SpecialCategoryInfo> getSpecialCategoryInfo() {
		return specialCategoryInfo;
	}

	public void setSpecialCategoryInfo(Set<SpecialCategoryInfo> specialCategoryInfo) {
		this.specialCategoryInfo = specialCategoryInfo;
	}

	@Override
	public String toString() {
		return "NonTeachingPosition [id=" + id + ", aisheCode=" + aisheCode + ", staffTypeId=" + staffTypeId
				+ ", groupdId=" + groupdId + ", sanctionedStrength=" + sanctionedStrength + ", total=" + total
				+ ", totalFemale=" + totalFemale + ", remarksId=" + remarksId + ", socialCategoryInfo="
				+ socialCategoryInfo + ", specialCategoryInfo=" + specialCategoryInfo + "]";
	}

}
