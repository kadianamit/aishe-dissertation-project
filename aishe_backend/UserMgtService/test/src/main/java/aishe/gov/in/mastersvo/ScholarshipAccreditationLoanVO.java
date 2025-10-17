package aishe.gov.in.mastersvo;

import java.util.List;
import java.util.Map;

public class ScholarshipAccreditationLoanVO {
	
	private Boolean scholarshipData;
	private Boolean fellowshipData;
	private Boolean loanData;
	private Boolean accreditation;
	private Map<String, List<CategoryDTO>> scholarship;
	private Map<String, List<CategoryDTO>> fellowship;
	private Map<String, List<CategoryDTO>> loan;
	private AccreditationVO accreditationVO;
	
	//private List<Map<String, Object>> data;
	public Boolean getScholarshipData() {
		return scholarshipData;
	}
	public void setScholarshipData(Boolean scholarshipData) {
		this.scholarshipData = scholarshipData;
	}
	public Boolean getFellowshipData() {
		return fellowshipData;
	}
	public void setFellowshipData(Boolean fellowshipData) {
		this.fellowshipData = fellowshipData;
	}
	public Boolean getLoanData() {
		return loanData;
	}
	public void setLoanData(Boolean loanData) {
		this.loanData = loanData;
	}
	public Boolean getAccreditation() {
		return accreditation;
	}
	public void setAccreditation(Boolean accreditation) {
		this.accreditation = accreditation;
	}
	public Map<String, List<CategoryDTO>> getScholarship() {
		return scholarship;
	}
	public void setScholarship(Map<String, List<CategoryDTO>> scholarship) {
		this.scholarship = scholarship;
	}
	public Map<String, List<CategoryDTO>> getFellowship() {
		return fellowship;
	}
	public void setFellowship(Map<String, List<CategoryDTO>> fellowship) {
		this.fellowship = fellowship;
	}
	public Map<String, List<CategoryDTO>> getLoan() {
		return loan;
	}
	public void setLoan(Map<String, List<CategoryDTO>> loan) {
		this.loan = loan;
	}
	public AccreditationVO getAccreditationVO() {
		return accreditationVO;
	}
	public void setAccreditationVO(AccreditationVO accreditationVO) {
		this.accreditationVO = accreditationVO;
	}
	
}
