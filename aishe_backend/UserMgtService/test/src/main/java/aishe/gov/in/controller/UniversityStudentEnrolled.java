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
public class UniversityStudentEnrolled {
	private static final Logger logger = LoggerFactory.getLogger(University.class);
	
	@Autowired
	UniversityEnrolledStudentService universityEnrolledStudentService;
	
	@PostMapping(value = "/api/course/studentThroughFaculty")	
	public ResponseEntity<ReturnResponse> studentThroughFaculty(@RequestBody CourseEnrolledStudentCountEO countCourseEnrolledStudentCountEO) throws ParseException
	{
		logger.info("universityStudentEnrolled controller : saveOrUpdateRegularStudentEnrolled method invoked : {}");
		boolean isUniversityStudentEnrolledSaved = universityEnrolledStudentService.saveOrUpdateStudentThroughFaculty(countCourseEnrolledStudentCountEO);
		if (isUniversityStudentEnrolledSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "universityStudentEnrolled Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}	
	
	/*
	 * @PostMapping(value = "/saveOrUpdateStudentEnrolledThroughFaculty") public
	 * ResponseEntity<ReturnResponse>
	 * saveOrUpdateStudentEnrolledThroughFaculty(@RequestBody StudentEnrolledCountVO
	 * enrolledStudentCountEO) throws ParseException { logger.
	 * info("universityStudentEnrolled controller : saveOrUpdateRegularStudentEnrolled method invoked : {}"
	 * ); boolean isUniversityStudentEnrolledSaved =
	 * universityEnrolledStudentService.saveOrUpdateStudentEnrolled(
	 * enrolledStudentCountEO); if (isUniversityStudentEnrolledSaved) { return new
	 * ResponseEntity<>( new ReturnResponse(HttpStatus.OK.value(),
	 * "universityStudentEnrolled Details has been  Successfully Saved!!"),
	 * HttpStatus.OK); } else { return new ResponseEntity<>(new
	 * ReturnResponse(HttpStatus.BAD_REQUEST.value(),
	 * "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST); }
	 * }
	 * 
	 * @PostMapping(value = "/saveOrUpdateRegularStudentEnrolled") public
	 * ResponseEntity<ReturnResponse>
	 * saveOrUpdateRegularStudentEnrolled(@RequestBody StudentEnrolledCountVO
	 * studentEnrolledCountVO) throws ParseException { logger.
	 * info("universityStudentEnrolled controller : saveOrUpdateRegularStudentEnrolled method invoked : {}"
	 * ); boolean isUniversityStudentEnrolledSaved =
	 * universityEnrolledStudentService.saveOrUpdateStudentEnrolled(
	 * studentEnrolledCountVO); if (isUniversityStudentEnrolledSaved) { return new
	 * ResponseEntity<>( new ReturnResponse(HttpStatus.OK.value(),
	 * "universityStudentEnrolled Details has been  Successfully Saved!!"),
	 * HttpStatus.OK); } else { return new ResponseEntity<>(new
	 * ReturnResponse(HttpStatus.BAD_REQUEST.value(),
	 * "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST); }
	 * }
	 * 
	 * @PostMapping(value = "/saveOrUpdateDistanceStudentEnrolled") public
	 * ResponseEntity<ReturnResponse>
	 * saveOrUpdateDistanceStudentEnrolled(@RequestBody StudentEnrolledCountVO
	 * studentEnrolledCountVO) throws ParseException { logger.
	 * info("universityDistanceStudentEnrolled controller : saveOrUpdateDistanceStudentEnrolled method invoked : {}"
	 * ); boolean isUniversityStudentEnrolledSaved =
	 * universityEnrolledStudentService.saveOrUpdateDistanceStudentEnrolled(
	 * studentEnrolledCountVO); if (isUniversityStudentEnrolledSaved) { return new
	 * ResponseEntity<>( new ReturnResponse(HttpStatus.OK.value(),
	 * "universityDistanceStudentEnrolled Details has been  Successfully Saved!!"),
	 * HttpStatus.OK); } else { return new ResponseEntity<>(new
	 * ReturnResponse(HttpStatus.BAD_REQUEST.value(),
	 * "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST); }
	 * }
	 */
	@PostMapping(value = "/saveOrUpdateOtherMinorityStudentEnrolled")	
	public ResponseEntity<ReturnResponse> saveOrUpdateOtherMinorityStudentEnrolled(@RequestBody StudentEnrolledCountVO studentEnrolledCountVO) throws ParseException
	{
		logger.info("universityDistanceStudentEnrolled controller : saveOrUpdateDistanceStudentEnrolled method invoked : {}");
		boolean isUniversityStudentEnrolledSaved = universityEnrolledStudentService.saveOrUpdateDistanceStudentEnrolled(studentEnrolledCountVO);
		if (isUniversityStudentEnrolledSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "universityDistanceStudentEnrolled Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

}
