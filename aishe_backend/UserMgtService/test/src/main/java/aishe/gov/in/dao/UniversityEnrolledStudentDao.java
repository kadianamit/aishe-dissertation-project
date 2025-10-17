package aishe.gov.in.dao;

public interface UniversityEnrolledStudentDao {
	
	public UniversityEnrolledRegularStudentEO getUniversityEnrolledRegularStudent(String universityId, Integer enrolledRegularStudentId,Integer surveyYear);

	public Integer saveOrUpdateEnrolledRegularStudent(String status,String universityId, Integer enrolledRegularStudentId, Integer surveyYear,String faculty, String department);

	public EnrolledRegularStudentCountEO getEnrolledRegularStudentCount(Integer enrolledRegularStudentId, Integer enrolledStudentCountId);

	public Integer saveOrUpdateStudentCount(String status, Integer enrolledRegularStudentId,EnrolledStudentCountEO studentEnrollment);

	public Integer savePersonCountByCategory(PersonsCountByCategory personCategoryCount);

	public UniversityEnrolledDistanceStudentEO getUniversityEnrolledDistanceStudent(String universityId, Integer enrolledDistanceStudentId,Integer surveyYear);

	public Integer saveOrUpdateEnrolledDistanceStudent(String status, String universityId, Integer enrolledDistanceStudentId,Integer surveyYear, String faculty, String department);

	public CourseEnrolledStudentCountEO getCourseEnrolledStudent(Integer courseId, Integer enrolledStudentCountId);

	public Integer saveOrUpdateStudentThroughFaculty(String status,CourseEnrolledStudentCountEO countCourseEnrolledStudentCountEO);

	public EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO getOtherMinority(Integer enrolledRegularStudentId,Integer otherMinorityCategoryCountId);

	public Boolean saveOtherMinorityPersonsCount(String status, Integer enrolledRegularStudentId,OtherMinorityCategoryCountEO otherMinorityCategoryCount);
	

}
