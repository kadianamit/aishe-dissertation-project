package aishe.gov.in.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UniversityFinancialIncomeServiceImpl implements UniversityFinancialIncomeService {
	private static final Logger logger = LoggerFactory.getLogger(UniversityFinancialIncomeServiceImpl.class);
	@Autowired
	UniversityFinancialIncomeDao universityFinancialIncome;

	@Override
	public boolean saveOrUpdateUniversityFinancialIncome(FinancialIncomeEO financialIncomeEO) {

		return universityFinancialIncome.saveOrUpdateUniversityFinancialIncome(financialIncomeEO);
	}

}
