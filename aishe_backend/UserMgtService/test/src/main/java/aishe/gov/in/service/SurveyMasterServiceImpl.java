package aishe.gov.in.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyMasterServiceImpl implements SurveyMasterService{
	
	@Autowired
	SurveyMasterDao surveyMasterDao;

	@Override
	public SurveyMasterEO getLatestClosedSurveyYearDetails() {
		// TODO Auto-generated method stub
		return surveyMasterDao.getLatestClosedSurveyYearDetails();
	}

}
