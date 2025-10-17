package aishe.gov.in.service;

public interface UniversityAisheService {

	boolean saveOrUpdateUniversityRegulatoryInformation(RegulatoryInformationVO regulatoryInformation);

	boolean saveOrUpdateUniversityOffShoreCentreInformation(UniversityOffShoreCenterVO offShoreCentre);

	boolean saveOrUpdateUniversityScholarshipInformation(UniversityScholarshipVO universityScholarshipVO);

	boolean saveOrUpdateUniversityAccrediationInformation(AccreditationVO accreditationVO);

	boolean saveOrUpdateUniversityFellowshipInformation(UniversityFellowshipVO university);

	boolean saveOrUpdateUniversityEducationLoanInformation(UniversityLoanVO university);
	
}
