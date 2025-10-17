package aishe.gov.in.controller;

import aishe.gov.in.masterseo.ActivityMasterEO;
import aishe.gov.in.masterseo.RefActivityActionEO;
import aishe.gov.in.masterseo.RefActivityEO;
import aishe.gov.in.mastersvo.ActivityMasterVO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.AisheUserRequestService;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.ReturnResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
//@CrossOrigin("*")
public class ActivityScheduleController {
    private static final Logger logger1 = LoggerFactory.getLogger(ActivityScheduleController.class);
    @Autowired(required = true)
    private AisheUserRequestService aisheUserRequestService;

    @GetMapping(value = "/refactivity")
    public List<RefActivityEO> activity( @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        logger1.info("university controller : activity method invoked : {}");
        List<RefActivityEO> collegeCourse = aisheUserRequestService.activity();
        return collegeCourse;
    }

    @GetMapping(value = "/refactivityaction")
    public List<RefActivityActionEO> activityAction(@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        logger1.info("university controller : fetchCollegeCourse method invoked : {}");
        List<RefActivityActionEO> collegeCourse = aisheUserRequestService.activityAction();
        return collegeCourse;
    }

    @PostMapping(value = "/saveactivity")
    public ResponseEntity<ReturnResponse> saveOrUpdateActivity(@RequestBody ActivityMasterEO activityMaster,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        logger1.info("ForeignInstitute controller : saveOrUpdateActivity method invoked : {}");
        Boolean isSaved = aisheUserRequestService.saveOrUpdateActivity(activityMaster);
        if (isSaved) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "Activity Details has been  Successfully Saved!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/save-activity-master-new")
    public ResponseEntity<ReturnResponse> saveOrUpdateActivityMaster(@RequestBody @Valid ActivityMasterVO activityMaster, BindingResult bindingResult,
                                                                     @WithUser UserInfo userInfo,
                                                                     HttpServletRequest request) {
        CommanObjectOperation.usernameValidate(userInfo);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        logger1.info("ActivityScheduleController controller : saveOrUpdateActivityMaster method invoked : {}");
        Boolean isSaved = aisheUserRequestService.saveOrUpdateActivityMaster(activityMaster, userInfo, request);
        if (isSaved) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "Activity Details has been  Successfully Saved!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/get-activity-master")
    public ResponseEntity<ReturnResponse> getActivityMaster(@RequestParam(required = false) Integer surveyYear, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List list = aisheUserRequestService.getActivityMaster(surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", list);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @DeleteMapping(value = "/delete-activity-master")
    public ResponseEntity<ReturnResponse> deleteActivityMaster(@RequestParam Integer id, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        Boolean isSaved = aisheUserRequestService.deleteActivityMaster(id);
        if (isSaved) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "Activity Detail has been Successfully Deleted!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }
}
