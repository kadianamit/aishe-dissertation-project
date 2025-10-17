package aishe.gov.in.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;

import aishe.gov.in.masterseo.RefCollegeInstitution;
import aishe.gov.in.masterseo.RefStandaloneInstitution;
import aishe.gov.in.masterseo.RefUniversity;
import aishe.gov.in.masterseo.SurveyStatusEO;

public interface AsyncOperationService {
   /* @Async
    CompletableFuture<List<RefUniversity>> saveUniversities(List<RefUniversity> refUniversities,Integer surveyYear);
    @Async
    CompletableFuture<List<RefCollegeInstitution>> saveColleges(List<RefCollegeInstitution> refUniversities, Integer surveyYear);
    @Async
    CompletableFuture<List<RefStandaloneInstitution>> saveStandalone(List<RefStandaloneInstitution> refUniversities, Integer surveyYear);
      */

    @Async
    CompletableFuture<List<SurveyStatusEO>> saveSurveyStatus(List<SurveyStatusEO> statusEOS, Integer surveyYear,String userId);
    /*
    @Async
    public CompletableFuture<List<RefUniversity>> getUniversityMaster(String aisheCode, Integer surveyYear, Boolean dcfStatus, String typeId);
    @Async
    public CompletableFuture<List<RefCollegeInstitution>> getCollegeMaster(Integer id, Integer surveyYear, Boolean dcfStatus,String type);
    @Async
    public CompletableFuture<List<RefStandaloneInstitution>> getStandaloneMaster(Integer id, Integer surveyYear, Integer stateBodyId);
    @Async
    public CompletableFuture<List<SurveyStatusEO>> getSurveyStatus(Integer surveyYear, String stateCode);*/

    @Async
    CompletableFuture<Boolean> saveCommonMaster(String query, Integer maxSurveyYear,Integer surveyYear);


}
