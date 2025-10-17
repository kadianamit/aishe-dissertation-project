package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.masterseo.UniversityEO;

@Service
public class UniversityRegionalCentreServiceImpl implements UniversityRegionalCentreService{
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);
	@Autowired
	UniversityRegionalCentreDao universityRegionalCentreDao;

	@Override
	public boolean saveOrUpdateRegionalCenters(RegionalCenter regionalCenter,String status) {
		logger.info("university serviceImpl : saveOrUpdateRegionalCenters method invoked : {}");
		
		Boolean isRegionalCenterSaved=false;
		isRegionalCenterSaved=universityRegionalCentreDao.saveOrUpdateRegionalCenter(regionalCenter,status);			
		return isRegionalCenterSaved;
	}

	@Override
	public boolean updateUniversityRegionalCenter(Boolean programmeThroughDistanceMode, String universityId) {
		logger.info("university serviceImpl : updateUniversityRegionalCenter method invoked : {}");
		
		Boolean isUniversityRegionalCenterUpdated=false;
		isUniversityRegionalCenterUpdated=universityRegionalCentreDao.updateUniversityRegionalCenter(programmeThroughDistanceMode,universityId);			
		return isUniversityRegionalCenterUpdated;
	}

	@Override
	public RegionalCenter getRegionalCenter(String universityId, Integer surveyYear, Integer id) {
		// TODO Auto-generated method stub
		return universityRegionalCentreDao.getRegionalCenter(universityId,surveyYear,id);
	}

	@Override
	public UniversityEO getUniversity(String universityId, Integer surveyYear) {
		// TODO Auto-generated method stub
		return universityRegionalCentreDao.getUniversity(universityId,surveyYear);
	}
	
	

}
