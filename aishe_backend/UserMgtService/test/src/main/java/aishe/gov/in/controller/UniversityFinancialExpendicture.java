package aishe.gov.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.utility.ReturnResponse;

@RestController
public class UniversityFinancialExpendicture {
	private static final Logger logger = LoggerFactory.getLogger(UniversityFinancialExpendicture.class);
   @Autowired
   UniversityFinancialExpendictureService universityFinancialExpendictureService;
   @PostMapping("/financialExpendicture")
   public ResponseEntity<ReturnResponse> saveOrUpdateUniversityFinancialExpendicture(@RequestBody FinancialExpendictureEO financialExpendicture){
	   logger.info("university Exam Result controller : saveOrUpdateUniversityExamResult method invoked : {}",
				financialExpendicture); 
	   boolean isSaved = universityFinancialExpendictureService.saveOrUpdateUniversityFinancialExpendicture(financialExpendicture);
				
				
		if (isSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "FinancialExpendicture Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
   }
	
}
