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
public class UniversityRegionalCentre {
	
	private static final Logger logger = LoggerFactory.getLogger(UniversityRegionalCentre.class);
	@Autowired
	UniversityRegionalCentreService universityRegionalCentreService;
	
	@PostMapping(value = "/saveOrUpdateUniversityRegionalCenter")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityRegionalCenter(@RequestBody RegionalCenter regionalCenter) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		
		UniversityEO universityEO=universityRegionalCentreService.getUniversity(regionalCenter.getUniversityId(),regionalCenter.getSurveyYear());
		boolean isUniversityUpdated=true;
		if(!universityEO.getOffersDistanceProgramme()) {
			 isUniversityUpdated=universityRegionalCentreService.updateUniversityRegionalCenter(regionalCenter.getProgrammeThroughDistanceMode(),regionalCenter.getUniversityId());
		}
		RegionalCenter regionalCenter2=universityRegionalCentreService.getRegionalCenter(regionalCenter.getUniversityId(),regionalCenter.getSurveyYear(),regionalCenter.getId());
		boolean isRegionalCenterSaved =false;		
		if(isUniversityUpdated) {
			String status="save";
			if(regionalCenter2!=null) {
				status="update";
			}
			 isRegionalCenterSaved = universityRegionalCentreService.saveOrUpdateRegionalCenters(regionalCenter,status);
		}	
		if (isRegionalCenterSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

}
