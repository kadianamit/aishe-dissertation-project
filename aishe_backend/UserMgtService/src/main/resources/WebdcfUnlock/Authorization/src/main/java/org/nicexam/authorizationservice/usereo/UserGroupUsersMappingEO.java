package org.nicexam.authorizationservice.usereo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "m_user_group_users_mapping")
public class UserGroupUsersMappingEO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4725789538433563314L;
	@Id
	@Column(name = "record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int recordId;
	@Column(name = "user_record_id")
	private int userRecordId;
	@Column(name = "user_group_record_id")
	private int userGroupRecordId;
	@Column(name = "eba")
	private String eba;
	@Column(name = "exam")
	private String exam;
	@Column(name = "session")
	private String session;
	@Column(name = "count")
	private int count;
	@Column(name = "is_active")
	private boolean isActive;
	@Column(name = "created_on")
	private Date createdOn;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "updated_on")
	private Date updatedOn;
	@Column(name = "updated_by")
	private String updatedBy;

	public int getRecordId() {
		return recordId;
	}

	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public int getUserRecordId() {
		return userRecordId;
	}

	public void setUserRecordId(int userRecordId) {
		this.userRecordId = userRecordId;
	}

	public int getUserGroupRecordId() {
		return userGroupRecordId;
	}

	public void setUserGroupRecordId(int userGroupRecordId) {
		this.userGroupRecordId = userGroupRecordId;
	}

	public String getEba() {
		return eba;
	}

	public void setEba(String eba) {
		this.eba = eba;
	}

	public String getExam() {
		return exam;
	}

	public void setExam(String exam) {
		this.exam = exam;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
