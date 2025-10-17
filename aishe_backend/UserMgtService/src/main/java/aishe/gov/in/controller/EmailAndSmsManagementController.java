package aishe.gov.in.controller;

import aishe.gov.in.enums.InstitutionTypeNew;
import aishe.gov.in.enums.InstitutionUserType;
import aishe.gov.in.enums.SurveyParticipatedStatus;
import aishe.gov.in.masterseo.UserContactDetail;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.UserContactDetailService;
import aishe.gov.in.utility.CommanObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class EmailAndSmsManagementController {
    @Autowired private UserContactDetailService contactDetailService;
    @GetMapping(value = "/contact-detail")
    public List<UserContactDetail> userContactDetail(@RequestParam String stateCode,
                                                               @RequestParam InstitutionTypeNew institutionType,
                                                               @RequestParam(required = false) String subCategory,
                                                               @RequestParam(required = false) Integer surveyYear,
                                                               @RequestParam(required = false) String districtCode,
                                                               @RequestParam(required = false) InstitutionUserType userType,
                                                               @RequestParam(required = false) SurveyParticipatedStatus participatedStatus,
                                                     @RequestParam(required = false) String universityId,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        return contactDetailService.userContactDetail(stateCode,institutionType,subCategory,surveyYear,districtCode,
                userType,participatedStatus,universityId);

    }
}
