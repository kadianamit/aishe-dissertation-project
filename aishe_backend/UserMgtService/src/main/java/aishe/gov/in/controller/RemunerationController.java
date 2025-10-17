package aishe.gov.in.controller;

import aishe.gov.in.client.EmailFeignClient;
import aishe.gov.in.dao.ReportGenerationDao;
import aishe.gov.in.enums.ExportType;
import aishe.gov.in.enums.InstituteCategory;
import aishe.gov.in.enums.RefRemunerationStatus;
import aishe.gov.in.exception.EntityException;
import aishe.gov.in.masterseo.RemunerationReport;
import aishe.gov.in.masterseo.UserAndInstitutionDetails;
import aishe.gov.in.masterseo.UserBankAccountEO;
import aishe.gov.in.mastersvo.AfiiliatedWithUniversityCount;
import aishe.gov.in.mastersvo.BankDetailVO;
import aishe.gov.in.mastersvo.RemunerationChangeStatus;
import aishe.gov.in.mastersvo.ReportVo;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.RemunerationService;
import aishe.gov.in.service.UserLogService;
import aishe.gov.in.utility.AisheCodeConvertInID;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.NullValueHandler;
import aishe.gov.in.utility.PdfOperation;
import aishe.gov.in.utility.RemunerationConstant;
import aishe.gov.in.utility.RemunerationSurveyYearComponent;
import aishe.gov.in.utility.ReturnResponse;
import aishe.gov.in.utility.UserAccountKey;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping("/api")
public class RemunerationController {
    @Autowired
    private RemunerationService remunerationService;

    @Autowired
    private NullValueHandler handler;
    @Autowired
    private UserLogService logService;
    @Autowired
    private RemunerationSurveyYearComponent surveyYearComponent;

    @Autowired
    private EmailFeignClient emailFeignClient;

    @Autowired
    private PdfOperation pdfOperation;

    @Autowired
    private AisheCodeConvertInID convert;

    @Autowired
    private ReportGenerationDao generationDao;


