package aishe.gov.in.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.RefMasterDao;
import aishe.gov.in.masterseo.RefCollegeInstitution;
import aishe.gov.in.masterseo.RefStandaloneInstitution;
import aishe.gov.in.masterseo.RefStandaloneInstitution1;
import aishe.gov.in.masterseo.RefUniversity;
import aishe.gov.in.masterseo.SurveyStatusEO;

@Service
public class AsyncOperationServiceImpl implements AsyncOperationService {

    @Autowired
    private RefMasterDao refMasterDao;

  /*  @Override
    public CompletableFuture<List<RefUniversity>> saveUniversities(List<RefUniversity> refUniversities, Integer surveyYear) {
        return CompletableFuture.completedFuture(refMasterDao.saveRefUniversityList(refUniversities, surveyYear));
    }

    @Override
    public CompletableFuture<List<RefCollegeInstitution>> saveColleges(List<RefCollegeInstitution> refUniversities, Integer surveyYear) {
        return CompletableFuture.completedFuture(refMasterDao.saveRefCollegeInstitutionList(refUniversities, surveyYear));
    }

    @Override
    public CompletableFuture<List<RefStandaloneInstitution>> saveStandalone(List<RefStandaloneInstitution> refUniversities, Integer surveyYear) {
        return CompletableFuture.completedFuture(refMasterDao.saveRefStandaloneInstitutionList(refUniversities, surveyYear));
    }
*/
    @Override
    public CompletableFuture<List<SurveyStatusEO>> saveSurveyStatus(List<SurveyStatusEO> statusEOS, Integer surveyYear,String userId) {
        return CompletableFuture.completedFuture(refMasterDao.saveSurveyStatus(statusEOS, surveyYear,userId));
    }
/*
    @Override
    public CompletableFuture<List<RefUniversity>> getUniversityMaster(String aisheCode, Integer surveyYear, Boolean dcfStatus, String typeId) {
        return CompletableFuture.completedFuture(refMasterDao.getUniversityMaster(aisheCode,surveyYear,dcfStatus,typeId));
    }

    @Override
    public CompletableFuture<List<RefCollegeInstitution>> getCollegeMaster(Integer id, Integer surveyYear, Boolean dcfStatus, String type) {
        return CompletableFuture.completedFuture(refMasterDao.getCollegeMaster(id, surveyYear, dcfStatus, type));
    }

    @Override
    public CompletableFuture<List<RefStandaloneInstitution>> getStandaloneMaster(Integer id, Integer surveyYear,Integer stateBodyId) {
        return CompletableFuture.completedFuture(refMasterDao.getStandaloneMaster(id,surveyYear,stateBodyId));
    }

    @Override
    public CompletableFuture<List<SurveyStatusEO>> getSurveyStatus(Integer surveyYear, String stateCode) {
        return CompletableFuture.completedFuture(refMasterDao.getSurveyStatus(surveyYear,stateCode));
    }*/

    @Override
    public CompletableFuture<Boolean> saveCommonMaster(String query, Integer maxSurveyYear, Integer surveyYear) {
        return CompletableFuture.completedFuture(refMasterDao.saveCommonMaster(query,maxSurveyYear, surveyYear));
    }
}
