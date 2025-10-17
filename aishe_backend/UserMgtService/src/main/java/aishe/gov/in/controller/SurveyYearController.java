package aishe.gov.in.controller;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import aishe.gov.in.utility.CommanObjectOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.enums.SurveyActionLog;
import aishe.gov.in.masterseo.RefSurveyAction;
import aishe.gov.in.mastersvo.SurveyMasterDTO;
import aishe.gov.in.mastersvo.SurveyMasterNewVO;
import aishe.gov.in.mastersvo.SurveyStatusDTO;
import aishe.gov.in.mastersvo.SurveyStatusWithUserStatusDTO;
import aishe.gov.in.mastersvo.YearDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUserRequestService;
import aishe.gov.in.service.SurveyYearService;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.ReturnResponse;

@RestController
//@CrossOrigin("*")
public class SurveyYearController {
    private static final Logger logger = LoggerFactory.getLogger(ActivityScheduleController.class);
    @Autowired(required = true)
    private AisheUserRequestService aisheUserRequestService;

    @Autowired
    private SurveyYearService surveyYearService;


    @PostMapping(value = "/saveSurveyYear")
    public ResponseEntity<ReturnResponse> saveSurveyYear(@RequestBody @Valid SurveyMasterDTO surveyMasterEO, BindingResult bindingResult,@WithUser UserInfo userInfo) throws ParseException, ExecutionException, InterruptedException {
       CommanObjectOperation.usernameValidate(userInfo);
        logger.info("SurveyYearController : saveSurveyYear method invoked : {}");
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        SurveyMasterDTO masterEO = surveyYearService.saveSurveyYear(surveyMasterEO);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.CREATED.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PutMapping(value = "/editSurveyYear")
    public ResponseEntity<ReturnResponse> editSurveyYear(@RequestBody @Valid SurveyMasterDTO surveyMaster, BindingResult bindingResult,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("SurveyYearController : saveSurveyYear method invoked : {}");
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        SurveyMasterDTO masterEO = surveyYearService.editSurveyYear(surveyMaster);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "updated", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/getSurveyMaster")
    public ResponseEntity<ReturnResponse> getSurveyMasterYear(@RequestParam(required = false) Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        SurveyMasterDTO masterEO = surveyYearService.getSurveyMasterYear(surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/getSurveyMasterList")
    public ResponseEntity<ReturnResponse> getSurveyMasterYearList(@RequestParam(required = false) Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<SurveyMasterDTO> masterEO = surveyYearService.getSurveyMasterYearList(surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/getSurveyStatus")
    public ResponseEntity<ReturnResponse> getSurveyYearStatus(@RequestParam Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<SurveyStatusDTO> masterEO = surveyYearService.getSurveyYearStatus(surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/getSurveyLog")
    public ResponseEntity<ReturnResponse> getSurveyYearLog(@RequestParam Integer surveyYear, @RequestParam(required=false) SurveyActionLog actionLog,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        ReturnResponse returnResponse = surveyYearService.getSurveyYearLog(surveyYear, actionLog);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PutMapping(value = "/freezeSurveyYear")
    public ResponseEntity<ReturnResponse> freezeSurveyYear(@Valid @RequestBody List<SurveyStatusWithUserStatusDTO> statusDTO, BindingResult bindingResult,@WithUser UserInfo userInfo) throws ParseException {
        logger.info("SurveyYearController : saveSurveyYear method invoked : {}");
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

        List<SurveyStatusWithUserStatusDTO> list = surveyYearService.freezeSurveyYear(statusDTO);
        if (null != list) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "updated ", list);
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "cannot process Entity"), HttpStatus.UNPROCESSABLE_ENTITY);

    }


    @GetMapping(value = "/surveyyearforaddinstituterequest")
    public Map<String, List<String>> surveyYearForAddInstituteRequest(){
        logger.info("university controller : fetchCollegeCourse method invoked : {}");
        Integer lsyActive = aisheUserRequestService.minSurveyYearForAddInstituteRequestPrior();
        List<Integer> syfai = aisheUserRequestService.surveyYearForAddInstituteRequest();
        Map<String, List<String>> syListMap = new HashMap<>();
        List<String> syList = new ArrayList<>();
        for (int i = 0; i < syfai.size(); i++) {
            syList.add(String.valueOf(syfai.get(i)) + "-" + String.valueOf(syfai.get(i) + 1));
        }
        if (lsyActive != null) {
            syList.add("Prior To" + " " + lsyActive + "-" + (lsyActive + 1));
        }
        syListMap.put("Survey Year For Request Add Institute Activity", syList);
        return syListMap;
    }

    @GetMapping(value = "/opensurveyyearforaishe")
    public List<String> surveyYearForAishe() throws ParseException {
        logger.info("university controller : surveyYearForAishe method invoked : {}");
        List<Integer> syfai = aisheUserRequestService.surveyYearForAisheOpen();
        List<String> syList = new ArrayList<>();
        for (int i = 0; i < syfai.size(); i++) {
            syList.add(String.valueOf(syfai.get(i)) + "-" + String.valueOf(syfai.get(i) + 1));
        }
        return syList;
    }

    @GetMapping(value = "/opensurveyyearforaishe-private")
    public List<String> surveyYearForAishePrivate(@WithUser UserInfo userInfo) throws ParseException {
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("university controller : surveyYearForAishe method invoked : {}");
        List<Integer> syfai = aisheUserRequestService.surveyYearForAisheOpen();
        List<String> syList = new ArrayList<>();
        for (int i = 0; i < syfai.size(); i++) {
            syList.add(String.valueOf(syfai.get(i)) + "-" + String.valueOf(syfai.get(i) + 1));
        }
        return syList;
    }

    @GetMapping(value = "/getYear")
    public ResponseEntity<ReturnResponse> getSurveyYear(@RequestParam(required = false) Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        YearDTO masterEO = surveyYearService.getYear(surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/surveyaction")
    public ResponseEntity<ReturnResponse> getSurveyAction(@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<RefSurveyAction> masterEO = surveyYearService.getSurveyAction();
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/surveymasternew")
    public ResponseEntity<ReturnResponse> getSurveyMaster(@RequestParam(required=false) Integer surveyYear,@RequestParam (defaultValue = "false") Boolean isSurveyFreezed,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<SurveyMasterNewVO> masterEO = surveyYearService.getSurveyMaster(surveyYear,isSurveyFreezed);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", masterEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }  
    
    @PostMapping(value = "/saveupdatesurveymasternew")
	public ResponseEntity<ReturnResponse> saveUpdateSurveyMasterNew(@RequestBody @Valid SurveyMasterDTO surveyMasterEO,
			BindingResult bindingResult ,@WithUser UserInfo userInfo ) throws ParseException, ExecutionException, InterruptedException {
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("SurveyYearController : saveSurveyYear method invoked : {}");
        SurveyMasterDTO masterEO=new SurveyMasterDTO();
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        switch (surveyMasterEO.getSurveyCreationType()) {
		case 1:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 2:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 3:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 4:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 5:
			masterEO = surveyYearService.saveRegistrationSurveyYear(surveyMasterEO);
			break;
		case 6:
			masterEO = surveyYearService.saveRegistrationSurveyYear(surveyMasterEO);
			break;
		case 7:
			masterEO = surveyYearService.saveRegistrationSurveyYear(surveyMasterEO);
			break;
		case 8:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 9:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 10:
			masterEO = surveyYearService.saveSurveyYearNew(surveyMasterEO);
			break;
		case 11:
			masterEO = surveyYearService.saveSurveyYearSpecialPermission(surveyMasterEO);
			break;
		case 12:
			masterEO = surveyYearService.saveSurveyYearSpecialPermission(surveyMasterEO);
			break;
		case 13:
			masterEO = surveyYearService.saveSurveyYearSpecialPermission(surveyMasterEO);
			break;
		case 14:
			masterEO = surveyYearService.saveUpdateFreezeSurveyYear(surveyMasterEO);
			break;
		default:
			break;
		}
        ReturnResponse returnResponse=new ReturnResponse();
        if(masterEO.getMessage()!=null){
        if(masterEO.getMessage().equals("Survey Year is Already Created.") || masterEO.getMessage().equals("Registration Survey Year Is Already Created.")) {
        	returnResponse = new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "can not done", masterEO);
        }
        if(masterEO.getMessage().equals("Success")){
       	 returnResponse = new ReturnResponse(HttpStatus.CREATED.value(), "success", masterEO);
       } 
        }
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/localdatetime")
    public String getSurveyMaster() {
        String date =DateUtils.convertDBDateTimeToStringNew();
        return date;
    } 
    
    @GetMapping(value = "/localdatetimeorig")
    public LocalDateTime getSurveyMasterTime() {
    	LocalDateTime dbDate = LocalDateTime.now();
        return dbDate;
    } 
}