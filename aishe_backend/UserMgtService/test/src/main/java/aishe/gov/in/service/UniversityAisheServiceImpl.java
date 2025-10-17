package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.masterseo.UniversityEO;


@Service
public class UniversityAisheServiceImpl implements UniversityAisheService {
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityAisheServiceImpl.class);
	@Autowired
	UniversityAisheDao universityDao;

	@Override
	public boolean saveOrUpdateUniversityRegulatoryInformation(RegulatoryInformationVO regulatoryInformation) {
		UniversityEO university = universityDao.fetchUniversityInformation(regulatoryInformation.getUniversityId(),
				regulatoryInformation.getSurveyYear());
		university.setIsUniversityUploadedActStatues(regulatoryInformation.getIsUniversityUploadStatusOnWebsite());
		university.setIsUniversityComplyingRules(regulatoryInformation.getIsUniversityComplyingUgcRule());
		university.setIsUniversity180ActualTeachingDays(regulatoryInformation.getIsUniversityMinimumActualTeachingDays());
		return universityDao.saveOrUpdateUniversityRegulatoryInformation(university);
	}

	@Override
	public boolean saveOrUpdateUniversityOffShoreCentreInformation(UniversityOffShoreCenterVO offShoreCentre) {
		return universityDao.saveOrUpdateUniversityOffShoreCentreInformation(offShoreCentre);
	}

	@Override
	public boolean saveOrUpdateUniversityScholarshipInformation(UniversityScholarshipVO universityScholarshipVO) {
		return universityDao.saveOrUpdateUniversityScholarshipInformation(universityScholarshipVO);
	}

	@Override
	public boolean saveOrUpdateUniversityAccrediationInformation(AccreditationVO accreditationVO) {
		return universityDao.saveOrUpdateUniversityAccrediationInformation(accreditationVO);
	}

	@Override
	public boolean saveOrUpdateUniversityFellowshipInformation(UniversityFellowshipVO university) {
		return universityDao.saveOrUpdateUniversityFellowshipInformation(university);
	}

	@Override
	public boolean saveOrUpdateUniversityEducationLoanInformation(UniversityLoanVO university) {
		return universityDao.saveOrUpdateUniversityEducationLoanInformation(university);
	}	
		}