package aishe.gov.in.dao;

import aishe.gov.in.masterseo.UniversityEO;

public interface UniversityRegionalCentreDao {
	
	public Boolean saveOrUpdateRegionalCenter(RegionalCenter regionalCenter,String status);

	public Boolean updateUniversityRegionalCenter(Boolean programmeThroughDistanceMode, String universityId);

	public RegionalCenter getRegionalCenter(String universityId, Integer surveyYear, Integer id);

	public UniversityEO getUniversity(String universityId, Integer surveyYear);


}
