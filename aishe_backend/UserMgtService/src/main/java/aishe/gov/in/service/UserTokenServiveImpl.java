package aishe.gov.in.service;

import aishe.gov.in.dao.UserTokenDao;
import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.masterseo.UserToken;
import aishe.gov.in.mastersvo.AisheCodeDetailsVo;
import aishe.gov.in.mastersvo.CollegeCountUniversityWise;
import aishe.gov.in.mastersvo.CreatedInstituteDetailsVo;
import aishe.gov.in.mastersvo.Message;
import aishe.gov.in.mastersvo.ProcessInfo;
import aishe.gov.in.mastersvo.ResponseVO;
import aishe.gov.in.mastersvo.UserDetailsVo;
import aishe.gov.in.mastersvo.UserTokenVO;
import aishe.gov.in.utility.CalculateTime;
import aishe.gov.in.utility.RandomToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserTokenServiveImpl implements UserTokenService {

    private static final Logger logger = LoggerFactory.getLogger(UserTokenServiveImpl.class);

    @Autowired
    UserTokenDao universityDao;


    @Override
    public ResponseVO refreshToken(String userId, String token) {
        String start_Time = CalculateTime.getCurrentLocalDateTimeStamp();
        Boolean tokenStatus = universityDao.validateToken(userId, token);
        UserTokenVO userTokenVO = new UserTokenVO();
        if (tokenStatus) {
            UserToken userToken2 = new UserToken();
            String generatedToken = RandomToken.generateRandomToken();
            UserToken userToken = universityDao.getUserToken(userId);
            userToken2.setUserId(userToken.getUserId());
            userToken2.setPassword(userToken.getPassword());
            userToken2.setGeneratedDateTime(Date.from(Instant.now()));//util date
            userToken2.setToken(generatedToken);
            Boolean id = universityDao.saveOrUpdate(userToken2);
            userTokenVO.setToken(generatedToken);
            userTokenVO.setUserId(userId);

        }
        String responseTime = CalculateTime.getCurrentLocalDateTimeStamp().toString();
        ResponseVO responseVO = new ResponseVO();
        List<Object> data = new ArrayList<Object>();
        Message message = new Message();
        message.setMsgCode("1000");
        message.setMsgText("success");
        ProcessInfo processInfo = new ProcessInfo();
        data.add(userTokenVO);
        responseVO.setApiId("U-12");
        responseVO.setApiName("UTIF");
        responseVO.setApiVersion("v1");
        responseVO.setDevelopedBy("Higher Education Division, NIC");
        responseVO.setMessage(message);
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("userId", userId);
        params.put("token", token);
        responseVO.setParams(params);
        processInfo.setRequestedTime(start_Time);
        processInfo.setResponseTime(responseTime);
        processInfo.setAuthenticationType("");
        processInfo.setResponseId("");
        processInfo.setRequestedBy("");
        responseVO.setProcessInfo(processInfo);
        responseVO.setData(data);
        return responseVO;
    }


    @Override
    public Boolean logOutWebDcf(String userId) {
        return universityDao.logOutWebDcf(userId);
    }

    @Override
    public List<UserDetailsVo> userDetails(String userId) {
        return universityDao.userDetailsmOU(userId);
    }

    @Override
    public List<UserDetailsVo> userDetailsaISHEcODE(String aisheCode) {
        return universityDao.userDetails(aisheCode);
    }

	@Override
	public AisheCodeDetailsVo userDetailsByAisheCodeOnCurrentSurveyYear(String instituteType, String instituteId,
			Integer currentSurveyYear) {
		 return universityDao.userDetailsByAisheCodeOnCurrentSurveyYear(instituteType,instituteId,currentSurveyYear);
	}


	@Override
	public List<CollegeCountUniversityWise> universityWiseCollege(Integer surveyYear) {
		 return universityDao.universityWiseCollege(surveyYear);
	}


	@Override
	public List<CreatedInstituteDetailsVo> createdInstituteDetailsOnBasisAisheCode(String instituteType,String instituteId) {
		 return universityDao.createdInstituteDetailsOnBasisAisheCode(instituteType,instituteId);
	}


	@Override
	public List<UserDetailsVo> userDetailsOnBasisAisheCodeAll(String aisheCode) {
		 return universityDao.userDetailsOnBasisAisheCodeAll(aisheCode);
	}


	@Override
	public List<InstituteDetailEO> getInstituteDetail(String token, String instituteType) {
		 return universityDao.getInstituteDetail(token,instituteType);
	}
}