package aishe.gov.in.service;

import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.SurveyMaster;

import java.util.List;

public interface RemunerationSurveyYearService {
    List<FormUpload> findByApproverIdForBasicForms(String id);
    List<FormUpload> findByApproverIdAndSurveyYearForBasicForms(String id, Integer surveyYear);
    List<SurveyMaster> getSurveyListForSubmittingBankDetails();
    List<SurveyMaster> getClosedSurveyList(int x);
    RemunerationStatementDetailEO findByFormIdAndStatusId(int formUploadId, int statusId);

    List<SurveyMaster> getAllSurveyList();
    List<RemunerationNormEO> findListByFrezzedSurveyYear(boolean flag);
}
