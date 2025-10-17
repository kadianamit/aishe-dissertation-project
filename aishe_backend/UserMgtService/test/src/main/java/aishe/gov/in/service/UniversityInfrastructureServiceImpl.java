package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityInfrastructureServiceImpl implements UniversityInfrastructureService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityInfrastructureServiceImpl.class);
	@Autowired
   UniversityInfrastructureDao  universityInfrastructureDao;
	@Override
	public boolean saveOrUpdateUniversityInfrastructure(Infrastructure infrastructure) {
		return universityInfrastructureDao.saveOrUpdateUniversityInfrastructure(infrastructure);
	}

}
