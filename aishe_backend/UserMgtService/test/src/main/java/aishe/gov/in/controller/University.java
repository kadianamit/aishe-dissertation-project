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
public class University {
	
	private static final Logger logger = LoggerFactory.getLogger(University.class);
	
	@Autowired
	
	UniversityService universityService;
	
	
	@PostMapping(value = "/saveOrUpdateUniversityBasicInformation")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityBasicInformation(@RequestBody UniversityVO university) throws ParseException
	{
		logger.info("university controller : getBasicInfoByInstituteId method invoked : {}");
	
		boolean isUniversitySaved = universityService.saveOrUpdateUniversityBasicInformation(university);
		if (isUniversitySaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}

	
/*	@PostMapping(value = "/saveOrUpdateUniversityRegionalCenter")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityRegionalCenter(@RequestBody List<RegionalCenter> regionalCenter) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isRegionalCenterSaved = universityService.saveOrUpdateRegionalCenters(regionalCenter);
		if (isRegionalCenterSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
@PostMapping(value = "/saveOrUpdateFellowship")
	public ResponseEntity<ReturnResponse> saveOrUpdateFellowship(@RequestBody Fellowship fellowship) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isRegionalCenterSaved = universityService.saveOrUpdateFellowship(fellowship);
		if (isRegionalCenterSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateLoan")
	public ResponseEntity<ReturnResponse> saveOrUpdateLoan(@RequestBody Loan loan) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isLoanSaved = universityService.saveOrUpdateLoan(loan);
		if (isLoanSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateScholarship")
	public ResponseEntity<ReturnResponse> saveOrUpdateScholarship(@RequestBody Scholarship scholarship) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isScholarshipSaved = universityService.saveOrUpdateScholarship(scholarship);
		if (isScholarshipSaved) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateFinanceExpenditure")
	public ResponseEntity<ReturnResponse> saveOrUpdateFinanceExpenditure(@RequestBody FinancialExpendictureEO financialExpendicture) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isFinancialExpendicture = universityService.saveOrUpdateFinanceExpenditure(financialExpendicture);
		if (isFinancialExpendicture) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateFinanceIncome")
	public ResponseEntity<ReturnResponse> saveOrUpdateFinanceIncome(@RequestBody FinancialIncomeEO financialIncome) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isFinanceIncome = universityService.saveOrUpdateFinanceIncome(financialIncome);
		if (isFinanceIncome) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}
	@PostMapping(value = "/saveOrUpdateUniversityInfrastructure")
	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityInfrastructure(@RequestBody InfrastructureEO infrastructure) throws ParseException {
		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
		boolean isInfrastructure = universityService.saveOrUpdateUniversityInfrastructure(infrastructure);
		if (isInfrastructure) {
			return new ResponseEntity<>(
					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
		}
	}*/

	/*
	 * @GetMapping(value = "/saveOrUpdateUniversityProgramDetails") public
	 * ResponseVO getUniversityProgramDetails(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityProgramDetails method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityProgramDetails(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityStaff") public ResponseVO
	 * getUniversityStaffDetail(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityStaff method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityStaffDetail(surveyYear,universityId
	 * ); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityExaminationResult") public
	 * ResponseVO getUniversityExaminationResult(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityStaff method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityExaminationResult(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityInfrastructure") public
	 * ResponseVO saveOrUpdateUniversityInfrastructure(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityInfrastructure method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityInfrastructure(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 *
	 * 
	 * @GetMapping(value = "/getUniversityScholarShipLoanAndAccreditation") public
	 * ResponseVO
	 * saveOrUpdateUniversityScholarShipLoanAndAccreditation(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegionalCenter method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityScholarShipLoanAndAccreditation(
	 * surveyYear,universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityRegularityInfo") public ResponseVO
	 * getUniversityRegularityInfo(@RequestParam String aisheCode,@RequestParam
	 * Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegularityInfo method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityRegularityInfo(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityOffShoreCenter") public ResponseVO
	 * getUniversityOffShoreCenter(@RequestParam String aisheCode,@RequestParam
	 * Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegularityInfo method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityOffShoreCenter(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityStudentEnrolled") public ResponseVO
	 * getUniversityStudentEnrolledRegular(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityStudentEnrolled method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityStudentEnrolledRegular(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityTIF") public ResponseVO
	 * getUniversityTIF(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityTIF method invoked : {}");
	 * String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityTIF(surveyYear,universityId);
	 * return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityAddendum") public ResponseVO
	 * getUniversityAddendum(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityTIF method invoked : {}");
	 * String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityAddendum(surveyYear,universityId);
	 * return responseVO; }
	 */
	

	
//	@PostMapping(value = "/saveOrUpdateUniversityRegionalCenter")
//	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityRegionalCenter(@RequestBody List<RegionalCenter> regionalCenter) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isRegionalCenterSaved = universityService.saveOrUpdateRegionalCenters(regionalCenter);
//		if (isRegionalCenterSaved) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateFellowship")
//	public ResponseEntity<ReturnResponse> saveOrUpdateFellowship(@RequestBody Fellowship fellowship) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isRegionalCenterSaved = universityService.saveOrUpdateFellowship(fellowship);
//		if (isRegionalCenterSaved) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateLoan")
//	public ResponseEntity<ReturnResponse> saveOrUpdateLoan(@RequestBody Loan loan) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isLoanSaved = universityService.saveOrUpdateLoan(loan);
//		if (isLoanSaved) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateScholarship")
//	public ResponseEntity<ReturnResponse> saveOrUpdateScholarship(@RequestBody Scholarship scholarship) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isScholarshipSaved = universityService.saveOrUpdateScholarship(scholarship);
//		if (isScholarshipSaved) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateFinanceExpenditure")
//	public ResponseEntity<ReturnResponse> saveOrUpdateFinanceExpenditure(@RequestBody FinancialExpendictureEO financialExpendicture) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isFinancialExpendicture = universityService.saveOrUpdateFinanceExpenditure(financialExpendicture);
//		if (isFinancialExpendicture) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateFinanceIncome")
//	public ResponseEntity<ReturnResponse> saveOrUpdateFinanceIncome(@RequestBody FinancialIncomeEO financialIncome) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isFinanceIncome = universityService.saveOrUpdateFinanceIncome(financialIncome);
//		if (isFinanceIncome) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}
//	@PostMapping(value = "/saveOrUpdateUniversityInfrastructure")
//	public ResponseEntity<ReturnResponse> saveOrUpdateUniversityInfrastructure(@RequestBody InfrastructureEO infrastructure) throws ParseException {
//		logger.info("university controller : getUniversityRegionalCenter method invoked : {}");
//		boolean isInfrastructure = universityService.saveOrUpdateUniversityInfrastructure(infrastructure);
//		if (isInfrastructure) {
//			return new ResponseEntity<>(
//					new ReturnResponse(HttpStatus.OK.value(), "Institute Details has been  Successfully Saved!!"),
//					HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
//					"Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
//		}
//	}

	/*
	 * @GetMapping(value = "/saveOrUpdateUniversityProgramDetails") public
	 * ResponseVO getUniversityProgramDetails(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityProgramDetails method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityProgramDetails(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityStaff") public ResponseVO
	 * getUniversityStaffDetail(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityStaff method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityStaffDetail(surveyYear,universityId
	 * ); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityExaminationResult") public
	 * ResponseVO getUniversityExaminationResult(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityStaff method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityExaminationResult(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/saveOrUpdateUniversityInfrastructure") public
	 * ResponseVO saveOrUpdateUniversityInfrastructure(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityInfrastructure method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityInfrastructure(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 *
	 * 
	 * @GetMapping(value = "/getUniversityScholarShipLoanAndAccreditation") public
	 * ResponseVO
	 * saveOrUpdateUniversityScholarShipLoanAndAccreditation(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegionalCenter method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityScholarShipLoanAndAccreditation(
	 * surveyYear,universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityRegularityInfo") public ResponseVO
	 * getUniversityRegularityInfo(@RequestParam String aisheCode,@RequestParam
	 * Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegularityInfo method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityRegularityInfo(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityOffShoreCenter") public ResponseVO
	 * getUniversityOffShoreCenter(@RequestParam String aisheCode,@RequestParam
	 * Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityRegularityInfo method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityOffShoreCenter(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityStudentEnrolled") public ResponseVO
	 * getUniversityStudentEnrolledRegular(@RequestParam String
	 * aisheCode,@RequestParam Integer surveyYear) throws ParseException { logger.
	 * info("university controller : getUniversityStudentEnrolled method invoked : {}"
	 * ); String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityStudentEnrolledRegular(surveyYear,
	 * universityId); return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityTIF") public ResponseVO
	 * getUniversityTIF(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityTIF method invoked : {}");
	 * String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityTIF(surveyYear,universityId);
	 * return responseVO; }
	 * 
	 * @GetMapping(value = "/getUniversityAddendum") public ResponseVO
	 * getUniversityAddendum(@RequestParam String aisheCode,@RequestParam Integer
	 * surveyYear) throws ParseException {
	 * logger.info("university controller : getUniversityTIF method invoked : {}");
	 * String[] splitted = aisheCode.trim().split("\\s*-\\s*"); String
	 * universityId=splitted[1]; ResponseVO
	 * responseVO=universityService.getUniversityAddendum(surveyYear,universityId);
	 * return responseVO; }
	 */
	

}
