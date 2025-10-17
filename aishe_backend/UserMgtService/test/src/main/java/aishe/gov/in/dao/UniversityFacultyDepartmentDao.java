package aishe.gov.in.dao;

public interface UniversityFacultyDepartmentDao {
	
	public Boolean saveOrUpdateUniversityFaculty(UniversityFaculty universityFaculty,String status);

	public UniversityFaculty getUniversityFaculty(String universityId, Integer id);

	public FacultyDepartment getUniversityFacultyDepartment(Integer departmentId);

	public Boolean saveOrUpdateUniversityFacultyDepartment(FacultyDepartment facultyDepartment, String status);

	public FacultyCourse getUniversityFacultyRegularProgramme(Integer courseId);

	public Boolean saveOrUpdateUniversityFacultyRegularProgramme(FacultyCourse facultyCourse, String status);

	public Integer saveOrUpdateFaculty(Faculty faculty, String status);

	public Integer saveOrUpdateDepartment(Department department, String status);

	public DepartmentCourseEO getUniversityDepartmentRegularProgramme(Integer courseId);

	public Boolean saveOrUpdateUniversityDepartmentRegularProgramme(DepartmentCourse departmentCourseEO,String status);

	public UniversityCourseEO getUniversityOtherProgramme(Integer courseId, Integer surveyYear, String universityId);

	public Boolean saveOrUpdateUniversityOtherProgramme(OtherCourseVO otherCourseVO, String status);
		

}
