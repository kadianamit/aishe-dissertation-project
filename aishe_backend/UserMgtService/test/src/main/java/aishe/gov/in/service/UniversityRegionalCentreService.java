package aishe.gov.in.service;

import aishe.gov.in.masterseo.UniversityEO;

public interface UniversityRegionalCentreService {
	
	public boolean saveOrUpdateRegionalCenters(RegionalCenter regionalCenter,String status);

	public boolean updateUniversityRegionalCenter(Boolean programmeThroughDistanceMode, String universityId);

	public RegionalCenter getRegionalCenter(String universityId, Integer surveyYear, Integer id);

	public UniversityEO getUniversity(String universityId, Integer surveyYear);
	


}
