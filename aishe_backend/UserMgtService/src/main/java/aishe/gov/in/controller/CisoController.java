package aishe.gov.in.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import aishe.gov.in.masterseo.CisoSubDomainDetailEO;
import aishe.gov.in.masterseo.GovtDomainContactDetailEO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import aishe.gov.in.enums.InstitutionCategory;
import aishe.gov.in.masterseo.CisoAIOSystemDetailsEO;
import aishe.gov.in.masterseo.CisoDocumentEo;
import aishe.gov.in.masterseo.CisoHeiEO;
import aishe.gov.in.masterseo.CisoInfoApplicationDetails;
import aishe.gov.in.masterseo.CisoInfoEO;
import aishe.gov.in.masterseo.CisoLetterEo;
import aishe.gov.in.masterseo.CisoServerDetailsEO;
import aishe.gov.in.masterseo.CisoSwitchNetworkDetailsEO;
import aishe.gov.in.masterseo.CisoSystemDetailsEO;
import aishe.gov.in.masterseo.RefCisoDocumentType;
import aishe.gov.in.mastersvo.CisoInfoDTO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.CisoService;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.NullValueHandler;
import aishe.gov.in.utility.PdfOperation;
import aishe.gov.in.utility.ReturnResponse;

@RestController
@RequestMapping("/api")
public class CisoController {
    @Autowired
    private CisoService cisoService;
    @Autowired
    private PdfOperation pdfOperation;
    @Autowired
    private NullValueHandler handler;
 
    private static final String status = "Not Found.";
    
