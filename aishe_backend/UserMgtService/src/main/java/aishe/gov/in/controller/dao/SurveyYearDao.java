package aishe.gov.in.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import aishe.gov.in.masterseo.RefSurveyAction;
import aishe.gov.in.masterseo.SurveyMasterEO;
import aishe.gov.in.masterseo.SurveyMasterNewEO;
import aishe.gov.in.masterseo.SurveyMastersLogEO;
import aishe.gov.in.masterseo.SurveyStatusLogsEO;
import aishe.gov.in.mastersvo.SurveyMasterDTO;
import aishe.gov.in.mastersvo.SurveyMasterNewVO;

public interface SurveyYearDao {
    public SurveyMasterEO getSurveyMaster(Integer surveyYear);
    List<SurveyMasterEO> getSurveyMasterYearList(Integer surveyYear);

    public SurveyMasterEO saveSurveyMaster(SurveyMasterEO masterEO);

    public SurveyMasterEO updateSurveyMaster(SurveyMasterEO masterEO, LocalDateTime prevStartLocalDateTime, LocalDateTime prevEndLocalDateTime );

    public List<SurveyMasterEO> getSurveyMasters(Integer surveyYear);

    public List<SurveyMastersLogEO> getSurveyMasterLog(Integer surveyYear, String type);
    public List<SurveyStatusLogsEO> getSurveyStatusLog(Integer surveyYear);
    public Integer MaxSurveyYear() ;

    public Timestamp getMaxSurveyYearFromUpload(Integer surveyYear);
    public Timestamp getMinSurveyYearFromUpload(Integer surveyYear);
	public List<RefSurveyAction> getSurveyAction();
	public List<SurveyMasterNewVO> getSurveyDataMasterList(Integer surveyYear, Boolean isSurveyFreezed);
	public SurveyMasterNewEO saveSurveyMaster(SurveyMasterNewEO surveyMaster, Integer creationType);
	public Integer fetchRegisterSurveyYearIsAlready(@NotNull Integer surveyYear);
	public Integer fetchSurveyYearIsAlready(@NotNull Integer surveyYear);
	public SurveyMasterNewEO saveSurveyYearSpecialPermission(SurveyMasterNewEO surveyMaster, Integer surveyCreationType);
	public SurveyMasterNewEO saveRegistrationSurveyYear(SurveyMasterNewEO surveyMaster, Integer surveyCreationType);
	public Long fetchSpecialPermissionForSurveyYearCanCreated(@NotNull Integer surveyYear);
	public SurveyMasterDTO updateFreezeSurveyYear(@Valid SurveyMasterDTO surveyMasterEO);
	public Integer fetchCreatedSurveyYearIsAlready(@NotNull Integer surveyYear);


}
