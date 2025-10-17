package org.nicexam.authorizationservice.requestDto;

public class TeacherProfileHistoryUpdateRequestDto {
	String loginId;
	String status; // rejected or approved
	String remarks;
	String approvedBy;
	Long teacherProfileHistoryId;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTeacherProfileHistoryId() {
		return teacherProfileHistoryId;
	}

	public void setTeacherProfileHistoryId(Long teacherProfileHistoryId) {
		this.teacherProfileHistoryId = teacherProfileHistoryId;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

}
