package aishe.gov.in.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import aishe.gov.in.utility.CommanObjectOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import aishe.gov.in.masterseo.InstituteDetailEO;
import aishe.gov.in.mastersvo.AisheCodeDetailsVo;
import aishe.gov.in.mastersvo.BisagResponseVO;
import aishe.gov.in.mastersvo.CollegeCountUniversityWise;
import aishe.gov.in.mastersvo.CreatedInstituteDetailsVo;
import aishe.gov.in.mastersvo.ResponseVO;
import aishe.gov.in.mastersvo.TokenInfo;
import aishe.gov.in.mastersvo.UserDetailsVo;
import aishe.gov.in.security.UserInfo;
import aishe.gov.in.security.WithUser;
import aishe.gov.in.service.UserTokenService;
import aishe.gov.in.utility.ReturnResponse;

@RestController
public class TokenController {
    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
    @Autowired
    private UserTokenService universityService;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/refreshToken")
    public ResponseVO refreshToken(@RequestParam String userId, @RequestParam String token, @WithUser UserInfo userInfo) throws ParseException {
        logger.info("university controller : refreshToken method invoked : {}");
        ResponseVO responseVO = universityService.refreshToken(userId, token);
        return responseVO;
    }

    @GetMapping(value = "/logoutwebdcf")
    public ResponseEntity<ReturnResponse> logoutWebDcf(@RequestParam String userId, @WithUser UserInfo userInfo) throws ParseException {
        logger.info("university controller : refreshToken method invoked : {}");
        Boolean responseVO = universityService.logOutWebDcf(userId);
        if (responseVO) {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.OK.value(), "You Are Successfully Logout From Webdcf!!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ReturnResponse(HttpStatus.BAD_REQUEST.value(), "Request Cannot Be Processed. Please Try Again."), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/userdetails")
    public List<UserDetailsVo> userDetailsAisheByUserId(@RequestParam String userId, @WithUser UserInfo userInfo) throws ParseException {
        logger.info("university controller : userDetails method invoked : {}");
        List<UserDetailsVo> responseVO = universityService.userDetails(userId);
        return responseVO;
    }

    @GetMapping(value = "/userdetailsasaishecode")
    public List<UserDetailsVo> userDetailsOnBasisAisheCode(@RequestParam String aisheCode) throws ParseException {
        logger.info("university controller : userDetails method invoked : {}");
        List<UserDetailsVo> responseVO = universityService.userDetailsaISHEcODE(aisheCode);
        return responseVO;
    }
    
    @GetMapping(value = "/userdetailsasaishecodeall")
    public List<UserDetailsVo> userDetailsOnBasisAisheCodeAll(@RequestParam String aisheCode) throws ParseException {
        logger.info("university controller : userDetails method invoked : {}");
        List<UserDetailsVo> responseVO = universityService.userDetailsOnBasisAisheCodeAll(aisheCode);
        if(responseVO==null || responseVO.isEmpty()) {
        	responseVO = new ArrayList<UserDetailsVo>();
        }
        return responseVO;
    }

    @GetMapping(value = "/userdetailsmou")
    public List<UserDetailsVo> userDetails(@RequestParam String userId) throws ParseException {
        logger.info("university controller : userDetails method invoked : {}");
        List<UserDetailsVo> responseVO = universityService.userDetails(userId);
        return responseVO;
    }

	@GetMapping(value = "/bisag-token-verify-state-district-subdistrict")
	public TokenInfo getAccessToken(@RequestParam String instituteType) {
		try {
			///////////////////////////////get token data///////////////////////////////////////////
			String loginurl = "https://ministry.pmgatishakti.gov.in/lat-lon-api/auth/token";
			Map map = new HashMap<String, String>();
			map.put("username", "ministry");
			map.put("password", "%K=jz:^r~PK?zHb@");
			ResponseEntity<TokenInfo> response = restTemplate.postForEntity(loginurl, map, TokenInfo.class);
			String token = response.getBody().getToken();
			TokenInfo tokendata = new TokenInfo();
			tokendata.setToken(token);
			System.out.println(token);
			///////////////////////////////////////////////////
			
			
			
//			HttpHeaders headers = new HttpHeaders();
//			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//			headers.set("Authorization", "Bearer "+token);
            List<InstituteDetailEO> instituteList = universityService.getInstituteDetail(token,instituteType);
//			Map map1 = new HashMap<String, String>();
//			map1.put("lon", "72.12");
//			map1.put("lat", "23.214");
//			System.out.println("Bearer "+token);
//			HttpEntity<Map<String, String>> request = new HttpEntity<>(map1, headers);
//            String responseurl = "https://ministry.pmgatishakti.gov.in/lat-lon-api/";
//            ResponseEntity<BisagResponseVO> response1 =restTemplate.postForEntity(responseurl, request, BisagResponseVO.class);
//            BisagResponseVO responsedata = response1.getBody();
			return tokendata;
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return null;
	}
    
    @GetMapping(value = "/aishecodedetailscsy")
    public Map<String,AisheCodeDetailsVo> userDetailsByAisheCodeOnCurrentSurveyYear(@RequestParam String aisheCode,@RequestParam Integer currentSurveyYear,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
        String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        String instituteId = splitted[1];
        String instituteType = splitted[0];
        AisheCodeDetailsVo responseVO = universityService.userDetailsByAisheCodeOnCurrentSurveyYear(instituteType,instituteId,currentSurveyYear);
        Map<String,AisheCodeDetailsVo> details = new HashMap<String,AisheCodeDetailsVo>();
        details.put("Institute Details For CSY",responseVO);
        return details;
    }
    
    @GetMapping(value = "/collegecountuniversitywise")
	public List<CollegeCountUniversityWise> universityWiseCollege(
			@RequestParam(required = false) Integer surveyYear/* ,@WithUser UserInfo userInfo */) {
       // CommanObjectOperation.usernameValidate(userInfo);
        List<CollegeCountUniversityWise> responseVO = universityService.universityWiseCollege(surveyYear);
        return responseVO;
    }
    
    @GetMapping(value = "/createdinstitutedetailsaishecode")
    public List<CreatedInstituteDetailsVo> createdInstituteDetailsOnBasisAisheCode(@RequestParam String aisheCode,@WithUser UserInfo userInfo) {
        CommanObjectOperation.usernameValidate(userInfo);
    	String[] splitted = aisheCode.trim().split("\\s*-\\s*");
        String instituteId = splitted[1];
        String instituteType = splitted[0];
        logger.info("university controller : createdInstituteDetailsOnBasisAisheCode method invoked : {}");
        List<CreatedInstituteDetailsVo> responseVO = universityService.createdInstituteDetailsOnBasisAisheCode(instituteType,instituteId);
        return responseVO;
    }
}
