package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityTeachingVernacularNameServiceImpl implements UniversityTeachingvernacularNameService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityService.class);
	@Autowired
	UniversityTeachingVernacularNameDao vernacular;
	
	
	@Override
	public boolean saveOrUpdateUniversityvernacular(TeachingVernacularNamesEO vernacular) {
		logger.info("university serviceImpl : saveOrUpdateUniversityVernaculareNames method invoked : {}");
		
		
		
		
		return false;
	}

}
