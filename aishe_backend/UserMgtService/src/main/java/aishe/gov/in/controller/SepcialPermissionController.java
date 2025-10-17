package aishe.gov.in.controller;

import aishe.gov.in.enums.ActionConstant;
import aishe.gov.in.enums.Constant;
import aishe.gov.in.enums.OrderBy;
import aishe.gov.in.enums.StandaloneAction;
import aishe.gov.in.enums.SurveyYearConstant;
import aishe.gov.in.enums.SurveyYearDropDown;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.mastersvo.UnlockVO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.CollegeAffiliationDeaffiliationService;
import aishe.gov.in.service.JasperGeneratorService;
import aishe.gov.in.service.LockService;
import aishe.gov.in.service.SpecialPermissionService;
import aishe.gov.in.utility.AisheCodeConvertInID;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.ReturnResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class SepcialPermissionController {

    private static final Logger logger = LoggerFactory.getLogger(SepcialPermissionController.class);

    @Autowired
    private SpecialPermissionService specialPermissionService;

    @Autowired
    private LockService lockService;

    @Autowired
    private JasperGeneratorService jasperGeneratorService;

    @Autowired
    private CollegeAffiliationDeaffiliationService affiliationDeaffiliationService;

    @Autowired
    private AisheCodeConvertInID aisheCodeToId;

    @PostMapping(value = "/assignspecialpermission")
    public ResponseEntity<ReturnResponse> assignSpecialPermissionToInstitute(@RequestParam(required = true) String instituteType, @RequestParam(required = true) String aisheCode, @RequestParam(required = false) String username,
                                                                             @RequestParam(required = true) SurveyYearDropDown surveyYear, @RequestParam(required = true, defaultValue = "false") Boolean specialPermission, @RequestParam(required = false) String userKey
            , @RequestParam(required = false) String remarks, HttpServletRequest request, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        logger.info("assignspecialpermission api invoked");
        //if(userKey.equals("MOEUSERONLY") && (username.equals("aishe.nic") || username.equals("riteshpatel"))) {
        if (/*
         * (userKey.equals("NIC@Aishe123") && (username.equals("aishe.nic") ||
         * username.equals("riteshpatel"))) &&
         */
            //(
                surveyYear.getStatusType().equals(SurveyYearDropDown.SURVEY_YEAR_2023.getStatusType()))
        //)
        {
            boolean isUniversityHostelDeleted = specialPermissionService.assignSpecialPermissionToInstitute(instituteType, aisheCode, username, surveyYear.getStatusType(), specialPermission.booleanValue(), remarks, request);
            if (isUniversityHostelDeleted) {
                return new ResponseEntity<>(
                        new ReturnResponse(HttpStatus.OK.value(), "Special Permission To Institute has been  Successfully Done!!"),
                        HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("webdcf-log-reports")
    public void webdcfLogReportsExcel(@RequestParam(required = false) SurveyYearConstant surveyYearConstant, @RequestParam ActionConstant action, @RequestParam(required = false) OrderBy orderBy,
                                      @RequestParam(required = false) Integer surveyYear, @RequestParam(required = false) String stateCode, @RequestParam(required = false, defaultValue = "ALL") String instituteType,
			HttpServletResponse response/* , @WithUser UserInfo userInfo */) throws JRException, IOException, SQLException {
       // CommanObjectOperation.usernameValidate(userInfo);
        logger.info("university teacher  jasper report generating pdf file and fill parameter into the template");
        InputStream stream;
        URL path1;
        stream = this.getClass().getResourceAsStream(Constant.SEPCIAL_PERMISSION + "Special_Permission.jrxml");
        path1 = this.getClass().getResource(Constant.SEPCIAL_PERMISSION);
        if (action.getStatusType() == 37) {
            stream = this.getClass().getResourceAsStream(Constant.WEBDCF_UNLOCK + "Unlock_Institution_List.jrxml");
            path1 = this.getClass().getResource(Constant.WEBDCF_UNLOCK);
        }
        if (action.getStatusType() == 1) {
            switch (instituteType.toUpperCase()) {
                case "C": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "CollegeSubmitted.jrxml");
                    break;
                }
                case "U": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "UniversitySubmitted.jrxml");

                    break;
                }
                case "S": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "StandaloneSubmitted.jrxml");
                    break;
                }
            }

        }
        if (action.getStatusType() == -1) {
            switch (instituteType.toUpperCase()) {
                case "C": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "CollegePending.jrxml");
                    break;
                }
                case "U": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "UniversityPending.jrxml");
                    break;
                }
                case "S": {
                    stream = this.getClass().getResourceAsStream(Constant.FINAL_SUBMITTED_PENDING + "StandalonePending.jrxml");
                    break;
                }
            }
        }
        final JasperReport report = JasperCompileManager.compileReport(stream);
        final Map<String, Object> parameters = new HashMap<>();


        if (surveyYear != null && action.getStatusType() == 37) {
            parameters.put("survey_year", surveyYear);
        } else if (surveyYear != null) {
            parameters.put("survey_year", surveyYear.toString());
            parameters.put("survey_year_2", surveyYear);
        } else if (action.getStatusType() == 37) {
            parameters.put("survey_year", surveyYearConstant.getStatusType());
        }
        if (stateCode != null) {
            parameters.put("state_code", stateCode);
        } else {
            parameters.put("state_code", "ALL");
        }
        if (instituteType != null) {
            parameters.put("institution_type", instituteType);
        }
        if (action != null && (action.getStatusType() == 1 || action.getStatusType() == -1))
         parameters.put("institution_type", "ALL");
        parameters.put("real_path", path1.toString());
        parameters.put("action_id", action.getStatusType().toString());
        parameters.put("action_name", action.name());
        Connection connection = jasperGeneratorService.findConnection();
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
        response.setContentType(Constant.EXCEL_XLS_CONTENT_TYPE);
        //fileName = reportFileName + " " + Util.getTimeStamp() + ".xlsx";
        response.setHeader(Constant.CONTENT_DISPOSITION, String.format("attachment;filename=WEB_DCF_" + action.name() + ".xlsx"));
        OutputStream out = response.getOutputStream();

        JRXlsxExporter jrXLSXExporter = new JRXlsxExporter();

        jrXLSXExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        jrXLSXExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);

        jrXLSXExporter.exportReport();


        // JasperExportManager.exportReportToPdfStream(print, out);
        connection.close();
        out.close();
        logger.info("successfully generated university Teacher dcf pdf  file and successfully filled parameter into the template");
    }

    @PostMapping(value = "/api/webdcf-unlock")
    public ResponseEntity<ReturnResponse> unlockWebdcf(@RequestBody UnlockVO unlockVO, HttpServletRequest request, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        boolean isUnlock = false;
        if (/*
         * unlockVO.getUserKey().equals("NIC@Aishe123") &&
         * (unlockVO.getUserName().equals("aishe.nic") ||
         * unlockVO.getUserName().equals("riteshpatel")) &&
         */ unlockVO.getSurveyYear().equals(SurveyYearDropDown.SURVEY_YEAR_2024.getStatusType())) {
            isUnlock = lockService.unlockWebdcf(unlockVO, request);
        }
        if (isUnlock) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "DCF has been Successfully UnLocked!!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Please Try Again With Correct Input Aishe_Code(Like 12345), Inst_type(Like C,S,U),Survey_Year(Only 2023), User_name or User_Key."), HttpStatus.BAD_REQUEST);

        }
    }

    // @GetMapping("webdcf-unlock-log-excel")
    public void webdcfUnlockReportExcel(@RequestParam SurveyYearConstant surveyYear, HttpServletResponse response) throws JRException, IOException, SQLException {
        logger.info("university teacher  jasper report generating pdf file and fill parameter into the template");
        final InputStream stream;
        URL path1;
        if (surveyYear.getStatusType() >= 2020) {
            stream = this.getClass().getResourceAsStream(Constant.WEBDCF_UNLOCK + "Unlock_Institution_List.jrxml");
            path1 = this.getClass().getResource(Constant.WEBDCF_UNLOCK);

        } else {
            stream = this.getClass().getResourceAsStream(Constant.REGIONAL_CENTER_ONE + ".jrxml");
            path1 = this.getClass().getResource(Constant.REGIONAL_CENTER_ONE);
        }
        final JasperReport report = JasperCompileManager.compileReport(stream);
        final Map<String, Object> parameters = new HashMap<>();
        parameters.put("survey_year", surveyYear.getStatusType());
        parameters.put("real_path", path1.toString());
        Connection connection = jasperGeneratorService.findConnection();
        final JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
        response.setContentType(Constant.EXCEL_XLS_CONTENT_TYPE);
        //fileName = reportFileName + " " + Util.getTimeStamp() + ".xlsx";
        response.setHeader(Constant.CONTENT_DISPOSITION, String.format("attachment;filename=WEBDCF_UNLOCK.xlsx"));
        OutputStream out = response.getOutputStream();

        JRXlsxExporter jrXLSXExporter = new JRXlsxExporter();

        jrXLSXExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        jrXLSXExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_IMAGE_BORDER_FIX_ENABLED, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
        jrXLSXExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, true);

        jrXLSXExporter.exportReport();


        // JasperExportManager.exportReportToPdfStream(print, out);
        connection.close();
        out.close();
        logger.info("successfully generated university Teacher dcf pdf  file and successfully filled parameter into the template");
    }

    @PostMapping(value = "/activateinactivatestandalone")
    public ResponseEntity<ReturnResponse> activateInactivateStandalone(@RequestParam(required = true) String aisheCode
            , @RequestParam(required = false) String username, @RequestParam(required = true) StandaloneAction standaloneAction,
                                                                       @RequestParam(required = false) String userKey, @RequestParam(required = false) String remarks, @RequestParam(required = false) Integer surveyYear, HttpServletRequest request, @WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        if (!(username.equals("shivam.pandey") && userKey.equals("MOEDIVISION@AISHE"))) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                    "You Are Not Authorized."), HttpStatus.UNAUTHORIZED);
        }
        if (surveyYear < 2022) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                    "Survey Year Can Be Only 2022 or 2023."), HttpStatus.UNAUTHORIZED);
        }
        Form_UploadBean formupload = affiliationDeaffiliationService.getFormUploadForStandalone(aisheCodeToId.aisheCodeToId(aisheCode), surveyYear);
        if (formupload != null) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.UNAUTHORIZED.value(),
                            "Cannot be Done. Institution has filled Webdcf for this Survey Year"),
                    HttpStatus.UNAUTHORIZED);
        }
        boolean isUniversityHostelDeleted = specialPermissionService.activateInactivateStandalone(aisheCode, standaloneAction.getStatusType(), remarks, surveyYear, username, request);
        if (isUniversityHostelDeleted) {
            return new ResponseEntity<>(
                    new ReturnResponse(HttpStatus.OK.value(), "Standalone Action has been  Successfully Done!!"),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }
}