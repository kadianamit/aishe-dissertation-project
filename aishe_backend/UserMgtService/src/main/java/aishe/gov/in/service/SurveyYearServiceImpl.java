package aishe.gov.in.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import aishe.gov.in.dao.NativeQuerySystem;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.ActionLogDao;
import aishe.gov.in.dao.RefMasterDao;
import aishe.gov.in.dao.SurveyYearDao;
import aishe.gov.in.enums.RefStateBodyType;
import aishe.gov.in.enums.RefUniversityCollegeType;
import aishe.gov.in.enums.RefUniversityType;
import aishe.gov.in.enums.SurveyActionLog;
import aishe.gov.in.exception.EntityException;
import aishe.gov.in.masterseo.RefCollegeInstitution;
import aishe.gov.in.masterseo.RefStandaloneInstitution;
import aishe.gov.in.masterseo.RefSurveyAction;
import aishe.gov.in.masterseo.RefUniversity;
import aishe.gov.in.masterseo.SurveyMasterEO;
import aishe.gov.in.masterseo.SurveyMasterNewEO;
import aishe.gov.in.masterseo.SurveyMastersLogEO;
import aishe.gov.in.masterseo.SurveyStatusEO;
import aishe.gov.in.masterseo.SurveyStatusLogsEO;
import aishe.gov.in.mastersvo.SurveyMasterDTO;
import aishe.gov.in.mastersvo.SurveyMasterNewVO;
import aishe.gov.in.mastersvo.SurveyStatusDTO;
import aishe.gov.in.mastersvo.SurveyStatusWithUserStatusDTO;
import aishe.gov.in.mastersvo.YearDTO;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.ObjectBinderComponent;
import aishe.gov.in.utility.ReturnResponse;

@Service
public class SurveyYearServiceImpl implements SurveyYearService {

    @Autowired
    private SurveyYearDao surveyYearDao;
    @Autowired
    private RefMasterDao refMasterDao;

    @Autowired
    private ActionLogDao actionLogDao;

    @Autowired
    private AsyncOperationService asyncOperationService;
    @Autowired
    private ObjectBinderComponent component;

