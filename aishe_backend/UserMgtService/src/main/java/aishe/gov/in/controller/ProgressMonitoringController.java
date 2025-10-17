package aishe.gov.in.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import aishe.gov.in.enums.ListType;
import aishe.gov.in.mastersvo.FinalProgressMonitoringDayWiseDTO;
import aishe.gov.in.mastersvo.FormUploadDateWiseDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringBasicDTO;
import aishe.gov.in.mastersvo.ProgressMontioringDetailDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aishe.gov.in.enums.FormType;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.mastersvo.ProgressMoniteringDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringDTO;
import aishe.gov.in.service.ProgressMonitoringService;
import aishe.gov.in.utility.ReturnResponse;

@RestController
@RequestMapping("/api")
public class ProgressMonitoringController {

    @Autowired
    private ProgressMonitoringService monitoringService;

    @GetMapping(value = "/progressMonitoring")
    public ResponseEntity<ReturnResponse> getProgressMonitoring(@RequestParam Integer surveyYear,
                                                                @RequestParam(required = false) String stateCode,
                                                                @RequestParam(required = false) String universityId,
                                                                @RequestParam(required = false) String fromDate,
                                                                @RequestParam(required = false) String toDate, @RequestParam InstitutionType institutionType, @RequestParam(required = false) Integer roleId, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<ProgressMoniteringDTO> registeredUserDTO = monitoringService.getProgressMonitoring(surveyYear, stateCode, universityId, fromDate, toDate, institutionType, roleId);

        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", registeredUserDTO != null ? registeredUserDTO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/progressMonitoringByType")
    public ResponseEntity<ReturnResponse> getInstituteDetailOfProgressMonitoring(@RequestParam Integer surveyYear,
                                                                                 @RequestParam(required = false) String stateCode,
                                                                                 @RequestParam(required = false) String universityId,
                                                                                 @RequestParam(required = false) String fromDate,
                                                                                 @RequestParam(required = false) String toDate,
                                                                                 @RequestParam InstitutionType institutionType,
                                                                                 @RequestParam String type, @RequestParam FormType formType/*,
                                                                                 @RequestParam(required = false) String searchText,*/
            /* @RequestParam int page, @RequestParam int pageSize*/, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        /*if (pageSize <= 0 || page <= 0) {
            ReturnResponse returnResponse = new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "invalid page or page size ");
            return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }*/
        List<InstituteDetailDTO> registeredUserDTO = monitoringService.getInstituteDetailOfProgressMonitoring(surveyYear, stateCode, universityId, fromDate, toDate, institutionType, type, formType/*, page, pageSize, searchText*/);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", registeredUserDTO != null ? registeredUserDTO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/progressMonitoringByBasicCount")
    public ResponseEntity<ReturnResponse> getProgressMonitoringByBasicCount(@RequestParam Integer surveyYear,
                                                                            @RequestParam(required = false) String fromDate,
                                                                            @RequestParam(required = false) String toDate, @RequestParam InstitutionType institutionType, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        ProgressMonitoringDTO registeredUserDTO = monitoringService.getProgressMonitoringByBasicCount(surveyYear, fromDate, toDate, institutionType);
        registeredUserDTO.setDayWise(setProgressMonitoringDayWise(registeredUserDTO));
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", registeredUserDTO != null ? registeredUserDTO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/progress-monitoring-state-wise-count")
    public ResponseEntity<ReturnResponse> progressMonitoringStateWise(@RequestParam Integer surveyYear, @RequestParam(required = false) ListType type,
                                                                      @RequestParam(required = false) String stateCode, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        CommanObjectOperation.validateRequestParam(null, stateCode, null);
        List<?> registeredUserDTO = monitoringService.progressMonitoringStateWise(surveyYear, type, stateCode);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", registeredUserDTO != null ? registeredUserDTO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    public List<FinalProgressMonitoringDayWiseDTO> setProgressMonitoringDayWise(ProgressMonitoringDTO registeredUserDTO) {
        Map<LocalDate, FinalProgressMonitoringDayWiseDTO> dateWiseMap = new TreeMap<>(); // Automatically sorts by date
        if (registeredUserDTO != null) {
            addDataToMap(dateWiseMap, registeredUserDTO.getCollege(), "c");
            addDataToMap(dateWiseMap, registeredUserDTO.getStandalone(), "s");
            addDataToMap(dateWiseMap, registeredUserDTO.getUniversity(), "u");
        }
        return new ArrayList<>(dateWiseMap.values());
    }

    private void addDataToMap(Map<LocalDate, FinalProgressMonitoringDayWiseDTO> map, ProgressMonitoringBasicDTO sourceDTO, String type) {
        if (sourceDTO == null || sourceDTO.getDayWise() == null || sourceDTO.getDayWise().isEmpty())
            return;

        for (FormUploadDateWiseDTO dto : sourceDTO.getDayWise()) {
            if (dto.getDate() == null || dto.getDate().trim().isEmpty())
                continue;
            LocalDate parsedDate = DateUtils.convertStringSlashDateToDBDate(dto.getDate());
            FinalProgressMonitoringDayWiseDTO wiseDTO = map.getOrDefault(parsedDate, new FinalProgressMonitoringDayWiseDTO());
            wiseDTO.setDate(dto.getDate());

            switch (type) {
                case "c":
                    wiseDTO.setCollege(dto.getTotalCount());
                    break;
                case "s":
                    wiseDTO.setStandalone(dto.getTotalCount());
                    break;
                case "u":
                    wiseDTO.setUniversity(dto.getTotalCount());
                    break;
            }
            map.put(parsedDate, wiseDTO);
        }
    }


}