    @PostMapping(value = "/bankDetails")
    public ResponseEntity<ReturnResponse> saveOrUpdateBankDetail(@RequestPart(required = false) MultipartFile file,
                                                                 @Valid @ModelAttribute BankDetailVO bankAccountEO,
                                                                 BindingResult bindingResult,
                                                                 HttpServletRequest request,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

        UserBankAccountEO eo = new UserBankAccountEO();
        BeanUtils.copyProperties(bankAccountEO, eo);
        UserAccountKey accountKey = new UserAccountKey();
        BeanUtils.copyProperties(bankAccountEO, accountKey);
        eo.setAccountKey(accountKey);
        if (null != file) {
            byte[] bytes = null;
            try {
                bytes = file.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (pdfOperation.isPdf(bytes)) {
                eo.setBankDocument(bytes);
                eo.setBankDocumentName(file.getOriginalFilename());
            } else {
                throw new EntityException("Please upload only Pdf File.. !!");
            }
        }
        String[] splitted = convert.aisheCodeToArray(bankAccountEO.getAisheCode());
        eo.setInstitutionId(splitted[1]);
        eo.setInstituteType(splitted[0]);
        UserBankAccountEO userBankAccountEO = remunerationService.saveOrUpdateUserBankDetail(eo, request);
        ReturnResponse returnResponse = userBankAccountEO != null ? new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    /*   @PostMapping(value = "/remunerationNorm")
       public ResponseEntity<ReturnResponse> saveOrUpdateRemunerationNorm(@Valid @RequestBody RemunerationNormEO remunerationNormEO, BindingResult bindingResult, HttpServletRequest request) {
           if (bindingResult.hasErrors())
               return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

           RemunerationNormEO userBankAccountEO = remunerationService.saveOrUpdateRemunerationNorm(remunerationNormEO, request);
           ReturnResponse returnResponse = userBankAccountEO != null ? new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
           return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
       }

       @PostMapping(value = "/remunerationNormType1Rule")
       public ResponseEntity<ReturnResponse> saveOrUpdateRemunerationNormType1Rule(@Valid @RequestBody RemunerationNormType1RuleEO remunerationNormEO, BindingResult bindingResult, HttpServletRequest request) {
           if (bindingResult.hasErrors())
               return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

           RemunerationNormType1RuleEO userBankAccountEO = remunerationService.saveOrUpdateRemunerationNormType1Rule(remunerationNormEO, request);
           ReturnResponse returnResponse = userBankAccountEO != null ? new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
           return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
       }

       @PostMapping(value = "/remunerationNormType2Rule")
       public ResponseEntity<ReturnResponse> saveOrUpdateRemunerationNormType2Rule(@Valid @RequestBody RemunerationNormType2RuleEO remunerationNormEO, BindingResult bindingResult, HttpServletRequest request) {
           if (bindingResult.hasErrors())
               return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

           RemunerationNormType2RuleEO userBankAccountEO = remunerationService.saveOrUpdateRemunerationNormType2Rule(remunerationNormEO, request);
           ReturnResponse returnResponse = userBankAccountEO != null ? new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
           return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
       }
   */
    @GetMapping(value = "/bankDetails")
    public ResponseEntity<ReturnResponse> getBankDetails(@RequestParam(required = false) Integer surveyYear,
                                                         @RequestParam(required = false) String userId,
                                                         @RequestParam(required = false) String aisheCode,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<UserBankAccountEO> userBankAccountEO = remunerationService.getUserBankDetails(surveyYear, userId, aisheCode);
        if (CommanObjectOperation.listValidate(userBankAccountEO)) {
            for (UserBankAccountEO eo : userBankAccountEO) {
                eo.setTrack(remunerationService.track(surveyYear, aisheCode));
            }
        }
        if (!CommanObjectOperation.listValidate(userBankAccountEO)) {
            if (surveyYear > RemunerationConstant.BASE_SURVEY_YEAR) {
                userBankAccountEO = remunerationService.getUserBankDetails(surveyYear-1, userId, aisheCode);
                if (CommanObjectOperation.listValidate(userBankAccountEO)) {
                    userBankAccountEO.forEach(bankAccount -> {
                        bankAccount.setStatus(null);
                        bankAccount.setStatusId(null);
                        bankAccount.setAmount(null);
                    });
                }
            }

        }
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    /* @GetMapping(value = "/remunerationNorm")
     public ResponseEntity<ReturnResponse> getRemunerationNorm(@RequestParam(required = false) Integer surveyYear,
                                                               @RequestParam(required = false) FormType formType) {
         List<RemunerationNormEO> userBankAccountEO = remunerationService.getRemunerationNorm(surveyYear, formType != null ? formType.getType() : null);
         ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
         return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
     }

     @GetMapping(value = "/remunerationNormType1Rule")
     public ResponseEntity<ReturnResponse> getRemunerationNormType1Rules(@RequestParam(required = false) Integer surveyYear,*//* @RequestParam(required = false) String aisheCode, @RequestParam(required = false) InstitutionType institutionType,*//*@RequestParam(required = false) FormType formType) {
        List<RemunerationNormType1RuleEO> userBankAccountEO = remunerationService.getRemunerationNormType1RuleEO(surveyYear, *//*aisheCode, institutionType*//*null, null, formType != null ? formType.getType() : null);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/remunerationNormType2Rule")
    public ResponseEntity<ReturnResponse> getRemunerationNormType2Rules(@RequestParam(required = false) Integer surveyYear,*//* @RequestParam(required = false) String aisheCode, @RequestParam(required = false) InstitutionType institutionType,*//* @RequestParam(required = false) FormType formType) {
        List<RemunerationNormType2RuleEO> userBankAccountEO = remunerationService.getRemunerationNormType2RuleEO(surveyYear,*//*aisheCode, institutionType*//*null, null, formType != null ? formType.getType() : null);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/getTransactionAndApprovalLog")
    public ResponseEntity<ReturnResponse> getRemunerationTransactionAndApprovalLog(@RequestParam Integer surveyYear, @RequestParam(required = false) String stateCode, @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, @RequestParam(required = false) InstituteCategory instituteCategory, @RequestParam LogType logType) {
        List<RemunerationTransactionAndApprovalLogVo> userBankAccountEO = remunerationService.getRemunerationTransactionAndApprovalLog(surveyYear, handler.allAssignForNull(stateCode), handler.allAssignForNull(fromDate), handler.allAssignForNull(toDate), handler.allAssignForNull(instituteCategory != null ? instituteCategory.getType() : null), logType);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/freezeRemunerationNorm")
    public ResponseEntity<ReturnResponse> freezeRemunerationNorm(@Valid @RequestBody RemunerationFreezeVO remunerationFreezeVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

        Boolean userBankAccountEO = remunerationService.freezeRemunerationNorm(remunerationFreezeVO);
        ReturnResponse returnResponse = userBankAccountEO == true ? new ReturnResponse(HttpStatus.OK.value(),
                "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/copyRemunerationNorm")
    public ResponseEntity<ReturnResponse> copyRemunerationNorm(@Valid @RequestBody RemunerationCopyVO remunerationVO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);

        Boolean userBankAccountEO = remunerationService.copyRemunerationNorm(remunerationVO);
        ReturnResponse returnResponse = userBankAccountEO == true ? new ReturnResponse(HttpStatus.OK.value(),
                "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
*/
    @GetMapping(value = "/getUserInstitutionDetail")
    public ResponseEntity<ReturnResponse> getUserInstitutionDetailByUserId(@RequestParam String userId,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        UserAndInstitutionDetails userBankAccountEO = remunerationService.getUserDetail(userId);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    /*  @GetMapping(value = "/getRemunerationStatus")
      public ResponseEntity<ReturnResponse> getRemunerationStatus(@RequestParam Integer surveyYear,
                                                                  @RequestParam(required = false) String userId,
                                                                  @RequestParam(required = false) String aisheCode) {
          RemunerationProcessStatusVO userBankAccountEO = remunerationService.getRemunerationStatus(surveyYear, userId, aisheCode);
          ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO);
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @GetMapping(value = "/remuneration-by-category-wise")
      public ResponseEntity<ReturnResponse> getRemunerationStatus(@RequestParam(required = false) String stateCode, @RequestParam Integer surveyYear,
                                                                  @RequestParam(required = false) InstituteCategory instituteCategory, @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, @RequestParam(required = false) String statusId, @RequestParam(required = false) String universityType, @RequestParam(required = false) String institutionId, @RequestParam(required = false) String approverRoleId, @RequestParam(required = false) String collegeType, @RequestParam(required = false) String parentInstitutionId, @RequestParam(required = false) String stateBodyId) {
          instituteCategory = null != instituteCategory ? instituteCategory : InstituteCategory.ALL;
          List<RemunerationStatementApprovalByCategoryEO> userBankAccountEO =
                  remunerationService.getStatementApprovalByCategory(handler.allAssignForNull(stateCode),
                          surveyYear, instituteCategory.getType(), handler.allAssignForNull(fromDate),
                          handler.allAssignForNull(toDate), handler.allAssignForNull(statusId),
                          handler.allAssignForNull(universityType),
                          handler.allAssignForNull(institutionId), handler.allAssignForNull(approverRoleId),
                          handler.allAssignForNull(collegeType), handler.allAssignForNull(parentInstitutionId),
                          handler.allAssignForNull(stateBodyId));
          ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @PostMapping(value = "/saveRemunerationStatementApproval")
      public ResponseEntity<ReturnResponse> saveRemunerationStatementApproval(
              @Valid @RequestBody RemunerationStatementApprovalEO approvalEO, BindingResult bindingResult,
              HttpServletRequest request) throws IOException {
          if (bindingResult.hasErrors())
              return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
          ObjectMapper mapper = new ObjectMapper();
          List<RemunerationStatementApprovalEO> list = mapper.readValue(mapper.writeValueAsString(approvalEO), mapper.getTypeFactory().constructCollectionType(List.class, RemunerationStatementApprovalEO.class));
          List<RemunerationStatementApprovalEO> userBankAccountEO =
                  remunerationService.saveOrUpdateStatementApproval(list, request);
          ReturnResponse returnResponse = userBankAccountEO != null ?
                  new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @PostMapping(value = "/remuneration-statement-generation")
      public ResponseEntity<ReturnResponse> saveRemunerationStatementGeneration(@Valid @RequestBody RemunerationStatementVo approvalEO, BindingResult bindingResult, HttpServletRequest request) {
          if (bindingResult.hasErrors())
              return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
          RemunerationStatementEO userBankAccountEO = remunerationService.statementGeneration(approvalEO, request);
          ReturnResponse returnResponse = userBankAccountEO != null ?
                  new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @GetMapping(value = "/remuneration-statement-dashboard")
      public ResponseEntity<ReturnResponse> getRemunerationStatementGeneration(@RequestParam Integer surveyYear,
                                                                               @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, @RequestParam(required = false) String generatedBy) {
          List<RemunerationStatementDashBoardEO> userBankAccountEO =
                  remunerationService.getRemunerationStatementDashBoard(handler.allAssignForNull(generatedBy),
                          surveyYear, handler.allAssignForNull(fromDate), handler.allAssignForNull(toDate));
          ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO != null ? userBankAccountEO : Collections.EMPTY_LIST);
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @GetMapping(value = "/statement-detail-by-statementId")
      public ResponseEntity<ReturnResponse> getRemunerationStatementDetail(@RequestParam String statementId,
                                                                           @RequestParam(required = false) String stateCode,
                                                                           @RequestParam(required = false) InstituteCategory instituteCategory,
                                                                           @RequestParam(required = false) String fromDate, @RequestParam(required = false) String toDate, @RequestParam(required = false) String transactionStatusId) {
          instituteCategory = null != instituteCategory ? instituteCategory : InstituteCategory.ALL;
          RemunerationStatementDetailVO userBankAccountEO = remunerationService.getStatementDetail(statementId,
                  handler.allAssignForNull(stateCode), instituteCategory.getType(),
                  handler.allAssignForNull(fromDate), handler.allAssignForNull(toDate),
                  handler.allAssignForNull(transactionStatusId));
          ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO);
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }


      @PostMapping(value = "/remuneration-statements-transaction-approval")
      public ResponseEntity<ReturnResponse> saveRemunerationStatementTransactionApproval(@RequestBody @Valid List<RemunerationStatementDetailEO> detailEOS, BindingResult bindingResult, HttpServletRequest request) throws IOException {
          if (bindingResult.hasErrors())
              return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
          ObjectMapper mapper = new ObjectMapper();
          List<RemunerationStatementDetailEO> list = mapper.readValue(mapper.writeValueAsString(detailEOS), mapper.getTypeFactory().constructCollectionType(List.class, RemunerationStatementDetailEO.class));
          List<RemunerationStatementDetailEO> userBankAccountEO = remunerationService.saveRemunerationStatementTransactionApproval(list, request);
          ReturnResponse returnResponse = userBankAccountEO != null ?
                  new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
          return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
      }

      @GetMapping(value = "/generate-remuneration-report")
      public ReportVo generateReport92(@RequestParam(required = false) String generatorUserId,
                                       @RequestParam(required = false) Integer roleId, @RequestParam(required =
              false) String statementId, @RequestParam ExportType exportType, HttpServletRequest request) throws JRException {
          if (RemunerationConstant.MHRD_ROLE_ID.equals(roleId)) {
              Map<String, Object> parameters = new HashMap<>(0);
              parameters.put(RemunerationConstant.reportTitle, RemunerationConstant.REPORT_92_TITLE);
              RemunerationStatementEO remunerationStatement = remunerationService.getRemunerationStatement(statementId);
              if (null != remunerationStatement) {
                  parameters.put(RemunerationConstant.statementId, statementId);
                  parameters.put(RemunerationConstant.surveyYear, remunerationStatement.getSurveyYear());
                  parameters.put(RemunerationConstant.surveyYearOne, (remunerationStatement.getSurveyYear() + 1));
                  parameters.put(RemunerationConstant.generatorUserId, remunerationStatement.getApproverUserId());
                  parameters.put(RemunerationConstant.statementTimestamp, remunerationStatement.getTimestamp());
                  List<RemunerationDashboard> remunerationDashboardList = remunerationService.findStatementListByIdAndStatus(statementId, RemunerationConstant.TRANSACTION_ALL_STATUS_ID, Integer.MAX_VALUE, RemunerationConstant.zero);
                  JRDataSource dataSource = new JRBeanCollectionDataSource(remunerationDashboardList);
                  InputStream jrxmlInputStream = this.getClass().getResourceAsStream(RemunerationConstant.REPORTS_PATH + RemunerationConstant.REPORT92);
                  JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlInputStream);
                  ReportVo reportVo = new ReportVo(exportType.type, handler.exportReport(dataSource, exportType.type, jasperReport, parameters));
                  if (null != generatorUserId) {
                      logService.save(new UserActionLogEO(logService.getMaxId() + 1, generatorUserId, null, null, RemunerationConstant.REPORT_ACTION_ID, remunerationStatement.getSurveyYear(), new Timestamp(new Date().getTime()), IpAddressProvider.getClientIpAddr(request), RemunerationConstant.REPORT_92_TITLE));
                  }
                  return reportVo;
              } else {
                  log.error("Remuneration statement >>>" + statementId + "<<< does not exists.");
                  throw new EntityException("Remuneration statement " + statementId + " does not exists.");
              }
          } else {
              log.error("Unauthorized access to Remuneration Report 92 by user id >>>" + generatorUserId
                      + "<<< having role >>>" + roleId + "<<<");
              throw new UnAuthorizationException("Unauthorized access to Remuneration Report 92 by user id " + generatorUserId);
          }
      }

      @PostMapping(value = "/upload-remuneration-file")
      public ResponseEntity<ReturnResponse> updateTransactionStatusByMultipartFile(@RequestPart(value = "file") MultipartFile file,
                                                                                   @RequestParam(required = false) String generatorUserId,
                                                                                   @RequestParam(required = false) Integer roleId, HttpServletRequest request) throws IOException {
          if (RemunerationConstant.MHRD_ROLE_ID.equals(roleId)) {
              List<RemunerationStatementDetailEO> userBankAccountEO = remunerationService.uploadRemunerationMultipart(file, generatorUserId, request);
              ReturnResponse returnResponse = userBankAccountEO != null ? new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
              return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
          } else {
              log.error("Unauthorized access to Remuneration Report 92 by user id >>>" + generatorUserId
                      + "<<< having role >>>" + roleId + "<<<");
              throw new UnAuthorizationException("Unauthorized access to Remuneration Report 92 by user id " + generatorUserId);
          }
      }
  */
    @GetMapping(value = "/get-Remuneration-survey-year-by-aishe-code")
    public ResponseEntity<ReturnResponse> getRemunerationAccordingUserId(@RequestParam(defaultValue = "ALL") String aisheCode,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<SelectOptionVO> optionVOS = aisheCode.equals("ALL") ?
                surveyYearComponent.generateSurveyYearDropDownListForApprovalDashBoard() :
                surveyYearComponent.generateSurveyYearDropDownListForAccount(aisheCode);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", optionVOS);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/check-eligibility-by-survey-year")
    public ResponseEntity<ReturnResponse> checkEligibility(@RequestParam String aisheCode, @RequestParam Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<SelectOptionVO> optionVOS = remunerationService.checkEligibility(aisheCode, surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", optionVOS);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/get-affiliated-college-count")
    public ResponseEntity<ReturnResponse> getAffiliatedCollegeCount(@RequestParam String universityId, @RequestParam Integer surveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        AfiiliatedWithUniversityCount afiiliatedWithUniversityCount = remunerationService.getAffiliatedCollegeCount(universityId, surveyYear);
        ReturnResponse returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", afiiliatedWithUniversityCount);
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/remuneration-report")
    public ReportVo remunerationReport(@RequestParam Integer surveyYear, @RequestParam(required = false) String aisheCode,
                                       @RequestParam InstituteCategory institutionType,
                                       @RequestParam(required = false) String stateCode,
                                       @RequestParam(required = false) String districtCode,
                                       @RequestParam(required = false) String fromDate,
                                       @RequestParam(required = false) String toDate,
                                       @RequestParam(required = false) String instituteSubType,
                                       @RequestParam(required = false) String universityId,
                                       @RequestParam(required = false) RefRemunerationStatus status,
                                       @RequestParam ExportType exportType,@WithUser UserInfo userInfo)throws ParseException {
        CommanObjectOperation.usernameValidate(userInfo);
        Date fDate;
        Date tDate;
        String reportName = "remuneration-report";
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        if (!CommanObjectOperation.stringValidate(fromDate)) {
            fDate = formatter.parse("01/01/2008");
        } else {
            fDate = formatter.parse(fromDate);
        }
        if (!CommanObjectOperation.stringValidate(toDate)) {
            tDate = formatter.parse("31/12/2070");
        } else {
            tDate = formatter.parse(toDate);
        }
        Integer statusId = null != status ? status.getId() : status.ALL.getId();
        stateCode = handler.allAssignForNull(stateCode);
        aisheCode = handler.allAssignForNull(aisheCode);
        districtCode = handler.allAssignForNull(districtCode);
        instituteSubType = handler.allAssignForNull(instituteSubType);
        universityId = handler.allAssignForNull(universityId);

        if (!(exportType.getType().equalsIgnoreCase(ExportType.JSON.getType()))) {
            Map<String, Object> parameters = new HashMap<>(0);
            parameters.put(RemunerationConstant.reportTitle, reportName);
            parameters.put("surveyYear", surveyYear);
            parameters.put("stateCode", stateCode);
            parameters.put("districtCode", districtCode);
            parameters.put("categoryType", instituteSubType);
            parameters.put("universityId", universityId);
            parameters.put("toDate", tDate);
            parameters.put("fromDate", fDate);
            parameters.put("institutionType", institutionType.getType());
            parameters.put("statusId", statusId);
            parameters.put("aisheCode", aisheCode);

            try (InputStream stream =
                         this.getClass().getResourceAsStream(RemunerationConstant.REPORTS_PATH + "remunration_new.jrxml");
                 Connection connection = generationDao.findConnection()) {
                final JasperReport report = JasperCompileManager.compileReport(stream);
                final JasperPrint print = JasperFillManager.fillReport(report, parameters, connection);
                return new ReportVo(reportName, handler.fileExportByType(exportType, print));
            } catch (Exception e) {
                log.error("error occurred while loading jasper due to {} this error ", e.getMessage());
            }

        } else {
            List<RemunerationReport> remunerationReport = remunerationService.remunerationReport(surveyYear,
                    institutionType, aisheCode, stateCode, districtCode, instituteSubType, universityId, fDate, tDate, statusId);
            return new ReportVo(reportName, null, remunerationReport);
        }
        return null;
    }


    @PostMapping(value = "/change-status")
    public ResponseEntity<ReturnResponse> remunerationChangeStatus(@RequestBody @Valid List<RemunerationChangeStatus> detailEOS, BindingResult bindingResult, HttpServletRequest request, @WithUser UserInfo userInfo) throws IOException {
       CommanObjectOperation.usernameValidate(userInfo);
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        ObjectMapper mapper = new ObjectMapper();
        List<RemunerationChangeStatus> list = mapper.readValue(mapper.writeValueAsString(detailEOS), mapper.getTypeFactory().constructCollectionType(List.class, RemunerationChangeStatus.class));
        List<RemunerationChangeStatus> userBankAccountEO = remunerationService.remunerationChangeStatus(list, request, userInfo);
        ReturnResponse returnResponse = userBankAccountEO != null ?
                new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ref_remuneration-status")
    public ResponseEntity<ReturnResponse> refRemunerationStatus(@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        List<RefRemunerationStatus> userBankAccountEO = remunerationService.refRemunerationStatus();
        ReturnResponse returnResponse = userBankAccountEO != null ?
                new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


  /*  @PostMapping(value = "/lock-bank-detail")
    public ResponseEntity<ReturnResponse> lockBankDetails(@RequestBody @Valid RemunerationChangeStatus detailEOS,
                                            BindingResult bindingResult, HttpServletRequest request, @WithUser UserInfo userInfo) throws IOException {
        if (bindingResult.hasErrors())
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again.", bindingResult.getFieldErrors()), HttpStatus.BAD_REQUEST);
        RemunerationChangeStatus userBankAccountEO = remunerationService.lockBankDetails(detailEOS, request, userInfo);
        ReturnResponse returnResponse = userBankAccountEO != null ?
                new ReturnResponse(HttpStatus.OK.value(), "success ", userBankAccountEO) : new ReturnResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Request Cannot Be Processed. Please Try Again.");
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
*/
}

