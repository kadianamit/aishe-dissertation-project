package aishe.gov.in.dao;

import aishe.gov.in.masterseo.UniversityEO;

public interface UniversityAisheDao {

	boolean saveOrUpdateUniversityRegulatoryInformation(UniversityEO university);

	boolean saveOrUpdateUniversityOffShoreCentreInformation(UniversityOffShoreCenterVO offShoreCentre);

	UniversityEO fetchUniversityInformation(String universityId, Integer surveyYear);

	boolean saveOrUpdateUniversityAccrediationInformation(AccreditationVO accreditationVO);

	boolean saveOrUpdateUniversityScholarshipInformation(UniversityScholarshipVO universityScholarshipVO);

	boolean saveOrUpdateUniversityFellowshipInformation(UniversityFellowshipVO university);

	boolean saveOrUpdateUniversityEducationLoanInformation(UniversityLoanVO university);
}