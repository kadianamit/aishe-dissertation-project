package aishe.gov.in.utility;

import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.SurveyMaster;
import aishe.gov.in.mastersvo.SelectOptionVO;
import aishe.gov.in.service.RemunerationSurveyYearService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
@Log4j2
public class RemunerationSurveyYearComponent {
    @Autowired
    private RemunerationSurveyYearService surveyYearService;

    public List<SelectOptionVO> generateSurveyYearDropDownListForAccount(String userId) {

        List<FormUpload> formuploadList = surveyYearService.findByApproverIdForBasicForms(userId);
        List<SurveyMaster> surveyYearListForSubmittingBankDetails =
                surveyYearService.getSurveyListForSubmittingBankDetails();
        List<SelectOptionVO> surveyYearDDList = new ArrayList();
        // surveyYearDDList.add(new SelectOptionVO("-1", "--Select Survey Year--"));
        for (SurveyMaster surveyMaster : surveyYearListForSubmittingBankDetails) {
            boolean isFormUploadedForSurveyYear = false;
            int surveyYear = surveyMaster.getSurveyYear();
            int nextYear = surveyYear + 1;
            if (surveyYear == 2010) {
                //Start from 2011-12
                continue;
            }
            for (FormUpload formUpload : formuploadList) {
                if (surveyYear == formUpload.getSurveyYear()) {
                    isFormUploadedForSurveyYear = true;
                    break;
                }
            }
            if (isFormUploadedForSurveyYear) {
                String surveyYearKey = new Integer(surveyYear).toString();
                String surveyYearValue = surveyYearKey + " - " + new Integer(nextYear);
                surveyYearDDList.add(new SelectOptionVO(surveyYearKey, surveyYearValue));
            }
        }
        List<SurveyMaster> lastTwoSurveyYear = surveyYearService.getClosedSurveyList(2);
        for (SurveyMaster sm : lastTwoSurveyYear) {
            int transFailedSurveyYear = sm.getSurveyYear();
            int transFailedNextYear = transFailedSurveyYear + 1;
            boolean transactionFailedSInSurveyYear = isTransactionFailedInSurveyYear(userId, transFailedSurveyYear);
            if (transactionFailedSInSurveyYear == true) {
                String transFailedSurveyYearKey = new Integer(transFailedSurveyYear).toString();
                String transFailedSurveyYearValue = transFailedSurveyYear + " - " + new Integer(transFailedNextYear);
                surveyYearDDList.add(new SelectOptionVO(transFailedSurveyYearKey, transFailedSurveyYearValue));
            }
        }
        // removed duplicates
        Set<SelectOptionVO> set = new LinkedHashSet<>(surveyYearDDList);
        surveyYearDDList.clear();
        surveyYearDDList.addAll(set);
        return surveyYearDDList;
    }

    public boolean isTransactionFailedInSurveyYear(String userId, Integer surveyYear) {
        try {
            List<FormUpload> formUploadList = surveyYearService.findByApproverIdAndSurveyYearForBasicForms(userId, surveyYear);
            for (FormUpload form : formUploadList) {
                RemunerationStatementDetailEO remunerationStatementDetail =
                        surveyYearService.findByFormIdAndStatusId(form.getId(), RemunerationConstant.TRANSACTION_FAILED_STATUS_ID);
                if (null != remunerationStatementDetail) {

                    return true;
                }
                break;
            }
        } catch (Exception e) {
            log.error("Exception in class: RemunerationBankAccountAction and method: isTransactionFailedInSurveyYear()- ", e);
        }


        return false;
    }


    public List<SelectOptionVO> generateSurveyYearDropDownListForApprovalDashBoard(){
       List<RemunerationNormEO> formuploadList = surveyYearService.findListByFrezzedSurveyYear(true);
        List<SurveyMaster> allSurveyMasterList = surveyYearService.getAllSurveyList();
        List<SelectOptionVO> surveyYearDDList = new ArrayList<SelectOptionVO>();
       // surveyYearDDList.add(new SelectOptionVO("-1", "--Select Survey Year--"));
        for(SurveyMaster surveyMaster : allSurveyMasterList) {
            boolean isFormUploadedForSurveyYear = false;
            int surveyYear = surveyMaster.getSurveyYear();
            int nextYear = surveyYear + 1;
            if(surveyYear == 2010) {
                continue;
            }
            for(RemunerationNormEO formUpload : formuploadList) {
                if(surveyYear == formUpload.getRemunerationKey().getSurveyYear()) {
                    isFormUploadedForSurveyYear = true;
                    break;
                }
            }
            if(isFormUploadedForSurveyYear) {
                String surveyYearKey = new Integer(surveyYear).toString();
                String surveyYearValue = surveyYearKey + " - " + new Integer(nextYear);
                surveyYearDDList.add(new SelectOptionVO(surveyYearKey, surveyYearValue));
            }

        }
        return surveyYearDDList;
    }





}
