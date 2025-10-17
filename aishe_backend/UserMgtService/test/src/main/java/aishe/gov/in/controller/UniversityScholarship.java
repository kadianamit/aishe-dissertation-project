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

import aishe.gov.in.utility.ReturnResponse;

@RestController
public class UniversityScholarship {

private static final Logger logger = LoggerFactory.getLogger(UniversityScholarship.class);
	
	@Autowired
	UniversityAisheService universityService;
	
	@PostMapping(value = "/saveOrUpdateUniversityScholarshipInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityScholarshipInformation(@RequestBody UniversityScholarshipVO universityScholarshipVO) throws ParseException
	{
		logger.info("university controller : saveOrUpdateUniversityScholarshipInformation method invoked : {}");
	
		boolean isUniversitySaved = universityService.saveOrUpdateUniversityScholarshipInformation(universityScholarshipVO);
		if (isUniversitySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/saveOrUpdateUniversityFellowshipInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityFellowshipInformation(@RequestBody UniversityFellowshipVO university) throws ParseException
	{
		logger.info("university controller : getBasicInfoByInstituteId method invoked : {}");
	
		boolean isUniversitySaved = universityService.saveOrUpdateUniversityFellowshipInformation(university);
		if (isUniversitySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/saveOrUpdateUniversityEducationLoanInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityEducationLoanInformation(@RequestBody UniversityLoanVO university) throws ParseException
	{
		logger.info("university controller : saveOrUpdateUniversityEducationLoanInformation method invoked : {}");
	
		boolean isUniversitySaved = universityService.saveOrUpdateUniversityEducationLoanInformation(university);
		if (isUniversitySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/saveOrUpdateUniversityAccrediationInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityAccrediationInformation(@RequestBody AccreditationVO accreditationVO) throws ParseException
	{
		logger.info("university controller : saveOrUpdateUniversityAccrediationInformation method invoked : {}");
		boolean isUniversitySaved = universityService.saveOrUpdateUniversityAccrediationInformation(accreditationVO);
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
