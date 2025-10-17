package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityFacultyDepartmentServiceImpl implements UniversityFacultyDepartmentService{
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityFacultyDepartmentServiceImpl.class);
	@Autowired
	UniversityFacultyDepartmentDao universityFacultyDepartmentDao;

	@Override
	public boolean saveOrUpdateUniversityFaculty(UniversityFaculty universityFaculty) {
		logger.info("universityFaculty serviceImpl : saveOrUpdateUniversityFaculty method invoked : {}");
		
		UniversityFaculty universityFacultyEO=universityFacultyDepartmentDao.getUniversityFaculty(universityFaculty.getUniversityId(),universityFaculty.getFaculty().getId());
		String status="save";
		if(universityFacultyEO!=null) {
			status="update";
		}
		Boolean isUniversityFacultySaved = true;
		universityFacultyDepartmentDao.saveOrUpdateFaculty(universityFaculty.getFaculty(),status);
		isUniversityFacultySaved = universityFacultyDepartmentDao.saveOrUpdateUniversityFaculty(universityFaculty,status);
		
		return isUniversityFacultySaved;
	}

	@Override
	public boolean saveOrUpdateUniversityFacultyDepartment(FacultyDepartment facultyDepartment) {
		logger.info("universityFacultyDepartment serviceImpl : saveOrUpdateUniversityFacultyDepartment method invoked : {}");
		
		FacultyDepartment facultyDepartmentEO=universityFacultyDepartmentDao.getUniversityFacultyDepartment(facultyDepartment.getDepartment().getId());
		String status="save";
		if(facultyDepartmentEO!=null) {
			status="update";
		}
		Boolean isUniversityFacultyDepartmentSaved = false;
		isUniversityFacultyDepartmentSaved = universityFacultyDepartmentDao.saveOrUpdateUniversityFacultyDepartment(facultyDepartment,status);
		return isUniversityFacultyDepartmentSaved;
	}

	@Override
	public boolean saveOrUpdateUniversityFacultyRegularProgramme(FacultyCourse facultyCourse) {
		logger.info("universityFacultyDepartment serviceImpl : saveOrUpdateUniversityFacultyDepartment method invoked : {}");
		FacultyCourse facultyCourseEO = universityFacultyDepartmentDao.getUniversityFacultyRegularProgramme(facultyCourse.getCourseId().getId());
		String status = "save";
		if (facultyCourseEO != null) {
			status = "update";
		}
		Boolean isUniversityFacultyDepartmentSaved = false;
		isUniversityFacultyDepartmentSaved = universityFacultyDepartmentDao.saveOrUpdateUniversityFacultyRegularProgramme(facultyCourse, status);
		return isUniversityFacultyDepartmentSaved;
	}

	@Override
	public boolean saveOrUpdateUniversityDepartmentRegularProgramme(DepartmentCourse departmentCourseEO) {
		logger.info("universityFacultyDepartment serviceImpl : saveOrUpdateUniversityFacultyDepartment method invoked : {}");
		//FacultyCourse facultyCourseEO = universityFacultyDepartmentDao.getUniversityFacultyRegularProgramme(facultyCourse.getCourseId().getId());
		DepartmentCourseEO departmentCourse = universityFacultyDepartmentDao.getUniversityDepartmentRegularProgramme(15);
		String status = "save";
		if (departmentCourse != null) {
			status = "update";
		}
		Boolean isUniversityFacultyDepartmentSaved = false;
		isUniversityFacultyDepartmentSaved = universityFacultyDepartmentDao.saveOrUpdateUniversityDepartmentRegularProgramme(departmentCourseEO, status);
		return isUniversityFacultyDepartmentSaved;
	}

	@Override
	public boolean saveOrUpdateUniversityOtherProgramme(OtherCourseVO otherCourseVO) {
		logger.info("universityFacultyDepartment serviceImpl : saveOrUpdateUniversityOtherProgramme method invoked : {}");
		//FacultyCourse facultyCourseEO = universityFacultyDepartmentDao.getUniversityFacultyRegularProgramme(facultyCourse.getCourseId().getId());
		UniversityCourseEO universityCourse = universityFacultyDepartmentDao.getUniversityOtherProgramme(otherCourseVO.getId(),otherCourseVO.getSurveyYear(),otherCourseVO.getAisheCode());
		String status = "save";
		if (universityCourse != null) {
			status = "update";
		}
		Boolean isUniversityOtherCourseSaved = false;
		isUniversityOtherCourseSaved = universityFacultyDepartmentDao.saveOrUpdateUniversityOtherProgramme(otherCourseVO, status);
		return isUniversityOtherCourseSaved;
	}

}
