package org.nicexam.authorizationservice.responseDto;

//@Data
public class PenDetailStatusResDTO {
	private String status;
	private String pen;
	private String name;
	private String applicationNo;
	private String submitionDate;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPen() {
		return pen;
	}

	public void setPen(String pen) {
		this.pen = pen;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApplicationNo() {
		return applicationNo;
	}

	public void setApplicationNo(String applicationNo) {
		this.applicationNo = applicationNo;
	}

	public String getSubmitionDate() {
		return submitionDate;
	}

	public void setSubmitionDate(String submitionDate) {
		this.submitionDate = submitionDate;
	}

}
