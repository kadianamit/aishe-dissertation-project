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
import javax.persistence.Table;

@Entity
@Table(schema = "institutionprofile", name = "SOCIAL_CATEGORY_INFO_DETAILS")
public class SocialCategoryInfo implements Serializable {
	private static final long serialVersionUID = -1427851188373779840L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "social_category_id")
	private Integer socialCategoryId;
	@Column(name = "count")
	private Integer count;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "SOCIAL_CATEGORY_GENDER_INFO", schema = "institutionprofile", joinColumns = @JoinColumn(name = "SOCIAL_CATEGORY_INFO_DETAILS_ID"), inverseJoinColumns = @JoinColumn(name = "GENDER_INFO_DETAILS_ID"))
	private Set<GenderInfoDetails> genderInfo = new HashSet<GenderInfoDetails>(0);

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSocialCategoryId() {
		return socialCategoryId;
	}

	public void setSocialCategoryId(Integer socialCategoryId) {
		this.socialCategoryId = socialCategoryId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Set<GenderInfoDetails> getGenderInfo() {
		return genderInfo;
	}

	public void setGenderInfo(Set<GenderInfoDetails> genderInfo) {
		this.genderInfo = genderInfo;
	}

}