    @Override
    public SurveyMasterDTO saveSurveyYear(SurveyMasterDTO masterEO) throws ExecutionException, InterruptedException {

        SurveyMasterEO surveyMaster = SurveyMasterEO.builder()
                .surveyYear(masterEO.getSurveyYear())
                .startDate(DateUtils.convertStringDateTimeToDBDateTime(masterEO.getStartDate()))
                .endDate(DateUtils.convertStringDateTimeToDBDateTime(masterEO.getEndDate()))
                .userId(masterEO.getUserId())
                .build();
        SurveyMasterEO surveyMasterEO = surveyYearDao.saveSurveyMaster(surveyMaster);
        if (null != surveyMasterEO) {
            Integer maxSurveyYear = masterEO.getSurveyYear() - 1;/* surveyYearDao.MaxSurveyYear();*/
       /*  List<SurveyStatusEO> list=   refMasterDao.getSurveyStatus(maxSurveyYear, null);
            refMasterDao.saveSurveyStatus(list, masterEO.getSurveyYear(), masterEO.getUserId());*/


            // job 1 get all  get all task list
           /* CompletableFuture<List<RefUniversity>> universities = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_University.getType());
            CompletableFuture<List<RefUniversity>> universities2 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Public_University.getType());
            CompletableFuture<List<RefUniversity>> universities3 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_University.getType());
            CompletableFuture<List<RefUniversity>> universities4 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government.getType());
            CompletableFuture<List<RefUniversity>> universities5 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government_Aided.getType());
            CompletableFuture<List<RefUniversity>> universities6 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Private.getType());
            CompletableFuture<List<RefUniversity>> universities7 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_of_National_Importance.getType());
            CompletableFuture<List<RefUniversity>> universities8 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_under_State_Legislature_Act.getType());
            CompletableFuture<List<RefUniversity>> universities9 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities10 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities11 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities12 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Other.getType());

            CompletableFuture<List<RefCollegeInstitution>> colleges = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Affiliated_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges2 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Autonomous_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges3 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Other.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges4 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Constituent_University_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges5 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Recognized_Center.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges6 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.PG_Center_Off_Campus_Center.getType());

           */

          /*  CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Paramedical.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions2 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Technical_Polytechnic.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions3 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Nursing.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions4 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.TeacherTraining.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions5 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Institutes_under_Ministries.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions6 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Hotel_Management_and_Catering.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions7 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.PGDM_Institutes.getType());
            CompletableFuture.allOf(universities, universities2, universities3, universities4, universities5, universities6, universities7, universities8, universities9, universities10, universities11, universities12,
                    colleges, colleges2, colleges3, colleges4, colleges5, colleges6, statusEOS,
                    standaloneInstitutions, standaloneInstitutions2, standaloneInstitutions3, standaloneInstitutions4, standaloneInstitutions5, standaloneInstitutions6, standaloneInstitutions7).join();
*/
            /*refMasterDao.saveRefUniversityList(universities,surveyMasterEO.getSurveyYear());
            refMasterDao.saveRefStandaloneInstitutionList(standaloneInstitutions,surveyMasterEO.getSurveyYear());
            refMasterDao.saveSurveyStatus(statusEOS,masterEO.getSurveyYear(), masterEO.getUserId());
            refMasterDao.saveRefCollegeInstitutionList(colleges,surveyMasterEO.getSurveyYear());*/

            //job 2 save universities,survey status and standalone
            /*CompletableFuture<List<RefUniversity>> saveUniversities = asyncOperationService.saveUniversities(universities.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities2 = asyncOperationService.saveUniversities(universities2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities3 = asyncOperationService.saveUniversities(universities3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities4 = asyncOperationService.saveUniversities(universities4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities5 = asyncOperationService.saveUniversities(universities5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities6 = asyncOperationService.saveUniversities(universities6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities7 = asyncOperationService.saveUniversities(universities7.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities8 = asyncOperationService.saveUniversities(universities8.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities9 = asyncOperationService.saveUniversities(universities9.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities10 = asyncOperationService.saveUniversities(universities10.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities11 = asyncOperationService.saveUniversities(universities11.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities12 = asyncOperationService.saveUniversities(universities12.get(), surveyMasterEO.getSurveyYear());


            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone = asyncOperationService.saveStandalone(standaloneInstitutions.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone2 = asyncOperationService.saveStandalone(standaloneInstitutions2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone3 = asyncOperationService.saveStandalone(standaloneInstitutions3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone4 = asyncOperationService.saveStandalone(standaloneInstitutions4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone5 = asyncOperationService.saveStandalone(standaloneInstitutions5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone6 = asyncOperationService.saveStandalone(standaloneInstitutions6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone7 = asyncOperationService.saveStandalone(standaloneInstitutions7.get(), surveyMasterEO.getSurveyYear());
*/

            /*CompletableFuture.allOf(saveUniversities, saveUniversities2, saveUniversities3, saveUniversities4, saveUniversities5, saveUniversities6, saveUniversities7, saveUniversities8, saveUniversities9, saveUniversities10, saveUniversities11, saveUniversities12,
                    saveStandalone, saveStandalone2, saveStandalone3, saveStandalone4, saveStandalone5, saveStandalone6, saveStandalone7, SurveyStatusEO).join();


            //job 2 save to save college parallel
            CompletableFuture<List<RefCollegeInstitution>> saveCollege1 = asyncOperationService.saveColleges(colleges.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege2 = asyncOperationService.saveColleges(colleges2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege3 = asyncOperationService.saveColleges(colleges3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege4 = asyncOperationService.saveColleges(colleges4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege5 = asyncOperationService.saveColleges(colleges5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege6 = asyncOperationService.saveColleges(colleges6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture.allOf(saveCollege1, saveCollege2, saveCollege3, saveCollege4, saveCollege5, saveCollege6).join();*/
            List<SurveyStatusEO> statusEOS = refMasterDao.getSurveyStatus(maxSurveyYear, null);

            CompletableFuture<List<SurveyStatusEO>> saveSurveyStatus = asyncOperationService.saveSurveyStatus(statusEOS, surveyMasterEO.getSurveyYear(), masterEO.getUserId());
            CompletableFuture<Boolean> saveColleges = asyncOperationService.saveCommonMaster(NativeQuerySystem.collegeQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveStandalone = asyncOperationService.saveCommonMaster(NativeQuerySystem.standaloneQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveUniversity = asyncOperationService.saveCommonMaster(NativeQuerySystem.universityQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture.allOf(saveUniversity,saveSurveyStatus).join();
            CompletableFuture.allOf(saveColleges, saveStandalone).join();
        }
        return bindSurveyObject(surveyMasterEO);
    }

    @Override
    public SurveyMasterDTO editSurveyYear(SurveyMasterDTO masterEO) {
        SurveyMasterEO surveyMasterEO = surveyYearDao.getSurveyMaster(masterEO.getSurveyYear());
        if (null != surveyMasterEO) {
            LocalDateTime prevStartLocalDateTime = surveyMasterEO.getStartDate() != null ? surveyMasterEO.getStartDate() : null;
            LocalDateTime prevEndLocalDateTime = surveyMasterEO.getEndDate() != null ? surveyMasterEO.getEndDate() : null;
            surveyMasterEO.setEndDate(masterEO.getEndDate() != null ? DateUtils.convertStringDateTimeToDBDateTime(masterEO.getEndDate()) : null);
            surveyMasterEO.setUserId(masterEO.getUserId());
            return bindSurveyObject(surveyYearDao.updateSurveyMaster(surveyMasterEO, prevStartLocalDateTime, prevEndLocalDateTime));
        }
        throw new EntityException("Survey year " + masterEO.getSurveyYear() + " not found ");
    }

    private SurveyMasterDTO bindSurveyObject(SurveyMasterEO surveyMasterEO) {
        if (null != surveyMasterEO)
            return SurveyMasterDTO
                    .builder()
                    .surveyYear(surveyMasterEO.getSurveyYear())
                    .endDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getEndDate()))
                    .startDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getStartDate()))
                    .build();
        return null;
    }
    
    private SurveyMasterDTO bindSurveyNewObject(SurveyMasterNewEO surveyMasterEO) {
    	SurveyMasterDTO dto = new SurveyMasterDTO();
        dto.setSurveyYear(surveyMasterEO.getSurveyYear());
        dto.setMessage("Success");
       // if(surveyMasterEO.getEndDate()!=null || !surveyMasterEO.getEndDate().equals("")) {
        //dto.setEndDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getEndDate()));	
        //}
       // dto.setStartDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getStartDate()));
        return dto;//SurveyMasterDTO
//                    .builder()
//                    .surveyYear(surveyMasterEO.getSurveyYear())
//                    .endDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getEndDate()))
//                    .startDate(DateUtils.convertDBDateTimeToString(surveyMasterEO.getStartDate()))
//                    .build();
        //return null;
    }



    private List<SurveyMasterDTO> bindSurveyObjectList(List<SurveyMasterEO> surveyMasterEO) {
        List<SurveyMasterDTO> list = new ArrayList<>();
        if (null != surveyMasterEO && surveyMasterEO.size() > 0)
            for (SurveyMasterEO masterEO : surveyMasterEO) {
                list.add(SurveyMasterDTO
                        .builder()
                        .surveyYear(masterEO.getSurveyYear())
                        .endDate(DateUtils.convertDBDateTimeToString(masterEO.getEndDate()))
                        .startDate(DateUtils.convertDBDateTimeToString(masterEO.getStartDate()))
                        .build());
            }
        return list;
    }

    private YearDTO bindSurveyYearObject(Integer year) {
        if (null != year) {
            Timestamp max = surveyYearDao.getMaxSurveyYearFromUpload(year);
            Timestamp min =   surveyYearDao.getMinSurveyYearFromUpload(year);

            return YearDTO
                    .builder()
                    .surveyYear(year)
                    .endYear(max != null ? max.toLocalDateTime().getYear() : null)
                    .startYear(min != null?min.toLocalDateTime().getYear():null)
                    .build();
        }
        return null;
    }
    @Override
    public SurveyMasterDTO getSurveyMasterYear(Integer surveyYear) {
        return bindSurveyObject(surveyYearDao.getSurveyMaster(surveyYear));
    }

    @Override
    public List<SurveyMasterDTO> getSurveyMasterYearList(Integer surveyYear) {
        return bindSurveyObjectList(surveyYearDao.getSurveyMasterYearList(surveyYear));
    }

    @Override
    public List<SurveyStatusDTO> getSurveyYearStatus(Integer surveyYear) {
        return component.bindStatusDto(refMasterDao.getSurveyStatus(surveyYear, null));
    }

    @Override
    public ReturnResponse getSurveyYearLog(Integer surveyYear, SurveyActionLog actionLog) {
        ReturnResponse returnResponse = null;
        if(actionLog!=null) {
        if (actionLog.getActionType()!=null) {
            //master Log
            List<SurveyMastersLogEO> surveyActionLog = surveyYearDao.getSurveyMasterLog(surveyYear,actionLog.getActionType());
            returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", component.bindMasterDTO(surveyActionLog));
        } }else {
            //status log
            List<SurveyStatusLogsEO> surveyActionLog = surveyYearDao.getSurveyStatusLog(surveyYear);
            returnResponse = new ReturnResponse(HttpStatus.OK.value(), "success ", component.bindStatusDTO(surveyActionLog));
        }
        return returnResponse;
    }

    @Override
    public List<SurveyStatusWithUserStatusDTO> freezeSurveyYear(List<SurveyStatusWithUserStatusDTO> statusDTO) {
        return refMasterDao.freezeSurveyYear(statusDTO);
    }

    @Override
    public YearDTO getYear(Integer surveyYear) {
        return bindSurveyYearObject(surveyYear);
    }

	@Override
	public List<RefSurveyAction> getSurveyAction() {
		return surveyYearDao.getSurveyAction();
	}

	@Override
	public List<SurveyMasterNewVO> getSurveyMaster(Integer surveyYear,Boolean isSurveyFreezed) {
		return surveyYearDao.getSurveyDataMasterList(surveyYear,isSurveyFreezed);
	}

	@Override
	public SurveyMasterDTO saveSurveyYearSpecialPermission(SurveyMasterDTO masterEO)
			throws ExecutionException, InterruptedException {
		Long isSurveyAllow= surveyYearDao.fetchSpecialPermissionForSurveyYearCanCreated(masterEO.getSurveyYear());
		if(isSurveyAllow==0 || isSurveyAllow==null) {
			masterEO.setMessage("Special Permission can not be created for this survey");
			return masterEO;
		}
		masterEO.setMessage("Success");
		SurveyMasterNewEO surveyMaster = SurveyMasterNewEO.builder()
                .surveyYear(masterEO.getSurveyYear())
                .specialPermissionStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()))
                .specialPermissionEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()))
                .userId(masterEO.getUserId())
                .build();
		SurveyMasterNewEO surveyMasterEO = surveyYearDao.saveSurveyYearSpecialPermission(surveyMaster,masterEO.getSurveyCreationType());
        return bindSurveyNewObject(surveyMasterEO);
    }

	@Override
	public SurveyMasterDTO saveSurveyYearNew(@Valid SurveyMasterDTO masterEO) throws InterruptedException, ExecutionException {

//        SurveyMasterNewEO surveyMaster = SurveyMasterNewEO.builder()
//                .surveyYear(masterEO.getSurveyYear())
//                .startDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()))
//                .endDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()))
//                .surveyStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()))
//                .surveyEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()))
//                .userId(masterEO.getUserId())
//                .build();
        
        SurveyMasterNewEO surveyMaster=new SurveyMasterNewEO();
        surveyMaster.setSurveyYear(masterEO.getSurveyYear());
        surveyMaster.setStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()));
        if(!masterEO.getEndDate().equals("")) {
        surveyMaster.setEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()));
        }
        surveyMaster.setSurveyStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()));
        if(!masterEO.getEndDate().equals("")) {
            surveyMaster.setSurveyEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()));
        }
        surveyMaster.setUserId(masterEO.getUserId());
        
        
        
        if(masterEO.getSurveyCreationType()==1 || masterEO.getSurveyCreationType()==8){
        	Integer createSurveyYear = surveyYearDao.fetchCreatedSurveyYearIsAlready(masterEO.getSurveyYear());
        	if(createSurveyYear!=null) {
        	masterEO.setMessage("Survey Year is Already Created.");
        	return masterEO;
        	}
        }
        masterEO.setMessage("Success");
        SurveyMasterNewEO surveyMasterEO = surveyYearDao.saveSurveyMaster(surveyMaster,masterEO.getSurveyCreationType());
        Integer surveyYear = surveyYearDao.fetchRegisterSurveyYearIsAlready(masterEO.getSurveyYear());
        if (null != surveyMasterEO && surveyYear==null) {
            Integer maxSurveyYear = masterEO.getSurveyYear() - 1;/* surveyYearDao.MaxSurveyYear();*/
       /*  List<SurveyStatusEO> list=   refMasterDao.getSurveyStatus(maxSurveyYear, null);
            refMasterDao.saveSurveyStatus(list, masterEO.getSurveyYear(), masterEO.getUserId());*/


            // job 1 get all  get all task list
          /*  CompletableFuture<List<RefUniversity>> universities = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_University.getType());
            CompletableFuture<List<RefUniversity>> universities2 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Public_University.getType());
            CompletableFuture<List<RefUniversity>> universities3 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_University.getType());
            CompletableFuture<List<RefUniversity>> universities4 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government.getType());
            CompletableFuture<List<RefUniversity>> universities5 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government_Aided.getType());
            CompletableFuture<List<RefUniversity>> universities6 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Private.getType());
            CompletableFuture<List<RefUniversity>> universities7 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_of_National_Importance.getType());
            CompletableFuture<List<RefUniversity>> universities8 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_under_State_Legislature_Act.getType());
            CompletableFuture<List<RefUniversity>> universities9 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities10 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities11 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities12 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Other.getType());

            CompletableFuture<List<RefCollegeInstitution>> colleges = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Affiliated_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges2 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Autonomous_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges3 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Other.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges4 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Constituent_University_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges5 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Recognized_Center.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges6 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.PG_Center_Off_Campus_Center.getType());

            CompletableFuture<List<SurveyStatusEO>> statusEOS = asyncOperationService.getSurveyStatus(maxSurveyYear, null);

            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Paramedical.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions2 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Technical_Polytechnic.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions3 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Nursing.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions4 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.TeacherTraining.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions5 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Institutes_under_Ministries.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions6 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Hotel_Management_and_Catering.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions7 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.PGDM_Institutes.getType());
            CompletableFuture.allOf(universities, universities2, universities3, universities4, universities5, universities6, universities7, universities8, universities9, universities10, universities11, universities12,
                    colleges, colleges2, colleges3, colleges4, colleges5, colleges6, statusEOS,
                    standaloneInstitutions, standaloneInstitutions2, standaloneInstitutions3, standaloneInstitutions4, standaloneInstitutions5, standaloneInstitutions6, standaloneInstitutions7).join();

            *//*refMasterDao.saveRefUniversityList(universities,surveyMasterEO.getSurveyYear());
            refMasterDao.saveRefStandaloneInstitutionList(standaloneInstitutions,surveyMasterEO.getSurveyYear());
            refMasterDao.saveSurveyStatus(statusEOS,masterEO.getSurveyYear(), masterEO.getUserId());
            refMasterDao.saveRefCollegeInstitutionList(colleges,surveyMasterEO.getSurveyYear());*//*
            /////////////////please write the spring batch if possible there
            //job 2 save universities,survey status and standalone
            CompletableFuture<List<RefUniversity>> saveUniversities = asyncOperationService.saveUniversities(universities.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities2 = asyncOperationService.saveUniversities(universities2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities3 = asyncOperationService.saveUniversities(universities3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities4 = asyncOperationService.saveUniversities(universities4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities5 = asyncOperationService.saveUniversities(universities5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities6 = asyncOperationService.saveUniversities(universities6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities7 = asyncOperationService.saveUniversities(universities7.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities8 = asyncOperationService.saveUniversities(universities8.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities9 = asyncOperationService.saveUniversities(universities9.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities10 = asyncOperationService.saveUniversities(universities10.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities11 = asyncOperationService.saveUniversities(universities11.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities12 = asyncOperationService.saveUniversities(universities12.get(), surveyMasterEO.getSurveyYear());


            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone = asyncOperationService.saveStandalone(standaloneInstitutions.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone2 = asyncOperationService.saveStandalone(standaloneInstitutions2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone3 = asyncOperationService.saveStandalone(standaloneInstitutions3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone4 = asyncOperationService.saveStandalone(standaloneInstitutions4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone5 = asyncOperationService.saveStandalone(standaloneInstitutions5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone6 = asyncOperationService.saveStandalone(standaloneInstitutions6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone7 = asyncOperationService.saveStandalone(standaloneInstitutions7.get(), surveyMasterEO.getSurveyYear());

            CompletableFuture<List<SurveyStatusEO>> SurveyStatusEO = asyncOperationService.saveSurveyStatus(statusEOS.get(), surveyMasterEO.getSurveyYear(), masterEO.getUserId());

            CompletableFuture.allOf(saveUniversities, saveUniversities2, saveUniversities3, saveUniversities4, saveUniversities5, saveUniversities6, saveUniversities7, saveUniversities8, saveUniversities9, saveUniversities10, saveUniversities11, saveUniversities12,
                    saveStandalone, saveStandalone2, saveStandalone3, saveStandalone4, saveStandalone5, saveStandalone6, saveStandalone7, SurveyStatusEO).join();


            //job 2 save to save college parallel
            CompletableFuture<List<RefCollegeInstitution>> saveCollege1 = asyncOperationService.saveColleges(colleges.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege2 = asyncOperationService.saveColleges(colleges2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege3 = asyncOperationService.saveColleges(colleges3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege4 = asyncOperationService.saveColleges(colleges4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege5 = asyncOperationService.saveColleges(colleges5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege6 = asyncOperationService.saveColleges(colleges6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture.allOf(saveCollege1, saveCollege2, saveCollege3, saveCollege4, saveCollege5, saveCollege6).join();*/

            List<SurveyStatusEO> statusEOS = refMasterDao.getSurveyStatus(maxSurveyYear, null);

            CompletableFuture<List<SurveyStatusEO>> saveSurveyStatus = asyncOperationService.saveSurveyStatus(statusEOS, surveyMasterEO.getSurveyYear(), masterEO.getUserId());
            CompletableFuture<Boolean> saveColleges = asyncOperationService.saveCommonMaster(NativeQuerySystem.collegeQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveStandalone = asyncOperationService.saveCommonMaster(NativeQuerySystem.standaloneQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveUniversity = asyncOperationService.saveCommonMaster(NativeQuerySystem.universityQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture.allOf(saveUniversity,saveSurveyStatus).join();
            CompletableFuture.allOf(saveColleges, saveStandalone).join();
        }
        return bindSurveyNewObject(surveyMasterEO);
    }

	@Override
	public SurveyMasterDTO saveRegistrationSurveyYear(@Valid SurveyMasterDTO masterEO) throws InterruptedException, ExecutionException {

//        SurveyMasterNewEO surveyMaster = SurveyMasterNewEO.builder()
//                .surveyYear(masterEO.getSurveyYear())
//                .registrationStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()))
//                .registrationEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()))
//                .userId(masterEO.getUserId())
//                .build();
        
        SurveyMasterNewEO surveyMaster=new SurveyMasterNewEO();
        surveyMaster.setSurveyYear(masterEO.getSurveyYear());
        surveyMaster.setRegistrationStartDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getStartDate()));
        if(!masterEO.getEndDate().equals("")) {
        surveyMaster.setRegistrationEndDate(DateUtils.convertStringDateTimeToDBDateTimeNew(masterEO.getEndDate()));
        }
        
        surveyMaster.setUserId(masterEO.getUserId());
        
        if(masterEO.getSurveyCreationType()==5){
        	Integer createSurveyYear = surveyYearDao.fetchRegisterSurveyYearIsAlready(masterEO.getSurveyYear());
        	if(createSurveyYear!=null) {
        	masterEO.setMessage("Registration Survey Year Is Already Created.");
        	return masterEO;
        	}
        }
        masterEO.setMessage("Success");
        SurveyMasterNewEO surveyMasterEO = surveyYearDao.saveRegistrationSurveyYear(surveyMaster,masterEO.getSurveyCreationType());
        Integer surveyYear = surveyYearDao.fetchSurveyYearIsAlready(masterEO.getSurveyYear());
        if (null != surveyMasterEO && surveyYear==null) {
            Integer maxSurveyYear = masterEO.getSurveyYear() - 1;/* surveyYearDao.MaxSurveyYear();*/
       /*  List<SurveyStatusEO> list=   refMasterDao.getSurveyStatus(maxSurveyYear, null);
            refMasterDao.saveSurveyStatus(list, masterEO.getSurveyYear(), masterEO.getUserId());*/


            // job 1 get all  get all task list
            /*CompletableFuture<List<RefUniversity>> universities = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_University.getType());
            CompletableFuture<List<RefUniversity>> universities2 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Public_University.getType());
            CompletableFuture<List<RefUniversity>> universities3 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_University.getType());
            CompletableFuture<List<RefUniversity>> universities4 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government.getType());
            CompletableFuture<List<RefUniversity>> universities5 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Government_Aided.getType());
            CompletableFuture<List<RefUniversity>> universities6 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Deemed_University_Private.getType());
            CompletableFuture<List<RefUniversity>> universities7 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_of_National_Importance.getType());
            CompletableFuture<List<RefUniversity>> universities8 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Institute_under_State_Legislature_Act.getType());
            CompletableFuture<List<RefUniversity>> universities9 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Central_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities10 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities11 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.State_Private_Open_University.getType());
            CompletableFuture<List<RefUniversity>> universities12 = asyncOperationService.getUniversityMaster(null, maxSurveyYear, true, RefUniversityType.Other.getType());

            CompletableFuture<List<RefCollegeInstitution>> colleges = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Affiliated_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges2 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Autonomous_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges3 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Other.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges4 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Constituent_University_College.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges5 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.Recognized_Center.getType());
            CompletableFuture<List<RefCollegeInstitution>> colleges6 = asyncOperationService.getCollegeMaster(null, maxSurveyYear, true, RefUniversityCollegeType.PG_Center_Off_Campus_Center.getType());

            CompletableFuture<List<SurveyStatusEO>> statusEOS = asyncOperationService.getSurveyStatus(maxSurveyYear, null);

            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Paramedical.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions2 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Technical_Polytechnic.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions3 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Nursing.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions4 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.TeacherTraining.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions5 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Institutes_under_Ministries.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions6 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.Hotel_Management_and_Catering.getType());
            CompletableFuture<List<RefStandaloneInstitution>> standaloneInstitutions7 = asyncOperationService.getStandaloneMaster(null, maxSurveyYear, RefStateBodyType.PGDM_Institutes.getType());
            CompletableFuture.allOf(universities, universities2, universities3, universities4, universities5, universities6, universities7, universities8, universities9, universities10, universities11, universities12,
                    colleges, colleges2, colleges3, colleges4, colleges5, colleges6, statusEOS,
                    standaloneInstitutions, standaloneInstitutions2, standaloneInstitutions3, standaloneInstitutions4, standaloneInstitutions5, standaloneInstitutions6, standaloneInstitutions7).join();

            *//*refMasterDao.saveRefUniversityList(universities,surveyMasterEO.getSurveyYear());
            refMasterDao.saveRefStandaloneInstitutionList(standaloneInstitutions,surveyMasterEO.getSurveyYear());
            refMasterDao.saveSurveyStatus(statusEOS,masterEO.getSurveyYear(), masterEO.getUserId());
            refMasterDao.saveRefCollegeInstitutionList(colleges,surveyMasterEO.getSurveyYear());*//*

            //job 2 save universities,survey status and standalone
            CompletableFuture<List<RefUniversity>> saveUniversities = asyncOperationService.saveUniversities(universities.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities2 = asyncOperationService.saveUniversities(universities2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities3 = asyncOperationService.saveUniversities(universities3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities4 = asyncOperationService.saveUniversities(universities4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities5 = asyncOperationService.saveUniversities(universities5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities6 = asyncOperationService.saveUniversities(universities6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities7 = asyncOperationService.saveUniversities(universities7.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities8 = asyncOperationService.saveUniversities(universities8.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities9 = asyncOperationService.saveUniversities(universities9.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities10 = asyncOperationService.saveUniversities(universities10.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities11 = asyncOperationService.saveUniversities(universities11.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefUniversity>> saveUniversities12 = asyncOperationService.saveUniversities(universities12.get(), surveyMasterEO.getSurveyYear());


            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone = asyncOperationService.saveStandalone(standaloneInstitutions.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone2 = asyncOperationService.saveStandalone(standaloneInstitutions2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone3 = asyncOperationService.saveStandalone(standaloneInstitutions3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone4 = asyncOperationService.saveStandalone(standaloneInstitutions4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone5 = asyncOperationService.saveStandalone(standaloneInstitutions5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone6 = asyncOperationService.saveStandalone(standaloneInstitutions6.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefStandaloneInstitution>> saveStandalone7 = asyncOperationService.saveStandalone(standaloneInstitutions7.get(), surveyMasterEO.getSurveyYear());

            CompletableFuture<List<SurveyStatusEO>> SurveyStatusEO = asyncOperationService.saveSurveyStatus(statusEOS.get(), surveyMasterEO.getSurveyYear(), masterEO.getUserId());

            CompletableFuture.allOf(saveUniversities, saveUniversities2, saveUniversities3, saveUniversities4, saveUniversities5, saveUniversities6, saveUniversities7, saveUniversities8, saveUniversities9, saveUniversities10, saveUniversities11, saveUniversities12,
                    saveStandalone, saveStandalone2, saveStandalone3, saveStandalone4, saveStandalone5, saveStandalone6, saveStandalone7, SurveyStatusEO).join();


            //job 2 save to save college parallel
            CompletableFuture<List<RefCollegeInstitution>> saveCollege1 = asyncOperationService.saveColleges(colleges.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege2 = asyncOperationService.saveColleges(colleges2.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege3 = asyncOperationService.saveColleges(colleges3.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege4 = asyncOperationService.saveColleges(colleges4.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege5 = asyncOperationService.saveColleges(colleges5.get(), surveyMasterEO.getSurveyYear());
            CompletableFuture<List<RefCollegeInstitution>> saveCollege6 = asyncOperationService.saveColleges(colleges6.get(), surveyMasterEO.getSurveyYear());

            CompletableFuture.allOf(saveCollege1, saveCollege2, saveCollege3, saveCollege4, saveCollege5, saveCollege6).join();*/

            List<SurveyStatusEO> statusEOS = refMasterDao.getSurveyStatus(maxSurveyYear, null);

            CompletableFuture<List<SurveyStatusEO>> saveSurveyStatus = asyncOperationService.saveSurveyStatus(statusEOS, surveyMasterEO.getSurveyYear(), masterEO.getUserId());
            CompletableFuture<Boolean> saveColleges = asyncOperationService.saveCommonMaster(NativeQuerySystem.collegeQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveStandalone = asyncOperationService.saveCommonMaster(NativeQuerySystem.standaloneQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture<Boolean> saveUniversity = asyncOperationService.saveCommonMaster(NativeQuerySystem.universityQuery,maxSurveyYear, surveyMasterEO.getSurveyYear());
            CompletableFuture.allOf(saveUniversity,saveSurveyStatus).join();
            CompletableFuture.allOf(saveColleges, saveStandalone).join();

        }
        return bindSurveyNewObject(surveyMasterEO);
    }

	@Override
	public SurveyMasterDTO saveUpdateFreezeSurveyYear(@Valid SurveyMasterDTO surveyMasterEO) {
		return surveyYearDao.updateFreezeSurveyYear(surveyMasterEO);
	}

}