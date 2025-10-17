package aishe.gov.in.service;

import aishe.gov.in.masterseo.UniversityEO;

public interface UniversityService {
	
	public boolean saveOrUpdateUniversityBasicInformation(UniversityVO university);

	public boolean saveOrUpdateUniversityAddress(UniversityEO university);	
	
}
