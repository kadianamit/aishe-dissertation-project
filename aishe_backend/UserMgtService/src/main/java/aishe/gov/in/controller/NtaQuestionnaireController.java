package aishe.gov.in.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import aishe.gov.in.utility.CommanObjectOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.NtaSortBy;
import aishe.gov.in.enums.States;
import aishe.gov.in.masterseo.NtaQuestionnaire;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUserRequestService;
import aishe.gov.in.utility.NullValueHandler;
import aishe.gov.in.utility.ReturnResponse;

@RestController
public class NtaQuestionnaireController {
    @Autowired(required = true)
    private AisheUserRequestService aisheUserRequestService;
    @Autowired

    private NullValueHandler handler;


    @GetMapping(value = "/nta-questionnaire")
    public ResponseEntity<ReturnResponse> getNtaQuestionNaire(@RequestParam(required = false) String aisheCode,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        NtaQuestionnaire masterEO = aisheUserRequestService.getNtaQuestionNaire(aisheCode);
        if (masterEO != null && masterEO.getMessage().equals("Aishe Code Not Participated in Survey Year 2021")) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "Aishe Code Not Participated in Survey Year 2021", masterEO.getMessage());
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success", masterEO);
        if (masterEO == null && masterEO.getMessage().equals("Elligible")) {
            returnResponse = new ReturnResponse(HttpStatus.OK.value(), "NO RECORD EXIST", "Aishe Code Elligible");
        }
        if (masterEO.getMessage().equals("IN Elligible")) {
            returnResponse = new ReturnResponse(HttpStatus.OK.value(), "NO RECORD EXIST", "Aishe Code Not Elligible");
        }
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/nta-questionnaire")
    public ResponseEntity<ReturnResponse> saveOrUpdateNtaQuestionNaire(@RequestBody NtaQuestionnaire userMaster,
    		HttpServletRequest request,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        NtaQuestionnaire masterEO = aisheUserRequestService.saveOrUpdateNtaQuestionNaire(userMaster,userInfo,request);
        ReturnResponse returnResponse = null;
        returnResponse = ((masterEO != null)
                ? new ReturnResponse(HttpStatus.OK.value(), "Nta Questionnaire Added Successfully.")
                : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Not Success."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/nta-question-report")
    public void getReportExcel(@RequestParam(required=false) States states, @RequestParam InstitutionType institutionType, @RequestParam NtaSortBy ntaSortBy, HttpServletResponse response,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        aisheUserRequestService.getReportExcel(institutionType == null ? InstitutionType.ALL : institutionType, handler.allAssignForNull(states == null ? States.ALL.getId() : states.getId()), ntaSortBy, response);
    }
}