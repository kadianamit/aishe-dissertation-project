package com.nic.aishe.master.entity;

import java.io.Serializable;
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
@Table(schema = "institutionprofile", name = "SPECIAL_CATEGORY_INFO_DETAILS")
public class SpecialCategoryInfo implements Serializable {
	private static final long serialVersionUID = -7360784861572325021L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "special_category_id")
	private Integer specialCategoryId;
	@Column(name = "total")
	private Integer total;
	@Column(name = "total_female")
	private Integer totalFemale;
	
	@OneToOne
	@JoinColumn(name="remarks_id",referencedColumnName = "id")
	private Remarks remarksId;
	
	@OneToMany(cascade = CascadeType.ALL)

	@JoinTable(name = "SPECIAL_CATEGORY_SOCIAL_CATEGORY_INFO", joinColumns = @JoinColumn(name = "SPECIAL_CATEGORY_INFO_DETAILS_ID"), schema = "institutionprofile", inverseJoinColumns = @JoinColumn(name = "SOCIAL_CATEGORY_INFO_DETAILS_ID"))
	private Set<SocialCategoryInfo> socialCategoryInfo = new HashSet<SocialCategoryInfo>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSpecialCategoryId() {
		return specialCategoryId;
	}

	public void setSpecialCategoryId(Integer specialCategoryId) {
		this.specialCategoryId = specialCategoryId;
	}

	public Integer getTotal() {
		return total;
	}

	public Set<SocialCategoryInfo> getSocialCategoryInfo() {
		return socialCategoryInfo;
	}

	public void setSocialCategoryInfo(Set<SocialCategoryInfo> socialCategoryInfo) {
		this.socialCategoryInfo = socialCategoryInfo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getTotalFemale() {
		return totalFemale;
	}

	public void setTotalFemale(Integer totalFemale) {
		this.totalFemale = totalFemale;
	}

	public Remarks getRemarksId() {
		return remarksId;
	}

	public void setRemarksId(Remarks remarksId) {
		this.remarksId = remarksId;
	}

}
