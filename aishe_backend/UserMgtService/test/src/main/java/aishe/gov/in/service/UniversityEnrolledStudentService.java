package aishe.gov.in.service;

public interface UniversityEnrolledStudentService {
	
	public boolean saveOrUpdateStudentEnrolled(StudentEnrolledCountVO studentEnrolledCountVO);

	public boolean saveOrUpdateDistanceStudentEnrolled(StudentEnrolledCountVO studentEnrolledCountVO);

	public boolean saveOrUpdateStudentThroughFaculty(CourseEnrolledStudentCountEO countCourseEnrolledStudentCountEO);

}
