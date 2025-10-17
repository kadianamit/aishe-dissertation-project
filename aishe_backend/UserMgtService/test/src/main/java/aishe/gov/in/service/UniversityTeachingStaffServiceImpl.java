package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UniversityTeachingStaffServiceImpl implements UniversityTeachingStaffService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityTeachingStaffServiceImpl.class);

	@Override
	public boolean saveOrUpdateUniversityTeachingStaffDetail(TeachingStaffDetailsEO teachingStaff) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*
	 * @Autowired UniversityTeachingStaffDao universityTeachingStaffDao;
	 * 
	 * @Override public boolean
	 * saveOrUpdateUniversityTeachingStaffDetail(TeachingStaffDetailsEO
	 * teachingStaff) { return
	 * universityTeachingStaffDao.saveOrUpdateUniversityTeachingStaff(teachingStaff)
	 * ; }
	 */

}
