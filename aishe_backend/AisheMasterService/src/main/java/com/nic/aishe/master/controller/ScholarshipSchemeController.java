package com.nic.aishe.master.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nic.aishe.master.entity.RefScholarshipSchemeEO;
import com.nic.aishe.master.handler.InvalidInputException;
import com.nic.aishe.master.repo.RefScholarshipSchemeRepo;
import com.nic.aishe.master.util.CommonObjectOperation;

//@CrossOrigin("*")
@RequestMapping("/api")
@RestController
public class ScholarshipSchemeController {

    @Autowired(required = false)
    private RefScholarshipSchemeRepo jobStatusRepo;


    @GetMapping("/scholarship-scheme")
    public List<RefScholarshipSchemeEO> getAllType(@RequestParam(required=false) String stateCode,@RequestParam(required=false) Integer surveyYear) {
        if (CommonObjectOperation.stringValidate(stateCode)) {
            if (!CommonObjectOperation.threeLength(stateCode)) {
                throw new InvalidInputException("State Code length should be less then 3 ");
            }
        }
        if(stateCode!=null && stateCode.equalsIgnoreCase("ALL")) {
        	return jobStatusRepo.findAllRefScholarshipScheme();
        }
        if(stateCode!=null && !stateCode.equalsIgnoreCase("ALL") && surveyYear==null) {
        	return jobStatusRepo.findAllRefScholarshipSchemeByState(stateCode);
        }
        else if(stateCode!=null && stateCode.equalsIgnoreCase("ALL") && surveyYear!=null) {
        	return jobStatusRepo.findAllRefScholarshipSchemeBySurveyYear(surveyYear);
        }
    	else {
    		return jobStatusRepo.findAllRefScholarshipSchemeByStateAndSurveyYear(stateCode,surveyYear);
    	}
    }

    @GetMapping("/scholarship-scheme-private")
    public List<RefScholarshipSchemeEO> getAllTypePrivate(@RequestParam(required=false) String stateCode,@RequestParam(required=false) Integer surveyYear) {
        if (CommonObjectOperation.stringValidate(stateCode)) {
            if (!CommonObjectOperation.threeLength(stateCode)) {
                throw new InvalidInputException("State Code length should be less then 3 ");
            }
        }
        if(stateCode!=null && stateCode.equalsIgnoreCase("ALL")) {
            return jobStatusRepo.findAllRefScholarshipScheme();
        }
        if(stateCode!=null && !stateCode.equalsIgnoreCase("ALL") && surveyYear==null) {
            return jobStatusRepo.findAllRefScholarshipSchemeByState(stateCode);
        }
        else if(stateCode!=null && stateCode.equalsIgnoreCase("ALL") && surveyYear!=null) {
            return jobStatusRepo.findAllRefScholarshipSchemeBySurveyYear(surveyYear);
        }
        else {
            return jobStatusRepo.findAllRefScholarshipSchemeByStateAndSurveyYear(stateCode,surveyYear);
        }
    }
}