package com.nic.aishe.master.controller;

import com.nic.aishe.master.dto.CustomReponseStatus;
import com.nic.aishe.master.dto.ReturnResponse;
import com.nic.aishe.master.dto.ServiceResponse;
import com.nic.aishe.master.enums.EnumDetails.EligibilityStatus;
import com.nic.aishe.master.enums.EnumDetails.InstituitionCategory;
import com.nic.aishe.master.enums.StatusMaster;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.repo.SurveyMasterRepo;
import com.nic.aishe.master.service.KnowYourAisheCodeService;
import com.nic.aishe.master.util.CommonObjectOperation;
import com.nic.aishe.master.util.NullValueHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;

@RequestMapping("/api")
@RestController
public class KnowYourAisheCodeController {
    private static final Logger logger = LoggerFactory.getLogger(KnowYourAisheCodeController.class);


    @Autowired(required = false)
    private SurveyMasterRepo surveyMasterRepo;

    @Autowired
    private KnowYourAisheCodeService yourAisheCodeService;

    @Autowired
    private NullValueHandler valueHandler;


    @GetMapping("/know-your-aishe_code")
    public ReturnResponse knowYourAisheCode(@RequestParam String aisheCode, @RequestParam(required = false) Integer surveyYear) {
        if (!aisheCode.contains("-"))
            return new ReturnResponse(HttpStatus.EXPECTATION_FAILED.value(), "Invalid aishe code ", null);
        if(CommonObjectOperation.stringValidate(aisheCode)){
            if(!CommonObjectOperation.sevenLength(aisheCode)){
                throw new InvalidInputException("AisheCode length should be less then 7 ");
            }
        }
        surveyYear = surveyYear != null ? surveyYear : surveyMasterRepo.maxSurvey();
        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        String instituteType = splitted[0].toUpperCase();
        switch (instituteType.toUpperCase()) {
            case "C":
            case "U":
            case "S":
                return yourAisheCodeService.collegeAisheCode(instituteType, splitted[1], surveyYear);

        }
        return new ReturnResponse(HttpStatus.EXPECTATION_FAILED.value(), "Invalid aishe code ", null);
    }


    @RequestMapping(value = "/knowYourAisheCodeList", method = RequestMethod.GET)
    public ServiceResponse knowYourAisheCode(@RequestParam String stateCode,
                                             @RequestParam InstituitionCategory institutionCategory,
                                             @RequestParam(defaultValue = "Eligible", required = true, value = "status") EligibilityStatus status,
                                             @RequestParam(required = false) String subCategory,
                                             @RequestParam(required = false) String universityId,
                                             @RequestParam(required = false) String districtCode,
                                             @RequestParam(required = false) String surveyYear) {
        ServiceResponse serviceResponse = new ServiceResponse();
        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
        CustomReponseStatus customResponse = null;
        if (null != valueHandler.nullValueHandler(universityId) && !(institutionCategory.getInstituitionCategory().equalsIgnoreCase("C"))) {
            String error = "Invalid input Parameters .";
            customResponse = new CustomReponseStatus(StatusMaster.FAILED.getResponseId(),
                    StatusMaster.FAILED.getResponseCode(), "Parameter expectation failed.", error);
            response.put("response", customResponse);
            serviceResponse.setServiceResponse(response);
            return serviceResponse;
        }
        if(CommonObjectOperation.stringValidate(stateCode)){
            if(!CommonObjectOperation.threeLength(stateCode)){
                throw new InvalidInputException("StateCode length should be less then 3 ");
            }
        }

        if(CommonObjectOperation.stringValidate(subCategory)){
            if(!CommonObjectOperation.threeLength(subCategory)){
                throw new InvalidInputException("Sub-category length should be less then 3 ");
            }
        }
        if(CommonObjectOperation.stringValidate(universityId)){
            if(!CommonObjectOperation.fiveLength(universityId)){
                throw new InvalidInputException("University length should be less then 5 ");
            }
        }
        if(CommonObjectOperation.stringValidate(districtCode)){
            if(!CommonObjectOperation.sevenLength(districtCode)){
                throw new InvalidInputException("District Code length should be less then 7 ");
            }
        }
        if(CommonObjectOperation.stringValidate(surveyYear)){
            if(!CommonObjectOperation.fiveLength(surveyYear)){
                throw new InvalidInputException("Survey Year length should be less then 5 ");
            }
        }
        return yourAisheCodeService.fetchAisheDetails(valueHandler.nullValueHandler(stateCode), institutionCategory,
                valueHandler.nullValueHandler(surveyYear), status, valueHandler.nullValueHandler(subCategory)
                , valueHandler.nullValueHandler(districtCode), valueHandler.nullValueHandler(universityId));
    }

}
