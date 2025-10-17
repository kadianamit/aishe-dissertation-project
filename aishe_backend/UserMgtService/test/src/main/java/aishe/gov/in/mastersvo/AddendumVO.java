package aishe.gov.in.mastersvo;

import java.util.List;

public class AddendumVO {
	private Integer noOfRegionalCenters;
	private  List<UniversityRegionalCenterVO> regionalCenters;
	private List<DistanceProgrammDetailVO> distanceProgrammDetail;
	private  List<EnrolledStudentVO>  enrolledStudentDistance;
	
	public Integer getNoOfRegionalCenters() {
		return noOfRegionalCenters;
	}
	public void setNoOfRegionalCenters(Integer noOfRegionalCenters) {
		this.noOfRegionalCenters = noOfRegionalCenters;
	}	
	public List<UniversityRegionalCenterVO> getRegionalCenters() {
		return regionalCenters;
	}
	public void setRegionalCenters(List<UniversityRegionalCenterVO> regionalCenters) {
		this.regionalCenters = regionalCenters;
	}
	public List<DistanceProgrammDetailVO> getDistanceProgrammDetail() {
		return distanceProgrammDetail;
	}
	public void setDistanceProgrammDetail(List<DistanceProgrammDetailVO> distanceProgrammDetail) {
		this.distanceProgrammDetail = distanceProgrammDetail;
	}
	public List<EnrolledStudentVO> getEnrolledStudentDistance() {
		return enrolledStudentDistance;
	}
	public void setEnrolledStudentDistance(List<EnrolledStudentVO> enrolledStudentDistance) {
		this.enrolledStudentDistance = enrolledStudentDistance;
	}
	
}
