package aishe.gov.in.service;

public interface UniversityFacultyDepartmentService {
	
	public boolean saveOrUpdateUniversityFaculty(UniversityFaculty universityFaculty);

	public boolean saveOrUpdateUniversityFacultyDepartment(FacultyDepartment facultyDepartment);

	public boolean saveOrUpdateUniversityFacultyRegularProgramme(FacultyCourse facultyCourse);

	public boolean saveOrUpdateUniversityDepartmentRegularProgramme(DepartmentCourse departmentCourseEO);

	public boolean saveOrUpdateUniversityOtherProgramme(OtherCourseVO otherCourseVO);

}
