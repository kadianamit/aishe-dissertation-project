package aishe.gov.in.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import aishe.gov.in.enums.SurveyActionLog;
import aishe.gov.in.masterseo.RefSurveyAction;
import aishe.gov.in.mastersvo.SurveyMasterDTO;
import aishe.gov.in.mastersvo.SurveyMasterNewVO;
import aishe.gov.in.mastersvo.SurveyStatusDTO;
import aishe.gov.in.mastersvo.SurveyStatusWithUserStatusDTO;
import aishe.gov.in.mastersvo.YearDTO;
import aishe.gov.in.utility.ReturnResponse;

public interface SurveyYearService {


    SurveyMasterDTO saveSurveyYear(SurveyMasterDTO masterEO) throws ExecutionException, InterruptedException;

    SurveyMasterDTO editSurveyYear(SurveyMasterDTO masterEO);

    SurveyMasterDTO getSurveyMasterYear(Integer surveyYear);

    List<SurveyMasterDTO> getSurveyMasterYearList(Integer surveyYear);
    List<SurveyStatusDTO> getSurveyYearStatus(Integer surveyYear);

    ReturnResponse getSurveyYearLog(Integer surveyYear, SurveyActionLog actionLog);

    List<SurveyStatusWithUserStatusDTO>  freezeSurveyYear(List<SurveyStatusWithUserStatusDTO> statusDTO);
    YearDTO getYear(Integer surveyYear);

	List<RefSurveyAction> getSurveyAction();

	List<SurveyMasterNewVO> getSurveyMaster(Integer surveyYear, Boolean isSurveyFreezed);

	SurveyMasterDTO saveSurveyYearSpecialPermission(SurveyMasterDTO masterEO) throws ExecutionException, InterruptedException;

	SurveyMasterDTO saveSurveyYearNew(@Valid SurveyMasterDTO surveyMasterEO) throws InterruptedException, ExecutionException;

	SurveyMasterDTO saveRegistrationSurveyYear(@Valid SurveyMasterDTO surveyMasterEO) throws InterruptedException, ExecutionException;

	SurveyMasterDTO saveUpdateFreezeSurveyYear(@Valid SurveyMasterDTO surveyMasterEO);
}
