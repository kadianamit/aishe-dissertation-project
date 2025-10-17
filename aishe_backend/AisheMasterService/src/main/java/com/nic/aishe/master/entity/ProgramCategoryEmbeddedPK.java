package com.nic.aishe.master.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ProgramCategoryEmbeddedPK implements Serializable {

	@Column(name = "programme_id")
	private String id;
	@Column(name = "group_category_id")
	private String groupCategoryId;

	public ProgramCategoryEmbeddedPK() {

	}

	public ProgramCategoryEmbeddedPK(String id, String groupCategoryId) {
		this.id = id;
		this.groupCategoryId = groupCategoryId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(String groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProgramCategoryEmbeddedPK))
			return false;
		ProgramCategoryEmbeddedPK that = (ProgramCategoryEmbeddedPK) o;
		return Objects.equals(getId(), that.getId()) && Objects.equals(getGroupCategoryId(), that.getGroupCategoryId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getGroupCategoryId());
	}

}
