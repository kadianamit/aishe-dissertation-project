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
public class UniversityFacultyDepartment {
	
private static final Logger logger = LoggerFactory.getLogger(UniversityFacultyDepartment.class);
	
	
	@Autowired
	UniversityFacultyDepartmentService universityFacultyDepartmentService;
	
	
	@PostMapping(value = "/saveOrUpdateUniversityFaculty")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityFaculty(@RequestBody UniversityFaculty universityFaculty) throws ParseException
	{
		logger.info("universityFaculty controller : saveOrUpdateUniversityFaculty method invoked : {}");
		boolean isUniversityFacultySaved = universityFacultyDepartmentService.saveOrUpdateUniversityFaculty(universityFaculty);
		if (isUniversityFacultySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "UniversityFaculty Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateDepartment")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityFacultyDepartment(@RequestBody FacultyDepartment facultyDepartment) throws ParseException
	{
		logger.info("universityFacultyDepartment controller : saveOrUpdateUniversityFaculty method invoked : {}");
		boolean isUniversityFacultyDepartmentSaved = universityFacultyDepartmentService.saveOrUpdateUniversityFacultyDepartment(facultyDepartment);
		if (isUniversityFacultyDepartmentSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "UniversityFaculty Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateCourse/{faculty}")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityFacultyProgramme(@RequestBody FacultyCourse facultyCourse) throws ParseException
	{
		logger.info("facultyCourse controller : saveOrUpdateUniversityFacultyProgramme method invoked : {}");
		boolean isUniversityFacultyRegularProgrammeSaved = universityFacultyDepartmentService.saveOrUpdateUniversityFacultyRegularProgramme(facultyCourse);
		if (isUniversityFacultyRegularProgrammeSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "UniversityFacultyCourse Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping(value = "/saveOrUpdateCourse/{dept}")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityDepartmentProgramme(@RequestBody DepartmentCourse departmentCourseEO) throws ParseException
	{
		logger.info("departmentCourse controller : saveOrUpdateUniversityDepartmentProgramme method invoked : {}");
		boolean isUniversityDepartmentRegularProgrammeSaved = universityFacultyDepartmentService.saveOrUpdateUniversityDepartmentRegularProgramme(departmentCourseEO);
		if (isUniversityDepartmentRegularProgrammeSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "UniversityDepartmentCourse Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateCourse/{other}")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityOtherProgramme(@RequestBody OtherCourseVO otherCourseVO) throws ParseException
	{
		logger.info("otherCourse controller : saveOrUpdateUniversityOtherProgramme method invoked : {}");
		boolean isUniversityDepartmentRegularProgrammeSaved = universityFacultyDepartmentService.saveOrUpdateUniversityOtherProgramme(otherCourseVO);
		if (isUniversityDepartmentRegularProgrammeSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "UniversityOtherCourse Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
					
	}

} 
