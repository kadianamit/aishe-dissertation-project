package aishe.gov.in.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aishe.gov.in.dao.CollegeAffiliationDeaffliationDao;
import aishe.gov.in.dao.UserActionLogDao;
import aishe.gov.in.masterseo.CollegeEO;
import aishe.gov.in.masterseo.Form_UploadBean;
import aishe.gov.in.masterseo.UniversityRef;
import aishe.gov.in.masterseo.UserActionLogEONew;
import aishe.gov.in.mastersvo.CollegeDeaffiliationAffiliationVO;
import aishe.gov.in.security.UserInfo;

@Service
public class CollegeAffiliationDeaffiliationServiceImpl implements CollegeAffiliationDeaffiliationService {
    @Autowired
    private CollegeAffiliationDeaffliationDao affiliationDeaffliationDao;
    @Autowired
    private UserActionLogDao userActionLogDao;

    @Override
    public CollegeEO getCollegeMaster(String collegeId, Integer surveyYear) {
        return affiliationDeaffliationDao.getCollegeMaster(collegeId, surveyYear);
    }

    @Override
    public UniversityRef getUniversityMaster(String universityId, Integer surveyYear) {
        return affiliationDeaffliationDao.getUniversityMaster(universityId, surveyYear);
    }

    @Override
    public Boolean saveUpdateCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO deaffiliationAffiliationVO, CollegeEO collegeEO, UniversityRef oldUniversityRef, UniversityRef newUnversityRef
    		, UserInfo userInfo) {
        Integer surveyyear=deaffiliationAffiliationVO.getSurveyYear();
        affiliationDeaffliationDao.saveCollegeAffiliationDeaffiliation(deaffiliationAffiliationVO, collegeEO, oldUniversityRef, newUnversityRef,userInfo);
       // Integer userLogMaxId = userActionLogDao.getMaxId();
        if(deaffiliationAffiliationVO.getReasonId()!=null && deaffiliationAffiliationVO.getReasonId()!=1) {
        	 UserActionLogEONew userActionLog = new UserActionLogEONew( userInfo.getUsername(), deaffiliationAffiliationVO.getCollegeAisheCode(), "C", 21, surveyyear, new Timestamp(new Date().getTime()), deaffiliationAffiliationVO.getIpAddress(), "college de-affiliated");
             userActionLogDao.saveUserActionLog(userActionLog);
        }
       
        
        affiliationDeaffliationDao.saveUpdateCollegeAffiliationDeaffiliation(deaffiliationAffiliationVO, collegeEO, newUnversityRef);
        List<CollegeEO> eoList = affiliationDeaffliationDao.getCollegeMasterList(deaffiliationAffiliationVO.getCollegeAisheCode(), deaffiliationAffiliationVO.getSurveyYear());
        if (eoList.size() > 0) {
            eoList.parallelStream().forEach(college -> {
                affiliationDeaffliationDao.saveUpdateCollegeAffiliationDeaffiliation(CollegeDeaffiliationAffiliationVO.bindSurveyYear(deaffiliationAffiliationVO, college.getUniversityPk().getSurveyYear()), college, newUnversityRef);
            });
        }
        UserActionLogEONew userActionLog2=null;
        if(deaffiliationAffiliationVO.getReasonId()!=null && deaffiliationAffiliationVO.getReasonId()!=1) {
        userActionLog2 = new UserActionLogEONew( userInfo.getUsername(), deaffiliationAffiliationVO.getCollegeAisheCode(), "C", 22, surveyyear, new Timestamp(new Date().getTime()), deaffiliationAffiliationVO.getIpAddress(), "college affiliated");
        userActionLogDao.saveUserActionLog(userActionLog2);
        }else {
        	userActionLog2 = new UserActionLogEONew( userInfo.getUsername(), deaffiliationAffiliationVO.getCollegeAisheCode(), "C", 47, surveyyear, new Timestamp(new Date().getTime()), deaffiliationAffiliationVO.getIpAddress(), "College Shifted from 'U-''"+oldUniversityRef.getId()+"' to '"+deaffiliationAffiliationVO.getUniversityAisheCode()+"'");
            userActionLogDao.saveUserActionLog(userActionLog2);
        }
        return true;
    }

	@Override
	public Form_UploadBean getFormUploadForCollege(String aisheCode, Integer surveyYear) {
		return affiliationDeaffliationDao.getFormUploadForCollege(aisheCode, surveyYear);
	}

	@Override
	public Form_UploadBean getFormUploadForStandalone(String aisheCode, Integer surveyYear) {
		return affiliationDeaffliationDao.getFormUploadForStandalone(aisheCode, surveyYear);
	}
}
