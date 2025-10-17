package aishe.gov.in.controller;


import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.masterseo.UniversityEO;
import aishe.gov.in.utility.ReturnResponse;



@RestController

public class UniversityInfrastructure {
	private static final Logger logger1 = LoggerFactory.getLogger(UniversityInfrastructure.class);
	@Autowired 
	UniversityInfrastructureService  universityInfrastructureService;
	
	@PostMapping("/Infrastructure")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityInfrastructure(@RequestBody Infrastructure infrastructure){
		logger1.info("university Exam Result controller : saveOrUpdateUniversityInfrastructure method invoked : {}",
				infrastructure);
		boolean isInfrastuructureSaved = universityInfrastructureService.saveOrUpdateUniversityInfrastructure(infrastructure);
				
				
		if (isInfrastuructureSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "infrastructure Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
		
	}
private static final Logger logger = LoggerFactory.getLogger(UniversityInfrastructure.class);
	
//	@Autowired

//	UniversityAisheService universityService;
	
	//@PostMapping(value = "/saveOrUpdateUniversityInfrastructureInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityBasicInformation(@RequestBody UniversityEO university) throws ParseException
	{
		logger1.info("university controller : getBasicInfoByInstituteId method invoked : {}");
	
		boolean isUniversitySaved = true;//universityService.saveOrUpdateUniversityBasicInformation(university);
		if (isUniversitySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	
}
