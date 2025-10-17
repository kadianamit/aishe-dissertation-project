package aishe.gov.in.service;

import aishe.gov.in.dao.RemunerationSurveyYearDao;
import aishe.gov.in.masterseo.FormUpload;
import aishe.gov.in.masterseo.RemunerationNormEO;
import aishe.gov.in.masterseo.RemunerationStatementDetailEO;
import aishe.gov.in.masterseo.SurveyMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RemunerationSurveyYearServiceImpl implements RemunerationSurveyYearService {
    @Autowired
    private RemunerationSurveyYearDao surveyYearDao;

    @Override
    public List<FormUpload> findByApproverIdForBasicForms(String id) {
        return surveyYearDao.findByApproverIdForBasicForms(id);
    }

    @Override
    public List<FormUpload> findByApproverIdAndSurveyYearForBasicForms(String id, Integer surveyYear) {
        return surveyYearDao.findByApproverIdAndSurveyYearForBasicForms(id, surveyYear);
    }

    @Override
    public List<SurveyMaster> getSurveyListForSubmittingBankDetails() {
        return surveyYearDao.getSurveyListForSubmittingBankDetails();
    }

    @Override
    public List<SurveyMaster> getClosedSurveyList(int x) {
        return surveyYearDao.getClosedSurveyList(x);
    }

    @Override
    public RemunerationStatementDetailEO findByFormIdAndStatusId(int formUploadId, int statusId) {
        return surveyYearDao.findByFormIdAndStatusId(formUploadId, statusId);
    }

    @Override
    public List<SurveyMaster> getAllSurveyList() {
        return surveyYearDao.getAllSurveyList();
    }

    @Override
    public List<RemunerationNormEO> findListByFrezzedSurveyYear(boolean flag) {
        return surveyYearDao.findListByFrezzedSurveyYear(flag);
    }
}
