package aishe.gov.in.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityEnrolledStudentServiceImpl implements UniversityEnrolledStudentService{
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityFacultyDepartmentServiceImpl.class);
	@Autowired
	UniversityEnrolledStudentDao universityEnrolledStudentDao;

	@Override
	public boolean saveOrUpdateStudentEnrolled(StudentEnrolledCountVO studentEnrolledCountVO) {
		logger.info("universityEnrolledStudent serviceImpl : saveOrUpdateStudentEnrolled method invoked : {}");
		
		UniversityEnrolledRegularStudentEO universityEnrolledRegularStudentEO=universityEnrolledStudentDao.getUniversityEnrolledRegularStudent(studentEnrolledCountVO.getAisheCode(),studentEnrolledCountVO.getId(),studentEnrolledCountVO.getSurveyYear());
		String status="save";
		if(universityEnrolledRegularStudentEO!=null) {
			status="update";
		}
		Integer enrolledRegularStudentId=universityEnrolledStudentDao.saveOrUpdateEnrolledRegularStudent(status,studentEnrolledCountVO.getAisheCode(),studentEnrolledCountVO.getId(),studentEnrolledCountVO.getSurveyYear(),studentEnrolledCountVO.getFaculty(),studentEnrolledCountVO.getDepartment());
		
		EnrolledRegularStudentCountEO enrolledRegularStudentCountEO=universityEnrolledStudentDao.getEnrolledRegularStudentCount(studentEnrolledCountVO.getId(),studentEnrolledCountVO.getStudentEnrollments().getId());
		String studentCountStatus="save";
		if(enrolledRegularStudentCountEO!=null) {
			studentCountStatus="update";
		}
		/*
		 * if(studentCountStatus.equalsIgnoreCase("save")) { Integer
		 * personCountByCategoryId=universityEnrolledStudentDao.
		 * savePersonCountByCategory(studentEnrolledCountVO.getStudentEnrollments().
		 * getPersonCategoryCount()); PersonsCountByCategory
		 * personsCountByCategory=studentEnrolledCountVO.getStudentEnrollments().
		 * getPersonCategoryCount();
		 * personsCountByCategory.setId(personCountByCategoryId); }
		 */
		Integer isStudentCountSaved=universityEnrolledStudentDao.saveOrUpdateStudentCount( studentCountStatus,enrolledRegularStudentId,studentEnrolledCountVO.getStudentEnrollments());
		
		
		return true;
	}

	@Override
	public boolean saveOrUpdateDistanceStudentEnrolled(StudentEnrolledCountVO studentEnrolledCountVO) {
		logger.info("universityEnrolledStudent serviceImpl : saveOrUpdateStudentEnrolled method invoked : {}");
		
		UniversityEnrolledDistanceStudentEO universityEnrolledDistanceStudentEO=universityEnrolledStudentDao.getUniversityEnrolledDistanceStudent(studentEnrolledCountVO.getAisheCode(),studentEnrolledCountVO.getId(),studentEnrolledCountVO.getSurveyYear());
		String status="save";
		if(universityEnrolledDistanceStudentEO!=null) {
			status="update";
		}
		Integer enrolledDistanceStudentId=universityEnrolledStudentDao.saveOrUpdateEnrolledDistanceStudent(status,studentEnrolledCountVO.getAisheCode(),studentEnrolledCountVO.getId(),studentEnrolledCountVO.getSurveyYear(),studentEnrolledCountVO.getFaculty(),studentEnrolledCountVO.getDepartment());
		
		EnrolledRegularStudentCountEO enrolledRegularStudentCountEO=universityEnrolledStudentDao.getEnrolledRegularStudentCount(studentEnrolledCountVO.getId(),studentEnrolledCountVO.getStudentEnrollments().getId());
		String studentCountStatus="save";
		if(enrolledRegularStudentCountEO!=null) {
			studentCountStatus="update";
		}
		Integer isStudentCountSaved=universityEnrolledStudentDao.saveOrUpdateStudentCount( studentCountStatus,enrolledDistanceStudentId,studentEnrolledCountVO.getStudentEnrollments());
		
		return true;
	}

	@Override
	public boolean saveOrUpdateStudentThroughFaculty(CourseEnrolledStudentCountEO countCourseEnrolledStudentCountEO) {
		logger.info("universityEnrolledStudent serviceImpl : saveOrUpdateStudentEnrolled method invoked : {}");
		
		CourseEnrolledStudentCountEO courseEnrolledStudentCount=universityEnrolledStudentDao.getCourseEnrolledStudent(countCourseEnrolledStudentCountEO.getCourse().getId(),countCourseEnrolledStudentCountEO.getEnrolledStudentCount().getId());
		String status="save";
		if(courseEnrolledStudentCount!=null) {
			status="update";
		}
		Integer enrolledRegularStudentId=universityEnrolledStudentDao.saveOrUpdateStudentThroughFaculty(status,countCourseEnrolledStudentCountEO);
		if(countCourseEnrolledStudentCountEO.getEnrolledStudentCount().getOtherMinorityCategoryCounts().size()>0) {
			List<OtherMinorityCategoryCountEO> otherMinorityCategoryCountList=countCourseEnrolledStudentCountEO.getEnrolledStudentCount().getOtherMinorityCategoryCounts();
			for(OtherMinorityCategoryCountEO otherMinorityCategoryCount:otherMinorityCategoryCountList) {
				EnrolledStudentCountOtherMinorityPersonsCountByCategoryEO enrolledStudentCountOtherMinorityPersonsCountByCategoryEO=universityEnrolledStudentDao.getOtherMinority(enrolledRegularStudentId,otherMinorityCategoryCount.getId());
				String otherStatus="save";
				if(enrolledStudentCountOtherMinorityPersonsCountByCategoryEO!=null) {
					otherStatus="update";
				}
				Boolean isOtherMinoritySaved=universityEnrolledStudentDao.saveOtherMinorityPersonsCount(status,enrolledRegularStudentId,otherMinorityCategoryCount);
			}
		}
		
		
		return true;
	}

}
