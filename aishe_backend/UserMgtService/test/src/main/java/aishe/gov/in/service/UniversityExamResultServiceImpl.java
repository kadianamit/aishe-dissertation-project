package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.masterseo.RefCourseLevel;
import aishe.gov.in.masterseo.RefCourseMode;

@Service
public class UniversityExamResultServiceImpl implements UniversityExamResultService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);

	@Autowired
	UniversityExamResultDao universityExamResult;

	@Override
	public boolean saveOrUpdateUniversityExamResult(ExaminationResultEO examinationResultEo) {
		
		logger.info("university serviceImpl : saveOrUpdateUniversityExamResult method invoked : {}");
		  ExaminationResultEO examinationResult=new ExaminationResultEO();
		  RefCourseMode refCourseMode=new RefCourseMode();
		  RefCourseLevel refCourseLevel=new RefCourseLevel();
		  RefProgramme refProgramme=new RefProgramme();
		  RefBroadDisciplineGroup refBroadDisciplineGroup=new RefBroadDisciplineGroup();
		  RefBroadDisciplineGroupCategory refBroadDisciplineGroupCategory=new RefBroadDisciplineGroupCategory();
		  
		  if(examinationResult!=null) {
			  examinationResult.setId(examinationResult.getId());
			  examinationResult.setDiscipline(examinationResult.getDiscipline());
			  examinationResult.setAppearedTotal(examinationResult.getAppearedTotal());
			  examinationResult.setAppearedFemale(examinationResult.getAppearedTotal());
			  examinationResult.setPassedTotal(examinationResult.getPassedTotal());
			  examinationResult.setPassedFemale(examinationResult.getPassedFemale());
			  examinationResult.setFirstClassPassedFemale(examinationResult.getFirstClassPassedFemale());
			  examinationResult.setFirstClassPassedTotal(examinationResult.getFirstClassPassedTotal());
			  refCourseMode.setId(refCourseMode.getId());
			  refCourseMode.setMode(refCourseMode.getMode());
			  refCourseLevel.setId(refCourseLevel.getId());
			  refCourseLevel.setLevel(refCourseLevel.getLevel());
			  refCourseLevel.setName(refCourseLevel.getName());
			  refProgramme.setId(refProgramme.getId());
			  refProgramme.setCourseLevelId(refProgramme.getCourseLevelId());
			  refProgramme.setProgramme(refProgramme.getProgramme());
			  refBroadDisciplineGroup.setId(refBroadDisciplineGroup.getId());
			  refBroadDisciplineGroup.setDisciplineGroup(refBroadDisciplineGroup.getDisciplineGroup());
			  refBroadDisciplineGroupCategory.setId(refBroadDisciplineGroup.getId());
			  refBroadDisciplineGroupCategory.setDisciplineGroupCategory(refBroadDisciplineGroupCategory.getDisciplineGroupCategory());
			  
		  }
		return universityExamResult.saveOrUpdateUniversityExamResult(examinationResultEo);

	}

}