    @GetMapping(value = "/ciso")
    public ResponseEntity<ReturnResponse> getList(@RequestParam(required = false) String agencyCode, @RequestParam(required = false) String post) {

        List<CisoInfoDTO> masterEO = cisoService.getAll(handler.nullValueHandler(agencyCode), handler.nullValueHandler(post));
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @PostMapping(value = "/ciso-info")
    public ResponseEntity<ReturnResponse> saveOrUpdateCiso(@RequestParam(value = "file", required = false) MultipartFile file, @RequestParam String agencyCode, @RequestParam String post, @RequestParam(required = false) String agencyName
            , @RequestParam(required = false) String designation, @RequestParam(required = false) String mobile, @RequestParam(required = false) String mobileAlternate, @RequestParam(required = false) String emailId
            , @RequestParam(required = false) String emailIdAlternate, @RequestParam(required = false) String officeAddress, @RequestParam(required = false) Integer priority
            , @RequestParam(required = false) String remarks, @RequestParam(required = false) String issuedDate, @RequestParam(required = false) String letterTitle, @RequestParam String name) throws IOException {

        CisoInfoEO cisoInfo = CisoInfoEO
                .builder().agencyCode(agencyCode.toUpperCase()).post(post.toUpperCase()).agencyName(agencyName).designation(designation).mobile(mobile).mobileAlternate(mobileAlternate).emailId(emailId)
                .emailIdAlternate(emailIdAlternate).officeAddress(officeAddress)                .priority(priority).remarks(remarks).issuedDate(DateUtils.convertStringSlashDateToDBDate(issuedDate)).letterTitle(letterTitle).name(name)
                .build();
        CisoInfoEO masterEO = null;
        if (null != file) {
            if (!file.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            byte[] byteArr = file.getBytes();
            boolean isPd = pdfOperation.isPdf(byteArr);
            if (!isPd) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            masterEO = cisoService.saveCiso(cisoInfo, byteArr, file);
        } else {
            masterEO = cisoService.saveCiso(cisoInfo, null, file);
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/ciso-letter")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoLetter(@RequestParam("file") MultipartFile file, @RequestParam String agencyCode, @RequestParam String post, @RequestParam(required = false) Integer id,
                                                                                                                                  @RequestParam(required = false) String letterTitle, @RequestParam(required = false) String remarks, @RequestParam(required = false) String issuedDated) throws IOException {
        CisoLetterEo cisoInfo = CisoLetterEo.builder().agencyCode(agencyCode.toUpperCase()).post(post.toUpperCase()).id(id).letterTitle(letterTitle).remarks(remarks).issuedDated(issuedDated).build();
        if (!file.getContentType().equals("application/pdf")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        byte[] byteArr = file.getBytes();
        boolean isPd = pdfOperation.isPdf(byteArr);
        if (!isPd) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        CisoLetterEo masterEO = cisoService.saveCisoLetter(cisoInfo, byteArr, file);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ciso-letters")
    public ResponseEntity<ReturnResponse> cisoLettersByCodeAndPost(@RequestParam String agencyCode, @RequestParam String post) {
        List<CisoLetterEo> masterEO = cisoService.getCisoLetter(agencyCode, post);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ciso-letter")
    public ResponseEntity<ReturnResponse> getLetterByLetterId(@RequestParam Integer letterId) {
        CisoLetterEo letter = cisoService.getCisoLetter(letterId);
        ReturnResponse returnResponse = ((letter != null) ? new ReturnResponse(HttpStatus.OK.value(), "Success" +
                letter) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @DeleteMapping(value = "/delete-ciso-info")
    public ResponseEntity<ReturnResponse> cisoInfoByCodeAndPost(@RequestParam String agencyCode, @RequestParam String post) {
        Boolean letter = cisoService.deleteCisoInfo(agencyCode, post);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO Info Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @DeleteMapping(value = "/delete-ciso-letters")
    public ResponseEntity<ReturnResponse> cisoLettersByCodeAndPost(@RequestParam Integer letterId) {
        Boolean letter = cisoService.deleteCisoLetter(letterId);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO letter Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/institution")
    public ResponseEntity<ReturnResponse> institutionDetail(@RequestParam String aisheCode) {
        if (aisheCode != null && !checkAisheCode(aisheCode)) {
            String error = aisheCode + " Aishe code is invalid ";
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), error),
                    HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
        InstituteDetailDTO letter = cisoService.institutionDetail(aisheCode);
        ReturnResponse returnResponse = ((letter != null) ? new ReturnResponse(HttpStatus.OK.value(), "Success", letter) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    private boolean checkAisheCode(String aisheCode) {
        boolean status = false;
        if (aisheCode != null && !aisheCode.contains("-")) {
            return status;
        } else if (aisheCode.contains("-")) {
            String aishe = aisheCode.substring(0, aisheCode.lastIndexOf("-"));
            if (!aishe.equalsIgnoreCase("C") && !aishe.equalsIgnoreCase("S") && !aishe.equalsIgnoreCase("U"))
                return status;
            else
                return true;
        }
        return status;
    }


/*    @PostMapping(value = "/ciso-info-data")
    public ResponseEntity<ReturnResponse> saveOrUpdateCiso(@ModelAttribute CisoInfoEO cisoInfoD, @RequestPart(required = false) MultipartFile file ) throws IOException {

        CisoInfoEO cisoInfo = CisoInfoEO
                .builder().agencyCode(cisoInfoD.getAgencyCode().toUpperCase()).post(cisoInfoD.getPost()).agencyName(cisoInfoD.getAgencyName())
                .designation(cisoInfoD.getDesignation()).mobile(cisoInfoD.getMobile()).mobileAlternate(cisoInfoD.getMobileAlternate())
                .emailId(cisoInfoD.getEmailId()).emailIdAlternate(cisoInfoD.getEmailIdAlternate()).officeAddress(cisoInfoD.getOfficeAddress())
                .priority(cisoInfoD.getPriority()).remarks(cisoInfoD.getRemarks()).issuedDate(cisoInfoD.getIssuedDate())
                .letterTitle(cisoInfoD.getLetterTitle()).name(cisoInfoD.getName()).build();
        CisoInfoEO masterEO = null;
        if (null != file) {
            if (!file.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            byte[] byteArr = file.getBytes();
            boolean isPd = pdfOperation.isPdf(byteArr);
            if (!isPd) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            masterEO = cisoService.saveCiso(cisoInfo, byteArr, file);
        } else {
            masterEO = cisoService.saveCiso(cisoInfo, null, file);
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }*/

    @PostMapping(value = "/ciso-info-data")
    public ResponseEntity<ReturnResponse> saveOrUpdateCiso(@RequestBody CisoInfoEO cisoInfoD) throws IOException {

        CisoInfoEO cisoInfo = CisoInfoEO
                .builder().agencyCode(cisoInfoD.getAgencyCode().toUpperCase()).post(cisoInfoD.getPost()).agencyName(cisoInfoD.getAgencyName())
                .designation(cisoInfoD.getDesignation()).mobile(cisoInfoD.getMobile()).mobileAlternate(cisoInfoD.getMobileAlternate())
                .emailId(cisoInfoD.getEmailId()).emailIdAlternate(cisoInfoD.getEmailIdAlternate()).officeAddress(cisoInfoD.getOfficeAddress())
                .priority(cisoInfoD.getPriority()).remarks(cisoInfoD.getRemarks()).issuedDate(cisoInfoD.getIssuedDate())
                .letterTitle(cisoInfoD.getLetterTitle()).name(cisoInfoD.getName()).build();
        CisoInfoEO masterEO = null;
        MultipartFile file = null;
        if (null != file) {
            if (!file.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            byte[] byteArr = file.getBytes();
            boolean isPd = pdfOperation.isPdf(byteArr);
            if (!isPd) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            masterEO = cisoService.saveCiso(cisoInfo, byteArr, file);
        } else {
            masterEO = cisoService.saveCiso(cisoInfo, null, file);
        }
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @PostMapping(value = "/ciso-letters", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoLetters(//@RequestPart String CisoLetter,
                                                                  @ModelAttribute CisoLetterEo letter,
                                                                  @RequestPart("file") MultipartFile file) throws IOException {
        //    CisoLetterEo letter = new ObjectMapper().readValue(CisoLetter, CisoLetterEo.class);
        if (!file.getContentType().equals("application/pdf")) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                    "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        byte[] byteArr = file.getBytes();
        boolean isPd = pdfOperation.isPdf(byteArr);
        if (!isPd) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
        }
        CisoLetterEo masterEO = cisoService.saveCisoLetter(letter, byteArr, file);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/ciso-by-category")
    public ResponseEntity<ReturnResponse> getListByCategory(@RequestParam InstitutionCategory category) {
        List<CisoInfoDTO> masterEO = cisoService.getAll(category);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ciso-application-details")
    public ResponseEntity<ReturnResponse> getApplication(@RequestParam(required = false) Integer id) {
        List<CisoInfoApplicationDetails> masterEO = cisoService.getAllApplication(id);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/ciso-save-application-details")
	public ResponseEntity<ReturnResponse> saveOrUpdateApplication(/*
									 * @RequestBody CisoInfoApplicationDetails
									 * applicationDetails
									 */
			@RequestPart(value = "auditCertificate",required = false) MultipartFile auditCertificate,
			@RequestPart(value = "finalAuditReport",required = false) MultipartFile finalAuditReport,
			@RequestPart(value = "csdReport",required = false) MultipartFile csdReport,@RequestParam Integer id, @RequestParam String applicationName, @RequestParam(required = false) String departmentName,
        @RequestParam(required = false) String websiteUrl, @RequestParam(required = false) String remarks, @RequestParam(required = false) Boolean isAudited
			/* , @RequestParam(required = false) Integer documentTypeId */, @RequestParam(required = false) String validUpto
        ,@RequestParam(required=false) Integer auditCertificateId,@RequestParam(required=false) Integer finalAuditReportId,@RequestParam(required=false) Integer csdReportId
	 , @WithUser UserInfo userInfo ) throws IOException {
    	//UserInfo userInfo = new UserInfo("anwar.khan", null);
    	CisoInfoApplicationDetails applicationDetails = new CisoInfoApplicationDetails();
    	applicationDetails.setId(id);
    	applicationDetails.setApplicationName(applicationName);
    	applicationDetails.setDepartmentName(departmentName);
    	applicationDetails.setWebsiteUrl(websiteUrl);
    	applicationDetails.setRemarks(remarks);
    	applicationDetails.setIsAudited(isAudited);
    	if(validUpto!=null) {
    		applicationDetails.setValidUpto(DateUtils.convertStringSlashDateToDBDate(validUpto));
    	}
    	if(auditCertificateId==0) {
    		auditCertificateId=null;
    	}
    	if(finalAuditReportId==0) {
    		finalAuditReportId=null;
    	}
    	if(csdReportId==0) {
    		csdReportId=null;
    	}
    	//audit cer save update
    	if(auditCertificate!=null) {
    	 if (!auditCertificate.getContentType().equals("application/pdf")) {
             return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                     "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
         }
         byte[] byteArr = auditCertificate.getBytes();
         CisoDocumentEo cisoServerDetailsEO = new CisoDocumentEo();
         cisoServerDetailsEO.setFileName(auditCertificate.getOriginalFilename());
	     cisoServerDetailsEO.setDocumentFile(byteArr);
	     cisoServerDetailsEO.setDocumentType(auditCertificate.getContentType());
	     RefCisoDocumentType refCisoDocType = new RefCisoDocumentType();
	     refCisoDocType.setId(1);
	     refCisoDocType.setType("Audit Certificate");
	     cisoServerDetailsEO.setRefDocumentType(refCisoDocType);
	     cisoServerDetailsEO.setId(auditCertificateId);
	     cisoServerDetailsEO.setUploadedBy(userInfo.getUsername());
	     cisoServerDetailsEO.setDateTime(DateUtils.obtainCurrentTimeStamp());
	     CisoDocumentEo auditCertificateSave = cisoService.saveOrUpdateCisoDocument(cisoServerDetailsEO);
	     applicationDetails.setAuditCertificateId(auditCertificateSave.getId());
	     applicationDetails.setAuditCertificateName(auditCertificate.getOriginalFilename());
    	}
    	//final audit save update
    	if(finalAuditReport!=null) {
       	 if (!finalAuditReport.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            byte[] byteArr = finalAuditReport.getBytes();
            CisoDocumentEo cisoServerDetailsEO = new CisoDocumentEo();
            cisoServerDetailsEO.setFileName(finalAuditReport.getOriginalFilename());
   	     cisoServerDetailsEO.setDocumentFile(byteArr);
   	     cisoServerDetailsEO.setDocumentType(finalAuditReport.getContentType());
   	     RefCisoDocumentType refCisoDocType = new RefCisoDocumentType();
   	     refCisoDocType.setId(2);
   	  refCisoDocType.setType("Final Audit Report");
   	     cisoServerDetailsEO.setRefDocumentType(refCisoDocType);
   	     cisoServerDetailsEO.setId(finalAuditReportId);
   	     cisoServerDetailsEO.setUploadedBy(userInfo.getUsername());
   	     cisoServerDetailsEO.setDateTime(DateUtils.obtainCurrentTimeStamp());
   	     CisoDocumentEo auditCertificateSave = cisoService.saveOrUpdateCisoDocument(cisoServerDetailsEO);
    	 applicationDetails.setFinalAuditReportName(finalAuditReport.getOriginalFilename());
     	 applicationDetails.setFinalAuditReportId(auditCertificateSave.getId());
       	}
    	//csd report save update
    	if(csdReport!=null) {
       	 if (!csdReport.getContentType().equals("application/pdf")) {
                return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(),
                        "Incorrect file type, PDF required.", null), HttpStatus.BAD_REQUEST);
            }
            byte[] byteArr = csdReport.getBytes();
            CisoDocumentEo cisoServerDetailsEO = new CisoDocumentEo();
            cisoServerDetailsEO.setFileName(csdReport.getOriginalFilename());
   	     cisoServerDetailsEO.setDocumentFile(byteArr);
   	     cisoServerDetailsEO.setDocumentType(csdReport.getContentType());
   	     RefCisoDocumentType refCisoDocType = new RefCisoDocumentType();
   	     refCisoDocType.setId(3);
   	  refCisoDocType.setType("CSD Report");
   	     cisoServerDetailsEO.setRefDocumentType(refCisoDocType);
   	     cisoServerDetailsEO.setId(csdReportId);
   	     cisoServerDetailsEO.setUploadedBy(userInfo.getUsername());
   	     cisoServerDetailsEO.setDateTime(DateUtils.obtainCurrentTimeStamp());
   	     CisoDocumentEo auditCertificateSave = cisoService.saveOrUpdateCisoDocument(cisoServerDetailsEO);
   	  applicationDetails.setCsdReportName(csdReport.getOriginalFilename());
   	  applicationDetails.setCsdReportId(auditCertificateSave.getId());
   	  
       	}
    	
    	CisoInfoApplicationDetails masterEO = cisoService.saveUpdate(applicationDetails);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @DeleteMapping(value = "/delete-ciso-application-details")
    public ResponseEntity<ReturnResponse> cisoInfoByCodeAndPost(@RequestParam Integer id) {
        Boolean letter = cisoService.deleteApplication(id);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO " +
                "Application Website details Successfully Deleted ") :
                new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-hei-md")
    public ResponseEntity<ReturnResponse> cisoMdHeiDetail(@RequestParam(required=false) String subCategory) {
        List<CisoHeiEO> letter = cisoService.cisoMdHeiDetail(subCategory);
        ReturnResponse returnResponse = ((letter != null) ? new ReturnResponse(HttpStatus.OK.value(), "Success", letter) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/delete-ciso-hei-md")
    public ResponseEntity<ReturnResponse> deleteCisoInfoHeiMd(@RequestParam String agencyCode,@RequestParam String instituteFullName) {
        Boolean letter = cisoService.deleteCisoInfoHeiMd(agencyCode,instituteFullName);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO HE MD Info Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @PostMapping(value = "/ciso-hei-md")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoHeiMd(@RequestBody CisoHeiEO cisohei) {
    	CisoHeiEO masterEO = cisoService.saveOrUpdateCisoHeiMd(cisohei);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-hei-md-notinciso")
    public ResponseEntity<ReturnResponse> cisoMdHeiDetailNotInCiso(@RequestParam(required=false) String subCategory) {
        List<CisoHeiEO> letter = cisoService.cisoMdHeiDetailNotInCiso(subCategory);
        ReturnResponse returnResponse = ((letter != null) ? new ReturnResponse(HttpStatus.OK.value(), "Success", letter) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-serverdetails")
    public ResponseEntity<ReturnResponse> getServerDetailsList() {
        List<CisoServerDetailsEO> masterEO = cisoService.getServerDetailsList();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-switchnetworkdetails")
    public ResponseEntity<ReturnResponse> getCisoNetworkDetailsList() {
        List<CisoSwitchNetworkDetailsEO> masterEO = cisoService.getCisoNetworkDetailsList();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-systemdetails")
    public ResponseEntity<ReturnResponse> getCisoSystemDetailsList() {
        List<CisoSystemDetailsEO> masterEO = cisoService.getCisoSystemDetailsList();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @PostMapping(value = "/ciso-serverdetails")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoServerDetails(@RequestBody CisoServerDetailsEO cisoServerDetailsEO) {
    	CisoServerDetailsEO masterEO = cisoService.saveOrUpdateCisoServerDetails(cisoServerDetailsEO);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @PostMapping(value = "/ciso-switchnetworkdetails")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoSwitchNetworkDetails(@RequestBody CisoSwitchNetworkDetailsEO cisoServerDetailsEO) {
    	CisoSwitchNetworkDetailsEO masterEO = cisoService.saveOrUpdateCisoSwitchNetworkDetailsEO(cisoServerDetailsEO);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @PostMapping(value = "/ciso-systemdetails")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoSystemDetailsList(@RequestBody CisoSystemDetailsEO cisoServerDetailsEO) {
    	CisoSystemDetailsEO masterEO = cisoService.saveOrUpdateCisoSystemDetailsList(cisoServerDetailsEO);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/delete-ciso-serverdetails")
    public ResponseEntity<ReturnResponse> deleteServerDetails(@RequestParam Integer serverId) {
        Boolean letter = cisoService.deleteServerDetails(serverId);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO Server Details Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/delete-ciso-switchnetworkdetails")
    public ResponseEntity<ReturnResponse> deleteSwitchNetworkDetails(@RequestParam Integer networkId) {
        Boolean letter = cisoService.deleteSwitchNetworkDetails(networkId);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO Switch Network Details Details Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/delete-ciso-systemdetails")
    public ResponseEntity<ReturnResponse> deleteSystemDetails(@RequestParam Integer systemId) {
        Boolean letter = cisoService.deleteSystemDetails(systemId);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO System Details Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @GetMapping(value = "/ciso-document")
    public ResponseEntity<ReturnResponse> getCisoDocumentList(@RequestParam(required=false) List<Integer> id) {
        List<CisoDocumentEo> masterEO = cisoService.getCisoDocumentList(id);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    @PostMapping(value = "/ciso-document")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoDocument(@RequestParam Integer id, @RequestParam String title, @RequestParam String documentType
    		, @RequestParam String uploadedBy,
    		//@ModelAttribute CisoDocumentEo cisoServerDetailsEO,
            @RequestParam("file") MultipartFile file) throws IOException {
    	byte[] byteArr = file.getBytes();
        boolean isPd = pdfOperation.isPdf(byteArr);
        String fileName = file.getOriginalFilename();
        CisoDocumentEo 	cisoServerDetailsEO = new CisoDocumentEo();
        if(isPd) {
        cisoServerDetailsEO.setFileName(fileName);
        cisoServerDetailsEO.setDocumentFile(byteArr);
        cisoServerDetailsEO.setDocumentType(documentType);
        cisoServerDetailsEO.setTitle(title);
        cisoServerDetailsEO.setId(id);
        cisoServerDetailsEO.setUploadedBy(uploadedBy);
    	CisoDocumentEo masterEO = cisoService.saveOrUpdateCisoDocument(cisoServerDetailsEO);
    	ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.OK.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
        }
        ReturnResponse returnResponse = ((isPd != true) ? new ReturnResponse(HttpStatus.BAD_REQUEST.value(), null, cisoServerDetailsEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
    
    @DeleteMapping(value = "/delete-ciso-document")
    public ResponseEntity<ReturnResponse> deleteCisoDocument(@RequestParam Integer documentId) {
        Boolean letter = cisoService.deleteCisoDocument(documentId);
        ReturnResponse returnResponse = ((letter == true) ? new ReturnResponse(HttpStatus.OK.value(), "CISO Document Successfully Deleted ") : new ReturnResponse(HttpStatus.NOT_FOUND.value(), "Not Found."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }


    @GetMapping(value = "/ciso-aio-system-details")
    public ResponseEntity<ReturnResponse> getCisoAIOSystemDetailsList() {
        List<CisoAIOSystemDetailsEO>  masterEO = cisoService.aioSystemDetails();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/ciso-aio-system-details")
    public ResponseEntity<ReturnResponse> saveOrUpdateCisoAIOSystemDetails(@RequestBody CisoAIOSystemDetailsEO cisoServerDetailsEO) {
        CisoAIOSystemDetailsEO masterEO = cisoService.saveOrUpdateCisoAIOSystemDetails(cisoServerDetailsEO);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ciso-govt-domain-contact-detail")
    public ResponseEntity<ReturnResponse> govtDomainContactDetails() {
        List<?>  masterEO = cisoService.govtDomainContactDetails();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value ="/ciso-govt-domain-contact-detail")
    public ResponseEntity<ReturnResponse> saveDomainContactDetails(@RequestBody GovtDomainContactDetailEO eo) {
        GovtDomainContactDetailEO  masterEO = cisoService.saveDomainContactDetails(eo);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @GetMapping(value = "/ciso-sub-domain-details")
    public ResponseEntity<ReturnResponse> subDomainDetail() {
        List<CisoSubDomainDetailEO>  masterEO = cisoService.subDomainDetail();
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.NOT_FOUND.value(), status));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }

    @PostMapping(value = "/ciso-sub-domain-details")
    public ResponseEntity<ReturnResponse> savesubDomainDetail(@RequestBody CisoSubDomainDetailEO cisoServerDetailsEO) {
        CisoSubDomainDetailEO masterEO = cisoService.savesubDomainDetail(cisoServerDetailsEO);
        ReturnResponse returnResponse = ((masterEO != null) ? new ReturnResponse(HttpStatus.OK.value(), null, masterEO) : new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request."));
        return new ResponseEntity<>(returnResponse, HttpStatus.valueOf(returnResponse.getStatus()));
    }
}