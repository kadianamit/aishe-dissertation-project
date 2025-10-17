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
public class UniversityTeachingStaff {
	private static final Logger logger1 = LoggerFactory.getLogger(UniversityTeachingStaff.class);
	@Autowired 
	UniversityTeachingStaffService teachingStaffservice;
	@PostMapping("/teachingstaff")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityTeachingStaff(@RequestBody TeachingStaffDetailsEO staffDetails){
		logger1.info("university TeachingStaff controller : saveOrUpdateUniversityTeaching staff method invoked : {}",
				staffDetails);

		boolean isSaved = teachingStaffservice.saveOrUpdateUniversityTeachingStaffDetail(staffDetails);

		//boolean isSaved = teachingStaffservice.saveOrUpdateUniversityTeachinfStaff(staffDetails);

		//boolean isSaved = true;
		
		
		//teachingStaffservice.saveOrUpdateUniversityTeachinfStaff(staffDetails);

				
				
		if (isSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "staff Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	
	
}
}
