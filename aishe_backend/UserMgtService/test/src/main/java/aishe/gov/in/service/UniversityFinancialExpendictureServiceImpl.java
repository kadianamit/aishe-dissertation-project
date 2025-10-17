package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityFinancialExpendictureServiceImpl implements UniversityFinancialExpendictureService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityFinancialExpendictureServiceImpl.class);
	@Autowired
	UniversityFinancialExpendictureDao  universityFinancialExpendictureDao;
	
	@Override
	public boolean saveOrUpdateUniversityFinancialExpendicture(FinancialExpendictureEO financialIncomeEO) {
		
		return universityFinancialExpendictureDao.saveOrUpdateUniversityFinancialExpendicture(financialIncomeEO);
	}

}
