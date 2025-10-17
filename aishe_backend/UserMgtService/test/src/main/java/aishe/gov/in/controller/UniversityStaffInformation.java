package aishe.gov.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.utility.ReturnResponse;

@RestController
@RequestMapping("/staffinformation")
public class UniversityStaffInformation {
	private static final Logger logger1 = LoggerFactory.getLogger(UniversityStaffInformation.class);
 @Autowired
 UniversityTeachingStaffService universityTeachingStaffService;

 @RequestMapping("/teachingstaffdetail")
 public ResponseEntity<ReturnResponse> saveOrUpdateUniversityTeachingStaff(@RequestBody TeachingStaffDetailsEO teachingStaffDetails){
		logger1.info("university Exam Result controller : saveOrUpdateUniversityTeachingStaff method invoked : {}",
				teachingStaffDetails);
		boolean isSaved = universityTeachingStaffService.saveOrUpdateUniversityTeachingStaffDetail(teachingStaffDetails);
				
				
		if (isSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "staffDetails Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
}
