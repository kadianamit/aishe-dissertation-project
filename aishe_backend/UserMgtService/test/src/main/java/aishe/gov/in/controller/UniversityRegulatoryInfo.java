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
public class UniversityRegulatoryInfo {

private static final Logger logger = LoggerFactory.getLogger(UniversityRegulatoryInfo.class);
	
	@Autowired
	UniversityAisheService universityService;
	
	@PostMapping(value = "/saveOrUpdateUniversityRegulatoryInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityRegulatoryInformation
	(@RequestBody RegulatoryInformationVO regulatoryInformation) throws ParseException	{
		logger.info("university controller : saveOrUpdateUniversityRegulatoryInformation method invoked : {}");
		boolean isRegulatorySaved = universityService.saveOrUpdateUniversityRegulatoryInformation(regulatoryInformation);
		if (isRegulatorySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "University Regulatory Info Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
}
