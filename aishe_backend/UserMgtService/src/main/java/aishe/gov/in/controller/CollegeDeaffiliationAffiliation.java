package aishe.gov.in.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.masterseo.CollegeEO;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.UniversityRef;
import aishe.gov.in.mastersvo.CollegeDeaffiliationAffiliationVO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.CollegeAffiliationDeaffiliationService;
import aishe.gov.in.service.CollegeStateDistrictService;
import aishe.gov.in.utility.AisheCodeConvertInID;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.ReturnResponse;

@RestController
public class CollegeDeaffiliationAffiliation {

    private static final Logger logger = LoggerFactory.getLogger(CollegeDeaffiliationAffiliation.class);

    @Autowired
    private AisheCodeConvertInID aisheCodeToId;

    @Autowired
    private CollegeAffiliationDeaffiliationService affiliationDeaffiliationService;

    @Autowired
    private CollegeStateDistrictService stateDistrictService;
//33947
    @PostMapping("/saveDeaffiliationAndAffiliation")
	public ResponseEntity<ReturnResponse> collegeDeaffiliationAffiliation(
			@RequestBody CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO  ,@WithUser UserInfo userInfo) {
    	//UserInfo userInfo = new UserInfo ("anwar.khan",null);
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("College Deaffiliation Affiliation controller : collegeDeaffiliationAffiliation method invoked : {}");
//        if(!(deaffiliationAffiliationVO.getUsername().equals("shivam.pandey") && deaffiliationAffiliationVO.getSecretKey().equals("MOEDIVISION@AISHE"))){
//        	 return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
//                     "You Are Not Authorized."), HttpStatus.UNAUTHORIZED);
//        }
//        if(deaffiliationAffiliationVO.getSurveyYear()!=2022 && deaffiliationAffiliationVO.getSurveyYear()!=2023){
//       	 return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
//                    "Survey Year Can Be Only 2022 or 2023."), HttpStatus.UNAUTHORIZED);
//       }
        if(deaffiliationAffiliationVO.getRoleId()==null && deaffiliationAffiliationVO.getRoleId()!=1){
       	 return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                    "You Are Not Authorized."), HttpStatus.UNAUTHORIZED);
       }
        Form_UploadBean formupload=affiliationDeaffiliationService.getFormUploadForCollege(aisheCodeToId.aisheCodeToId(deaffiliationAffiliationVO.getCollegeAisheCode()), deaffiliationAffiliationVO.getSurveyYear());
        if(formupload!=null) {
        	if(deaffiliationAffiliationVO.getReasonId()!=null && deaffiliationAffiliationVO.getReasonId()==1) {
        		return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                        "Shift University operation cannot be performed as this institution has already submitted the DCF for this survey year."), HttpStatus.UNAUTHORIZED);
        	}else {
        		return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                        "Cannot be Done. Institution has filled Webdcf for this Survey Year"), HttpStatus.UNAUTHORIZED);
        	}
        }
        String collegeId = aisheCodeToId.aisheCodeToId(deaffiliationAffiliationVO.getCollegeAisheCode());
        String universityId = aisheCodeToId.aisheCodeToId(deaffiliationAffiliationVO.getUniversityAisheCode());
        CollegeEO collegeEO = affiliationDeaffiliationService.getCollegeMaster(collegeId, deaffiliationAffiliationVO.getSurveyYear());
        UniversityRef newUniversityRef = affiliationDeaffiliationService.getUniversityMaster(universityId, deaffiliationAffiliationVO.getSurveyYear());
        if (null == collegeEO) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.NOT_FOUND.value(),
                    "College not found for AISHE code C-" + collegeId + " for survey year " + deaffiliationAffiliationVO.getSurveyYear()), HttpStatus.NOT_FOUND);
        }
        if (null == newUniversityRef) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.NOT_FOUND.value(),
                    "University not found for AISHE code U-" + universityId + " for survey year " + deaffiliationAffiliationVO.getSurveyYear()), HttpStatus.NOT_FOUND);
        }
        UniversityRef oldUniversityRef = affiliationDeaffiliationService.getUniversityMaster(collegeEO.getUniversityId(), deaffiliationAffiliationVO.getSurveyYear());
        if (null == oldUniversityRef) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.NOT_FOUND.value(),
                    "University not found for AISHE code U-" + 
            oldUniversityRef.getId() + " for survey year " + deaffiliationAffiliationVO.getSurveyYear()), HttpStatus.NOT_FOUND);
        } else {
            deaffiliationAffiliationVO.setCollegeAisheCode(collegeId);
            deaffiliationAffiliationVO.setUniversityAisheCode(universityId);
            Boolean isSaved = affiliationDeaffiliationService.saveUpdateCollegeAffiliationDeaffiliation(deaffiliationAffiliationVO, collegeEO, oldUniversityRef, newUniversityRef,userInfo);
            if (isSaved) {
            	if(deaffiliationAffiliationVO.getReasonId()!=null && deaffiliationAffiliationVO.getReasonId()==1) {
            		return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "College C-" + deaffiliationAffiliationVO.getCollegeAisheCode() + " has been successfully Shifted from U-"
                            + oldUniversityRef.getId() + "( " + oldUniversityRef.getName() + " , " + stateDistrictService.getDistrictByCode(oldUniversityRef.getDistrictCode()).getName() + " , "
                            + stateDistrictService.getStateByCode(oldUniversityRef.getStateCode()).getName() + " )" + " and Now Affiliated to U-" + newUniversityRef.getId() + "( " + newUniversityRef.getName() + " , "
                            + stateDistrictService.getDistrictByCode(newUniversityRef.getDistrictCode()).getName() + " , " + stateDistrictService.getStateByCode(newUniversityRef.getStateCode()).getName() + " )"),
                            HttpStatus.OK);
            	}else {
            		return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "College C-" + deaffiliationAffiliationVO.getCollegeAisheCode() + " has been successfully Deffiliated from U-"
                            + oldUniversityRef.getId() + "( " + oldUniversityRef.getName() + " , " + stateDistrictService.getDistrictByCode(oldUniversityRef.getDistrictCode()).getName() + " , "
                            + stateDistrictService.getStateByCode(oldUniversityRef.getStateCode()).getName() + " )" + " and affiliated to U-" + newUniversityRef.getId() + "( " + newUniversityRef.getName() + " , "
                            + stateDistrictService.getDistrictByCode(newUniversityRef.getDistrictCode()).getName() + " , " + stateDistrictService.getStateByCode(newUniversityRef.getStateCode()).getName() + " )"),
                            HttpStatus.OK);            		
            	}
            
            } else {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);

            }
        }
    }
}
