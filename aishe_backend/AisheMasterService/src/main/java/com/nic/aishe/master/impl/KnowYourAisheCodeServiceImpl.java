package com.nic.aishe.master.impl;

import com.nic.aishe.master.dto.AisheDetailsDto;
import com.nic.aishe.master.dto.AisheDetailsResponseDto;
import com.nic.aishe.master.dto.CustomReponseStatus;
import com.nic.aishe.master.dto.ReturnResponse;
import com.nic.aishe.master.dto.ServiceResponse;
import com.nic.aishe.master.enums.EnumDetails.EligibilityStatus;
import com.nic.aishe.master.enums.EnumDetails.InstituitionCategory;
import com.nic.aishe.master.enums.StatusMaster;
import com.nic.aishe.master.service.KnowYourAisheCodeService;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class KnowYourAisheCodeServiceImpl implements KnowYourAisheCodeService {
    @Autowired
    private EntityManager session;
    private static final String CONSTANT_SUB_STRING = " AND ";
    private static final Logger logger = LoggerFactory.getLogger(KnowYourAisheCodeServiceImpl.class);


    @Override
    public ReturnResponse collegeAisheCode(String instituteType, String aisheId, Integer surveyYear) {
        AisheDetailsResponseDto aisheDetailsResponseDto = new AisheDetailsResponseDto();
        Query query = session.createNativeQuery(eligibleInstitution(instituteType, aisheId, surveyYear).toString());
        List<Object[]> eligibleObjects = query.getResultList();
        setResponse(aisheDetailsResponseDto, eligibleObjects, "Eligible", instituteType);
        if (eligibleObjects.isEmpty()) {
            List<Object[]> notEligibleObjects = session.createNativeQuery(notEligibleInstitution(instituteType, aisheId, surveyYear).toString()).getResultList();
            setResponse(aisheDetailsResponseDto, notEligibleObjects, "Not Eligible", instituteType);
            if (notEligibleObjects.isEmpty()) {
                return new ReturnResponse(HttpStatus.EXPECTATION_FAILED.value(), "AISHE Code is not valid", null);
            }
        }
        List<Object[]> activeAddress = session.createNativeQuery(activeInactiveAddressFromMaster(instituteType + "-" + aisheId, 1).toString()).getResultList();
        setMasterAddress(aisheDetailsResponseDto, activeAddress);
        if (activeAddress.isEmpty()) {
            List<Object[]> transactionAddress = session.createNativeQuery(selectAddressFromTransaction(instituteType, aisheId).toString()).getResultList();
            setTransactionAddress(aisheDetailsResponseDto, transactionAddress);
            if (transactionAddress.isEmpty()) {
                List<Object[]> inactiveAddress = session.createNativeQuery(activeInactiveAddressFromMaster(instituteType + "-" + aisheId, 0).toString()).getResultList();
                setMasterAddress(aisheDetailsResponseDto, inactiveAddress);
                if (inactiveAddress.isEmpty()) {
                    int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
                    List<Object[]> addressId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
                    if (!addressId.isEmpty()) {
                        Object[] ids = addressId.get(0);
                        List<Object[]> requestAddress = session.createNativeQuery(addressFromRequestDetail(Integer.valueOf(ids[2] != null ? ids[2].toString() : "0")).toString()).getResultList();
                        setRequestAddress(aisheDetailsResponseDto, requestAddress);
                    }
                }
            }
        }


        List<Object[]> nodalOfficer = session.createNativeQuery(activeInactiveNodalFromMaster(instituteType + "-" + aisheId, 1).toString()).getResultList();
        setNodalOfficer(aisheDetailsResponseDto, nodalOfficer);
        if (nodalOfficer.isEmpty()) {
            List<Object[]> nodalOfficerFromSurvey = session.createNativeQuery(officerDetails(instituteType, aisheId, "NO").toString()).getResultList();
            setNodalOfficer(aisheDetailsResponseDto, nodalOfficerFromSurvey);
            if (nodalOfficerFromSurvey.isEmpty()) {
                List<Object[]> nodalOfficerNE = session.createNativeQuery(activeInactiveNodalFromMaster(instituteType + "-" + aisheId, 0).toString()).getResultList();
                setNodalOfficer(aisheDetailsResponseDto, nodalOfficerNE);
                if (nodalOfficerNE.isEmpty()) {
                    int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
                    List<Object[]> noId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
                    if (!noId.isEmpty()) {
                        Object[] ids = noId.get(0);
                        List<Object[]> nodalIOfficers = session.createNativeQuery(fetchNodalOfficerDetails(Integer.valueOf(ids[1] != null ? ids[1].toString() : "0")).toString()).getResultList();
                        setNodalOfficer(aisheDetailsResponseDto, nodalIOfficers);
                    }
                }
            }
        }
        List<Object[]> InstFromSurvey = session.createNativeQuery(officerDetails(instituteType, aisheId, "IH").toString()).getResultList();
        setInstitutionHead(aisheDetailsResponseDto, InstFromSurvey);
        if (InstFromSurvey.isEmpty()) {
            int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
            List<Object[]> noId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
            if (!noId.isEmpty()) {
                Object[] ids = noId.get(0);
                List<Object[]> nodalIOfficer = session.createNativeQuery(fetchHeadOfficerDetails(Integer.valueOf(ids[3] != null ? ids[3].toString() : "0")).toString()).getResultList();
                setInstitutionHead(aisheDetailsResponseDto, nodalIOfficer);
            }
        }
        return new ReturnResponse(HttpStatus.OK.value(), "Success", aisheDetailsResponseDto);
    }

    @Override
    public ServiceResponse fetchAisheDetails(String stateCode, InstituitionCategory instituitionCategory, String surveyYear, EligibilityStatus status, String subCategory, String districtCode, String universityId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        LinkedHashMap<Object, Object> response = new LinkedHashMap<>();
        CustomReponseStatus customResponse = null;
        List<AisheDetailsDto> datalist = new ArrayList<AisheDetailsDto>();
        List<AisheDetailsDto> standloneDataList = null;
        List<AisheDetailsDto> universityDatalist = null;
        List<AisheDetailsDto> collegeDatalist = null;
        List<AisheDetailsDto> rndDataList = null;
        logger.info("Know Your Aishe Code Service Impl : Start fetchAisheDetails method ");
        if (surveyYear == null) {
            surveyYear = String.valueOf(maxSurveyYear());
        }
        if (instituitionCategory.getInstituitionCategory().equalsIgnoreCase(InstituitionCategory.ALL.getInstituitionCategory())) {
            InstituitionCategory instituitionCategoryUniversity = InstituitionCategory.U;
            InstituitionCategory instituitionCategoryCollege = InstituitionCategory.C;
            InstituitionCategory instituitionCategoryStandlone = InstituitionCategory.S;
            InstituitionCategory instituitionCategoryRnd = InstituitionCategory.R;

            universityDatalist = searchAisheDetails(stateCode, instituitionCategoryUniversity, surveyYear, status, subCategory, districtCode, universityId);
            if (universityDatalist != null && universityDatalist.size() > 0) {
                datalist.addAll(universityDatalist);
            }
            collegeDatalist = searchAisheDetails(stateCode, instituitionCategoryCollege, surveyYear, status, subCategory, districtCode, universityId);

            if (collegeDatalist != null && collegeDatalist.size() > 0) {
                datalist.addAll(collegeDatalist);
            }
            standloneDataList = searchAisheDetails(stateCode, instituitionCategoryStandlone, surveyYear, status, subCategory, districtCode, universityId);
            if (standloneDataList != null && standloneDataList.size() > 0) {
                datalist.addAll(standloneDataList);
            }
            rndDataList = searchAisheDetails(stateCode, instituitionCategoryRnd, surveyYear, status, subCategory, districtCode, universityId);
            if (rndDataList != null && rndDataList.size() > 0) {
                datalist.addAll(rndDataList);
            }


        } else {
            datalist = searchAisheDetails(stateCode, instituitionCategory, surveyYear, status, subCategory, districtCode, universityId);
        }

        customResponse = new CustomReponseStatus(StatusMaster.SUCCESS.getResponseId(),
                StatusMaster.SUCCESS.getResponseCode(), StatusMaster.SUCCESS.getResponseMessage(),
                StatusMaster.SUCCESS.getResponseDescription());
        response.put("customResponse", customResponse);

        response.put("data", (datalist == null || datalist.size() == 0) ? "No Data Found" : datalist);
        serviceResponse.setServiceResponse(response);
        logger.info("total data : " + datalist.size());
        logger.info("Know Your Aishe Code Service Impl :  End fetchAisheDetails method ");

        return serviceResponse;
    }


    public List<AisheDetailsDto> searchAisheDetails(String stateCode,
                                                    InstituitionCategory instituitionCategory, String surveyYear, EligibilityStatus status, String subCategory, String districtCode, String universityId) {

        List<AisheDetailsDto> aisheDetailsResponseDtoList = new ArrayList<AisheDetailsDto>();
        List<AisheDetailsDto> aisheDetailsResponseDtoListNonEligible = new ArrayList<AisheDetailsDto>();
        List<AisheDetailsDto> aisheDetailsResponseDtoListEligible = new ArrayList<AisheDetailsDto>();
        if (status.getStatusType().equalsIgnoreCase(EligibilityStatus.All.getStatusType())) {
            aisheDetailsResponseDtoListEligible = searchAisheDetailsNew(stateCode, instituitionCategory, surveyYear, EligibilityStatus.Eligible, subCategory, districtCode, universityId);
            if (aisheDetailsResponseDtoListEligible.size() > 0) {
                aisheDetailsResponseDtoList.addAll(aisheDetailsResponseDtoListEligible);
            }

            aisheDetailsResponseDtoListNonEligible = searchAiseDetailsNotEligible(stateCode, instituitionCategory, surveyYear, EligibilityStatus.Not_Eligible, subCategory, districtCode, universityId);
            if (null != aisheDetailsResponseDtoListNonEligible && aisheDetailsResponseDtoListNonEligible.size() > 0) {
                aisheDetailsResponseDtoList.addAll(aisheDetailsResponseDtoListNonEligible);
            }
        } else {
            if (status.getStatusType().equalsIgnoreCase(EligibilityStatus.Eligible.getStatusType())) {
                aisheDetailsResponseDtoList = searchAisheDetailsNew(stateCode, instituitionCategory, surveyYear, status,
                        subCategory, districtCode, universityId);
            } else {
                aisheDetailsResponseDtoList = searchAiseDetailsNotEligible(stateCode, instituitionCategory, surveyYear, EligibilityStatus.Not_Eligible, subCategory, districtCode, universityId);
            }
        }
        return aisheDetailsResponseDtoList;
    }


    public List<AisheDetailsDto> searchAisheDetailsNew(String stateCode,
                                                       InstituitionCategory instituitionCategory, String surveyYear, EligibilityStatus status, String subCategory, String districtCode, String universityId) {

        StringBuilder query = null;

        String universityTable = (status.getStatusType() == EligibilityStatus.Eligible.getStatusType()) ? "public.ref_university" : "public.deleted_university";
        String collegeTable = (status.getStatusType() == EligibilityStatus.Eligible.getStatusType()) ? "public.college" : "public.college_deaffiliation";
        String standloneTable = (status.getStatusType() == EligibilityStatus.Eligible.getStatusType()) ? "public.ref_standalone_institution" : "public.deleted_standalone_institution";

        if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("U")) {
            query = new StringBuilder(
                    "SELECT CONCAT('U-',clg.id) AS instituteAisheCode, clg.id AS aisheCode, 'University' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,rut.type AS instType,clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable  As status, mgt.management , clg.statecode as state_code,clg.district_code"
                            + " ,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                            "else CONCAT(u.address_line1||' ',u.address_line2) end as address, case\n" +
                            "when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                            "else u.pin_code end as pin_code	FROM " + universityTable
                            + " AS clg	Left JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN " + universityTable
                            + " ru ON (ru.id = clg.type_id) Left JOIN public.ref_university_type rut ON (rut.id = clg.type_id) "
                            + " Left JOIN  ref_institution_management as mgt on (mgt.id=clg.management_id)"
                            + " left join institute_detail insd on insd.aishe_code='U-'||clg.id\n" +
                            " left join university u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  university where id=clg.id and address_line1 is not null)) ");

        } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
            query = new StringBuilder(
                    "SELECT  CONCAT('C-',clg.id) AS instituteAisheCode,clg.id AS aisheCode ,'College' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv, "
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType, "
                            + "clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable As status , mgt.management ," +
                            " clg.state_code as state_code,clg.district_code "
                            + " ,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                            "else CONCAT(u.address_line1||' ',u.address_line2) end as address, case\n" +
                            "when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                            "else u.pin_code end as pin_code FROM " + collegeTable + " AS clg "
                            + "Left JOIN public.ref_state st ON (clg.state_code=st.st_code) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN  ref_institution_management as mgt on (mgt.id=clg.management_id) "
                            + "Left JOIN public.ref_university ru ON (ru.id = clg.university_id  AND ru.survey_year = clg.survey_year) "
                            + "Left JOIN public.ref_university_college_type clgType  on (clgType.id = clg.type_id) "
                            + " left join institute_detail insd on insd.aishe_code='C-'||clg.id\n" +
                            " left join college_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  college_institution where id=clg.id and address_line1 is not null)) ");
        } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("S")) {
            query = new StringBuilder(
                    "SELECT CONCAT('S-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'Standlone' AS institutionCategory ,clg.name AS institutionName, 'Null' AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType , clg.survey_year AS eligibleSurveyYear ,true as status, mgt.management , clg.statecode as state_code,clg.district_code "
                            + " ,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                            "else CONCAT(u.address_line1||' ',u.address_line2) end as address, case\n" +
                            "when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                            "else u.pin_code end as pin_code FROM  " + standloneTable
                            + " AS clg Left JOIN public.ref_state st ON (clg.statecode=st.st_code) "
                            + " Left JOIN  ref_institution_management as mgt on (mgt.id=clg.management_id) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) Left JOIN public.ref_State_body clgType  on (clgType.id = clg.statebodyid)"
                            + " left join institute_detail insd on insd.aishe_code='S-'||clg.id\n"
                            + " left join standalone_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  standalone_institution where id=clg.id and address_line1 is not null))");
        }


        if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("R")) {
            query = new StringBuilder(
                    "SELECT CONCAT('R-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'RnD ' AS institutionCategory ,clg.name AS institutionName, null AS affiliatingUniv,\n" +
                            "dist.name AS district, st.name AS  state ,clgType.name AS instType , clg.survey_year AS eligibleSurveyYear ,null AS status, null as management ,\n" +
                            "clg.state_code as state_code,clg.district_code ,\n" +
                            "case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2) else CONCAT(a.line1||' ',a.line2) end as address, \n" +
                            "case when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code else a.pin_code end as pin_code\n" +
                            "FROM  ref_rnd_institution AS clg Left JOIN public.ref_state st ON (clg.state_code=st.st_code)\n" +
                            "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) \n" +
                            "Left JOIN public.ref_ministry_onos clgType  on (clgType.id = clg.ministry_onos_id)\n" +
                            "left join institute_detail insd on insd.aishe_code='R-'||clg.id\n" +
                            "left join rnd_institution_request_details rird on rird.created_rnd_institution_id=clg.id\n" +
                            "left join request_details red on rird.request_id=red.id\n" +
                            "left join address a on a.id=red.address_id  ");

        }

        StringBuilder whereClause = new StringBuilder(" Where 1=1 ");
        StringBuilder orderClause = new StringBuilder(" ORDER BY instituteAisheCode  ");
        //  if (universityId == null) {
        if (stateCode != null && !stateCode.isEmpty() && !"ALL".equals(stateCode)) {
            if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C") || instituitionCategory.getInstituitionCategory().equalsIgnoreCase("R")) {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.state_code ='").append(stateCode).append("'");
            } else {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.statecode ='").append(stateCode).append("'");
            }
        }
        if (districtCode != null && !districtCode.isEmpty() && !"ALL".equals(districtCode)) {
            whereClause.append(CONSTANT_SUB_STRING).append(" clg.district_code ='").append(districtCode).append("'");
        }
        // }
        if (universityId != null) {
            whereClause.append(CONSTANT_SUB_STRING).append(" clg.university_id ='").append(universityId).append("'");
        }
        if (surveyYear != null && !instituitionCategory.getInstituitionCategory().equals("R")) {
            whereClause.append(CONSTANT_SUB_STRING).append(" clg.survey_year ='").append(surveyYear).append("'");
        }
        if (subCategory != null) {
            if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("S")) {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.statebodyid ='").append(subCategory).append("'");
            } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("R")) {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.ministry_onos_id ='").append(subCategory).append("'");
            } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.university_id in (select ru.id from ref_university ru where type_id ='" + subCategory + "' and survey_year in\r\n"
                        + "(select max(survey_year) from ref_university) )");
            } else {
                whereClause.append(CONSTANT_SUB_STRING).append(" clg.type_id ='").append(subCategory).append("'");
            }
        }


        query.append(whereClause).append(orderClause);

        logger.info("Query: " + query);

        List<Object[]> resultList = session.createNativeQuery(query.toString()).getResultList();

        return basicEligible(resultList, status);
    }


    public List<AisheDetailsDto> searchAiseDetailsNotEligible(String stateCode,
                                                              InstituitionCategory instituitionCategory, String surveyYear, EligibilityStatus status, String subCategory, String districtCode, String universityId) {

        StringBuilder query = null;
        StringBuilder secondQuery = null;
        StringBuilder whereClause2 = null;
        if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("U")) {
           /* query = new StringBuilder(
                    "(SELECT Distinct CONCAT('U-',clg.id) AS instituteAisheCode, clg.id AS aisheCode, 'University' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,\n" +
                            "dist.name AS district, st.name AS  state ,rut.type AS instType,clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable  As status \n" +
                            "FROM public.deleted_university AS clg\tLeft JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) \n" +
                            "Left JOIN public.deleted_university ru ON (ru.id = clg.type_id) Left JOIN public.ref_university_type rut ON (rut.id = clg.type_id) ");

            secondQuery = new StringBuilder("except\n" +
                    "(SELECT CONCAT('U-',clg.id) AS instituteAisheCode, clg.id AS aisheCode, 'University' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,\n" +
                    "dist.name AS district, st.name AS  state ,rut.type AS instType,clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable  As status \n" +
                    "FROM public.ref_university AS clg\tLeft JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) \n" +
                    "Left JOIN public.ref_university ru ON (ru.id = clg.type_id) Left JOIN public.ref_university_type rut ON (rut.id = clg.type_id)  ");
*/
        } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
            query = new StringBuilder("( SELECT Distinct  CONCAT('C-',clg.id) AS instituteAisheCode,clg.id AS aisheCode ,'College' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,\n" +
                    "dist.name AS district, st.name AS  state ,clgType.type AS instType, \n" +
                    "clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable As status,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                    "else CONCAT(u.address_line1||' ',u.address_line2) end as address,\n" +
                    "case when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                    "else u.pin_code end as pin_code FROM public.college_deaffiliation AS clg\n" +
                    "Left JOIN public.ref_state st ON (clg.state_code=st.st_code)Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) \n" +
                    "Left JOIN public.ref_university ru ON (ru.id = clg.university_id  AND ru.survey_year = clg.survey_year)\n" +
                    "Left JOIN public.ref_university_college_type clgType  on (clgType.id = clg.type_id) left join institute_detail insd on insd.aishe_code='C-'||clg.id "
                    + " left join college_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  college_institution where id=clg.id and address_line1 is not null)) ");

            whereClause2 = new StringBuilder(" and clg.survey_year=(select max(cd.survey_year) from college_deaffiliation cd where id=clg.id)  ");

            secondQuery = new StringBuilder("except \n" +
                    "(SELECT  CONCAT('C-',clg.id) AS instituteAisheCode,clg.id AS aisheCode ,'College' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,\n" +
                    "dist.name AS district, st.name AS  state ,clgType.type AS instType, \n" +
                    "clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable As status,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                    "else CONCAT(u.address_line1||' ',u.address_line2) end as address,\n" +
                    "case when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                    "else u.pin_code end as pin_code FROM public.college AS clg\n" +
                    "Left JOIN public.ref_state st ON (clg.state_code=st.st_code)Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) \n" +
                    "Left JOIN public.ref_university ru ON (ru.id = clg.university_id  AND ru.survey_year = clg.survey_year)\n" +
                    "Left JOIN public.ref_university_college_type clgType  on (clgType.id = clg.type_id) left join institute_detail insd on insd.aishe_code='C-'||clg.id " +
                    "left join college_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  college_institution where id=clg.id and address_line1 is not null)) ");

        } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("S")) {
            query = new StringBuilder(
                    "(SELECT Distinct CONCAT('S-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'Standlone' AS institutionCategory ,clg.name AS institutionName, 'Null' AS affiliatingUniv,\n" +
                            "dist.name AS district, st.name AS  state ,clgType.type AS instType, clg.survey_year  AS eligibleSurveyYear," +
                            "case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                            "else CONCAT(u.address_line1||' ',u.address_line2) end as address,\n" +
                            "case when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                            "else u.pin_code end as pin_code \n" +
                            "FROM  public.deleted_standalone_institution AS clg \n" +
                            "Left JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public" +
                            ".ref_district dist ON (clg.district_code=dist.dist_code) Left JOIN public.ref_State_body clgType  on (clgType.id = clg.statebodyid) "
                            + "left join institute_detail insd on insd.aishe_code='S-'||clg.id " +
                            "left join standalone_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  standalone_institution where id=clg.id and address_line1 is not null)) ");

            whereClause2 = new StringBuilder(" and clg.survey_year=(select max(cd.survey_year) from deleted_standalone_institution cd where id=clg.id)  ");

            secondQuery = new StringBuilder("except\n" +
                    "(SELECT CONCAT('S-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'Standlone' AS institutionCategory ,clg.name AS institutionName, 'Null' AS affiliatingUniv,\n" +
                    "dist.name AS district, st.name AS  state ,clgType.type AS instType, clg.survey_year AS eligibleSurveyYear,case when CONCAT(insd.address_line1,insd.address_line2)!='' then CONCAT(insd.address_line1||' ',insd.address_line2)\n" +
                    "else CONCAT(u.address_line1||' ',u.address_line2) end as address,\n" +
                    "case when CONCAT(insd.address_line1,insd.address_line2)!='' then insd.pin_code\n" +
                    "else u.pin_code end as pin_code \n" +
                    "FROM  public.ref_standalone_institution AS clg \n" +
                    "Left JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist " +
                    "ON (clg.district_code=dist.dist_code) Left JOIN public.ref_State_body clgType  on (clgType.id = clg.statebodyid) "
                    + "left join institute_detail insd on insd.aishe_code='S-'||clg.id " +
                    "left join standalone_institution u on (u.id=clg.id and u.survey_year=(select max(survey_year) from  standalone_institution where id=clg.id and address_line1 is not null)) "
            );
        }

        StringBuilder whereClause = new StringBuilder(" Where 1=1 ");
       /* StringBuilder orderClause = new StringBuilder(" ORDER BY instituteAisheCode, eligibleSurveyYear  ");
        StringBuilder limitClause = new StringBuilder(" limit 1  ");*/
        whereClause.append(whereClause2);
        //  if (universityId == null) {
        if (stateCode != null) {
            if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.state_code ='").append(stateCode).append("'");
            } else {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.statecode ='").append(stateCode).append("'");
            }
        }
        if (districtCode != null) {
            whereClause.append(CONSTANT_SUB_STRING).append("clg.district_code ='").append(districtCode).append("'");
        }
        // }
        if (universityId != null) {
            whereClause.append(CONSTANT_SUB_STRING).append("clg.university_id ='").append(universityId).append("'");
        }

        if (subCategory != null) {
            if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("S")) {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.statebodyid ='").append(subCategory).append("'");
            } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.university_id in (select ru.id from ref_university ru where type_id ='" + subCategory + "' and survey_year in\r\n"
                        + "(select max(survey_year) from ref_university) )");
            } else {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.type_id ='").append(subCategory).append("'");
            }
        }
        if (query != null) {
            query.append(whereClause);

            if (surveyYear != null) {
                whereClause.append(CONSTANT_SUB_STRING).append("clg.survey_year ='").append(surveyYear).append("'");
            }

            secondQuery.append(whereClause);

            logger.info("Query: " + query.append(")").append(secondQuery).append(")"));

            List<Object[]> resultList = session.createNativeQuery(query.toString()).getResultList();
            return basicNotEligible(resultList, status, instituitionCategory);
        }
        return Collections.EMPTY_LIST;
    }

    private List<AisheDetailsDto> basicNotEligible(List<Object[]> resultList, EligibilityStatus status, InstituitionCategory instituitionCategory) {
        List<AisheDetailsDto> aisheDetailsResponseDtos = new ArrayList<>();
        if (!resultList.isEmpty()) {
            for (Object[] objects : resultList) {
                AisheDetailsDto dto = new AisheDetailsDto();
                if (null != objects[0])
                    dto.setAishecode(objects[0].toString());
                if (null != objects[5])
                    dto.setDistrict(objects[5].toString());
                if (null != objects[2])
                    dto.setInstitutionCategory(objects[2].toString());
                if (null != objects[8]) {
                    dto.setEligibleSurveyYear(objects[8].toString());
                    dto.setSourceOfData(objects[8].toString());
                }
                if (null != objects[3])
                    dto.setInstitutionName(objects[3].toString());
                if (null != objects[6])
                    dto.setState(objects[6].toString());
                String sts = (status.getStatusType() == EligibilityStatus.Eligible.getStatusType()) ? "Eligible" : "Not Eligible";
                dto.setStatus(sts);
                if (null != objects[7])
                    dto.setInstType(objects[7].toString());
                if (null != objects[4])
                    dto.setAffiliatingUniv(objects[4].toString());
                if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("S")) {
                    if (null != objects[9] && !String.valueOf(objects[9]).isEmpty())
                        dto.setAddress(objects[9].toString());
                    if (null != objects[10])
                        dto.setPinCode(objects[10].toString());
                } else if (instituitionCategory != null && instituitionCategory.getInstituitionCategory().equalsIgnoreCase("C")) {
                    if (null != objects[10] && !String.valueOf(objects[10]).isEmpty())
                        dto.setAddress(objects[10].toString());
                    if (null != objects[11])
                        dto.setPinCode(objects[11].toString());
                }
                aisheDetailsResponseDtos.add(dto);
            }
        }
        return aisheDetailsResponseDtos;
    }

    private List<AisheDetailsDto> basicEligible(List<Object[]> resultList, EligibilityStatus status) {
        List<AisheDetailsDto> aisheDetailsResponseDtos = new ArrayList<>();
        if (!resultList.isEmpty()) {
            for (Object[] objects : resultList) {
                AisheDetailsDto dto = new AisheDetailsDto();
                if (null != objects[0])
                    dto.setAishecode(objects[0].toString());
                if (null != objects[5])
                    dto.setDistrict(objects[5].toString());
                if (null != objects[2])
                    dto.setInstitutionCategory(objects[2].toString());
                if (null != objects[8]) {
                    dto.setEligibleSurveyYear(objects[8].toString());
                    dto.setSourceOfData(objects[8].toString());
                }
                if (null != objects[3])
                    dto.setInstitutionName(objects[3].toString());
                if (null != objects[10])
                    dto.setMgmtBody(objects[10].toString());
                if (null != objects[6])
                    dto.setState(objects[6].toString());
                String sts = (status.getStatusType() == EligibilityStatus.Eligible.getStatusType()) ? "Eligible" : "Not Eligible";
                dto.setStatus(sts);
                if (null != objects[7])
                    dto.setInstType(objects[7].toString());
                if (null != objects[4])
                    dto.setAffiliatingUniv(objects[4].toString());
                if (null != objects[13] && !String.valueOf(objects[13]).isEmpty())
                    dto.setAddress(objects[13].toString());
                if (null != objects[14])
                    dto.setPinCode(objects[14].toString());
                aisheDetailsResponseDtos.add(dto);
            }
        }
        return aisheDetailsResponseDtos;
    }

    public void fetchAddressDetails(String instituteType, String aisheId, AisheDetailsResponseDto aisheDetailsResponseDto) {
        Session session = null;
        try {

            List<Object[]> activeAddress = session.createNativeQuery(activeInactiveAddressFromMaster(instituteType + "-" + aisheId, 1).toString()).getResultList();
            setMasterAddress(aisheDetailsResponseDto, activeAddress);
            if (activeAddress.isEmpty()) {
                List<Object[]> transactionAddress = session.createNativeQuery(selectAddressFromTransaction(instituteType, aisheId).toString()).getResultList();
                setTransactionAddress(aisheDetailsResponseDto, transactionAddress);
                if (transactionAddress.isEmpty()) {
                    List<Object[]> inactiveAddress = session.createNativeQuery(activeInactiveAddressFromMaster(instituteType + "-" + aisheId, 0).toString()).getResultList();
                    setMasterAddress(aisheDetailsResponseDto, inactiveAddress);
                    if (inactiveAddress.isEmpty()) {
                        int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
                        List<Object[]> addressId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
                        if (!addressId.isEmpty()) {
                            Object[] ids = addressId.get(0);
                            List<Object[]> requestAddress = session.createNativeQuery(addressFromRequestDetail(Integer.valueOf(ids[2] != null ? ids[2].toString() : "0")).toString()).getResultList();
                            setRequestAddress(aisheDetailsResponseDto, requestAddress);
                        }
                    }
                }
            }


            List<Object[]> nodalOfficer = session.createNativeQuery(activeInactiveNodalFromMaster(instituteType + "-" + aisheId, 1).toString()).getResultList();
            setNodalOfficer(aisheDetailsResponseDto, nodalOfficer);
            if (nodalOfficer.isEmpty()) {
                List<Object[]> nodalOfficerFromSurvey = session.createNativeQuery(officerDetails(instituteType, aisheId, "NO").toString()).getResultList();
                setNodalOfficer(aisheDetailsResponseDto, nodalOfficerFromSurvey);
                if (nodalOfficerFromSurvey.isEmpty()) {
                    List<Object[]> nodalOfficerNE = session.createNativeQuery(activeInactiveNodalFromMaster(instituteType + "-" + aisheId, 0).toString()).getResultList();
                    setNodalOfficer(aisheDetailsResponseDto, nodalOfficerNE);
                    if (nodalOfficerNE.isEmpty()) {
                        int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
                        List<Object[]> noId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
                        if (!noId.isEmpty()) {
                            Object[] ids = noId.get(0);
                            List<Object[]> nodalIOfficers = session.createNativeQuery(fetchNodalOfficerDetails(Integer.valueOf(ids[1] != null ? ids[1].toString() : "0")).toString()).getResultList();
                            setNodalOfficer(aisheDetailsResponseDto, nodalIOfficers);
                        }
                    }
                }
            }
            List<Object[]> InstFromSurvey = session.createNativeQuery(officerDetails(instituteType, aisheId, "IH").toString()).getResultList();
            setInstitutionHead(aisheDetailsResponseDto, InstFromSurvey);
            if (InstFromSurvey.isEmpty()) {
                int requestId = fetchRequestIdByAisheCode(instituteType, aisheId);
                List<Object[]> noId = session.createNativeQuery(fetchHeadIdAddressByRequestId(requestId).toString()).getResultList();
                if (!noId.isEmpty()) {
                    Object[] ids = noId.get(0);
                    List<Object[]> nodalIOfficer = session.createNativeQuery(fetchHeadOfficerDetails(Integer.valueOf(ids[3] != null ? ids[3].toString() : "0")).toString()).getResultList();
                    setInstitutionHead(aisheDetailsResponseDto, nodalIOfficer);
                }
            }
        } finally {

        }

    }

    public Integer maxSurveyYear() {
        return (Integer) session.createNativeQuery("select MAX(survey_year) from survey_master").getSingleResult();
    }

    private void setNodalOfficer(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> address) {
        if (!address.isEmpty()) {
            for (Object[] objects : address) {
                if (null != objects[0])
                    aisheDetailsResponseDto.setOfficerName(objects[0].toString());
                if (null != objects[1])
                    aisheDetailsResponseDto.setEmail(objects[1].toString());
                if (null != objects[2])
                    aisheDetailsResponseDto.setMobile(objects[2].toString());
                if (null != objects[3])
                    aisheDetailsResponseDto.setLandline(objects[3].toString());
                if (null != objects[4])
                    aisheDetailsResponseDto.setDesignation(objects[4].toString());
            }
        }
    }

    private void setInstitutionHead(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> address) {
        if (!address.isEmpty()) {
            for (Object[] objects : address) {
                if (null != objects[0])
                    aisheDetailsResponseDto.setHeadName(objects[0].toString());
                if (null != objects[1])
                    aisheDetailsResponseDto.setHeadEmail(objects[1].toString());
                if (null != objects[2])
                    aisheDetailsResponseDto.setHeadMobile(objects[2].toString());
                if (null != objects[3])
                    aisheDetailsResponseDto.setHeadTelephone(objects[3].toString());
                if (null != objects[4])
                    aisheDetailsResponseDto.setHeadDesignation(objects[4].toString());
            }
        }
    }

    private void setRequestAddress(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> address) {
        if (!address.isEmpty()) {
            for (Object[] objects : address) {
                if (null != objects[1])
                    aisheDetailsResponseDto.setAddressLine1(objects[1].toString());
                if (null != objects[2])
                    aisheDetailsResponseDto.setAddressLine2(objects[2].toString());
              /*  if (null != objects[4])
                    aisheDetailsResponseDto.setDistrict(objects[4].toString());
                if (null != objects[5])
                    aisheDetailsResponseDto.setState(objects[5].toString());
            */
            }
        }
    }

    private void setTransactionAddress(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> address) {
        if (!address.isEmpty()) {
            for (Object[] objects : address) {
                if (null != objects[0])
                    aisheDetailsResponseDto.setAddressLine1(objects[0].toString());
                if (null != objects[1])
                    aisheDetailsResponseDto.setAddressLine2(objects[1].toString());
                if (null != objects[2])
                    aisheDetailsResponseDto.setWebsite(objects[2].toString());
                if (null != objects[3])
                    aisheDetailsResponseDto.setSourceOfData(objects[3].toString());
               /* if (null != objects[4])
                    aisheDetailsResponseDto.setDistrict(objects[4].toString());
                if (null != objects[5])
                    aisheDetailsResponseDto.setState(objects[5].toString());
*/

            }
        }
    }

    private void setMasterAddress(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> address) {
        if (!address.isEmpty()) {
            for (Object[] objects : address) {
                if (null != objects[0])
                    aisheDetailsResponseDto.setAddressLine1(objects[0].toString());
                if (null != objects[1])
                    aisheDetailsResponseDto.setAddressLine2(objects[1].toString());
                /*if (null != objects[5])
                    aisheDetailsResponseDto.setDistrict(objects[5].toString());
                if (null != objects[6])
                    aisheDetailsResponseDto.setState(objects[6].toString());
*/

            }
        }
    }

    private void setResponse(AisheDetailsResponseDto aisheDetailsResponseDto, List<Object[]> eligibleObjects,
                             String status, String institutionType) {
        if (!eligibleObjects.isEmpty()) {
            for (Object[] objects : eligibleObjects) {
                if (null != objects[0])
                    aisheDetailsResponseDto.setAishecode(objects[0].toString());
                if (null != objects[2])
                    aisheDetailsResponseDto.setInstitutionCategory(objects[2].toString());
                if (null != objects[3])
                    aisheDetailsResponseDto.setInstitutionName(objects[3].toString());
                if (null != objects[4])
                    aisheDetailsResponseDto.setAffiliatingUniv(objects[4].toString());
                if (null != objects[5])
                    aisheDetailsResponseDto.setDistrict(objects[5].toString());
                if (null != objects[6])
                    aisheDetailsResponseDto.setState(objects[6].toString());
                if (null != objects[7])
                    aisheDetailsResponseDto.setInstType(objects[7].toString());
                if (null != objects[8])
                    aisheDetailsResponseDto.setEligibleSurveyYear(objects[8].toString());
                aisheDetailsResponseDto.setStatus(status);
                if (status.equals("Eligible")) {
                    if (!institutionType.equals("S")) {
                        if (null != objects[10])
                            aisheDetailsResponseDto.setMgmtBody(objects[10].toString());
                    } else {
                        if (null != objects[9])
                            aisheDetailsResponseDto.setMgmtBody(objects[9].toString());
                    }
                }
            }
        }
    }

    public StringBuilder addressFromRequestDetail(Integer addressId) {
        return new StringBuilder("select id as id,line1 as addline1,line2 as addline2,city as city,state_code as stateCode, district_code as districtCode ,pin_code as pincode from address WHERE id = '" + addressId + "' ");
    }


    public StringBuilder fetchNodalOfficerDetails(Integer createdBy) {
        return new StringBuilder("select name as name,email_id as email,mobile as mobile, landline as telephone,designation as designation from person_details WHERE id = '" + createdBy + "' ");
    }

    public StringBuilder fetchHeadOfficerDetails(Integer institutionHeadId) {
        return new StringBuilder("select name as name,email_id as email,mobile as mobile, landline as telephone,designation as designation from person_details WHERE id = '" + institutionHeadId + "' ");
    }

    public StringBuilder fetchHeadIdAddressByRequestId(Integer requestId) {
        return new StringBuilder(
                "select id as requestId,created_by as nodalOfficer,address_id as address,institutional_head_details as headId from request_details WHERE id = '" + requestId + "' ");
    }

    public Integer fetchRequestIdByAisheCode(String aisheType, String aisheCode) {
        StringBuilder query = null;
        if (aisheType.equalsIgnoreCase("S")) {
            query = new StringBuilder("select request_id from standalone_institution_request_details where created_standalone_institution_id = '" + aisheCode + "' ");
        }
        if (aisheType.equalsIgnoreCase("C")) {
            query = new StringBuilder("select request_id from college_institution_request_details where created_college_institution_id = '" + aisheCode + "' ");
        }
        try {
            return (Integer) session.createNativeQuery(query.toString()).getSingleResult();
        } catch (Exception ex) {
            logger.info("fetchRequestIdByAisheCode method error occurred  " + ex.getMessage());
        } finally {
        }
        return 0;
    }

    private StringBuilder eligibleInstitution(String aisheType, String aisheCodes, Integer surveyYear) {
        StringBuilder query = null;
        if (aisheType.equalsIgnoreCase("U")) {
            query = new StringBuilder(
                    "SELECT CONCAT('U-',clg.id) AS instituteAisheCode, clg.id AS aisheCode, 'University' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,rut.type AS instType,clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable  As status , clg.management_id as  managementName"
                            + "	FROM public.ref_university AS clg Left JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN public.ref_university ru ON (ru.id = clg.type_id) Left JOIN public.ref_university_type rut ON (rut.id = clg.type_id) ");
        } else if (aisheType.equalsIgnoreCase("C")) {
            query = new StringBuilder(
                    "SELECT CONCAT('C-',clg.id) AS instituteAisheCode,clg.id AS aisheCode ,'College' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv, "
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType, "
                            + "clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable As status , mgt.management as  managementName "
                            + "FROM public.college AS clg "
                            + "Left JOIN public.ref_state st ON (clg.state_code=st.st_code) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN public.ref_university ru ON (ru.id = clg.university_id AND ru.survey_year = clg.survey_year) "
                            + "Left JOIN public.ref_university_college_type clgType  on (clgType.id = clg.type_id) "
                            + "Left JOIN public.ref_institution_management mgt on (mgt.id=clg.management_id ) ");
        } else if (aisheType.equalsIgnoreCase("S")) {
            query = new StringBuilder(
                    "SELECT CONCAT('S-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'Standlone' AS institutionCategory ,clg.name AS institutionName, 'Null' AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType, clg.survey_year AS eligibleSurveyYear, mgt.management as  managementName "
                            + "FROM  public.ref_standalone_institution AS clg Left JOIN public.ref_state st ON (clg.statecode=st.st_code) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code)"
                            + "Left JOIN public.ref_institution_management mgt on (mgt.id=clg.management_id ) Left JOIN public.ref_State_body clgType  on (clgType.id = clg.statebodyid)  ");
        }

        StringBuilder whereClause = new StringBuilder(" Where 1=1 ");
        StringBuilder orderClause = new StringBuilder(" ORDER BY clg.survey_year DESC  ");
        StringBuilder limitClause = new StringBuilder(" limit 1  ");

        if (aisheCodes != null && aisheCodes.length() != 0) {
            whereClause.append(CONSTANT_SUB_STRING).append("clg.id ='").append(aisheCodes).append("'");
        }
        if (surveyYear != null) {
            whereClause.append(CONSTANT_SUB_STRING).append("clg.survey_year ='").append(surveyYear).append("'");
        }
        query.append(whereClause).append(orderClause);
        query.append(limitClause);
        return query;
    }


    public StringBuilder notEligibleInstitution(String aisheType, String aisheCodes, Integer surveyYear) {
        StringBuilder query = null;
        StringBuilder secondQuery = null;
        if (aisheType.equalsIgnoreCase("U")) {
            query = new StringBuilder(
                    "SELECT CONCAT('U-',clg.id) AS instituteAisheCode, clg.id AS aisheCode, 'University' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,rut.type AS instType,clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable  As status "
                            + "	FROM public.deleted_university AS clg Left JOIN public.ref_state st ON (clg.statecode=st.st_code) Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN public.deleted_university ru ON (ru.id = clg.type_id) Left JOIN public.ref_university_type rut ON (rut.id = clg.type_id) ");
            secondQuery = new StringBuilder("(select max(survey_year)  from deleted_university where id = '" + aisheCodes + "')");
        } else if (aisheType.equalsIgnoreCase("C")) {
            query = new StringBuilder(
                    "SELECT CONCAT('C-',clg.id) AS instituteAisheCode,clg.id AS aisheCode ,'College' AS institutionCategory ,clg.name AS institutionName, ru.name AS affiliatingUniv, "
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType, "
                            + "clg.survey_year AS eligibleSurveyYear, clg.is_dcf_applicable As status "
                            + "FROM public.college_deaffiliation AS clg "
                            + "Left JOIN public.ref_state st ON (clg.state_code=st.st_code) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) "
                            + "Left JOIN public.deleted_university ru ON (ru.id = clg.university_id AND ru.survey_year = clg.survey_year) "
                            + "Left JOIN public.ref_university_college_type clgType  on (clgType.id = clg.type_id) ");
            secondQuery = new StringBuilder("(select max(survey_year)  from college_deaffiliation where id = '" + aisheCodes + "')");
        } else if (aisheType.equalsIgnoreCase("S")) {
            query = new StringBuilder(
                    "SELECT CONCAT('S-',clg.id) AS instituteAisheCode,clg.id AS aisheCode, 'Standlone' AS institutionCategory ,clg.name AS institutionName, 'Null' AS affiliatingUniv,"
                            + "dist.name AS district, st.name AS  state ,clgType.type AS instType, clg.survey_year AS eligibleSurveyYear "
                            + "FROM  public.deleted_standalone_institution AS clg Left JOIN public.ref_state st ON (clg.statecode=st.st_code) "
                            + "Left JOIN public.ref_district dist ON (clg.district_code=dist.dist_code) Left JOIN public.ref_State_body clgType  on (clgType.id = clg.statebodyid) ");
            secondQuery = new StringBuilder("(select max(survey_year)  from deleted_standalone_institution where id = '" + aisheCodes + "')");
        }

        StringBuilder whereClause = new StringBuilder(" Where 1=1 ");
        StringBuilder orderClause = new StringBuilder(" ORDER BY clg.survey_year DESC  ");
        StringBuilder limitClause = new StringBuilder(" limit 1  ");
        if (aisheCodes != null && aisheCodes.length() != 0)
            whereClause.append(CONSTANT_SUB_STRING).append("clg.id ='").append(aisheCodes).append("'");
        whereClause.append(CONSTANT_SUB_STRING).append("clg.survey_year =").append(secondQuery);
        query.append(whereClause).append(orderClause);
        query.append(limitClause);
        return query;
    }

    public StringBuilder activeInactiveAddressFromMaster(String aisheCode, Integer status) {
        return new StringBuilder("select address_line1 as addressLine1,address_line2 as addressLine2,email_id as email,phone_mobile as mobile,phone_landline as landline,dist.name AS district, st.name AS  state   from user_master um\n" +
                "Left JOIN public.ref_state st ON (um.address_state_code=st.st_code) \n" +
                "Left JOIN public.ref_district dist ON (um.address_district_code=dist.dist_code) where um.aishe_code = '" + aisheCode + "' and um.is_approved= '" + status + "'");
    }

    public StringBuilder selectAddressFromTransaction(String aisheType, String aisheCodes) {
        StringBuilder query = null;
        StringBuilder secondQuery = null;
        if (aisheType.equalsIgnoreCase("U")) {
            query = new StringBuilder(
                    "select address.address_line1,address.address_line2, address.website,survey_year as sourceOfData,dist.name AS district, st.name AS  state  from university address\n" +
                            "Left JOIN public.ref_state st ON (address.state_code=st.st_code) Left JOIN public.ref_district dist ON (address.district_code=dist.dist_code) ");
            secondQuery = new StringBuilder("(select max(survey_year) from university where id = '" + aisheCodes + "')");
        } else if (aisheType.equalsIgnoreCase("C")) {
            query = new StringBuilder(
                    "select address.address_line1,address.address_line2, address.website,survey_year as sourceOfData,dist.name AS district, st.name AS  state  from college_institution address\n" +
                            "Left JOIN public.ref_state st ON (address.state_code=st.st_code) Left JOIN public.ref_district dist ON (address.district_code=dist.dist_code)  ");
            secondQuery = new StringBuilder("(select max(survey_year)  from college_institution where id = '" + aisheCodes + "')");
        } else if (aisheType.equalsIgnoreCase("S")) {
            query = new StringBuilder(
                    "select address.address_line1,address.address_line2, address.website,survey_year as sourceOfData,dist.name AS district, st.name AS  state  from standalone_institution address\n" +
                            "Left JOIN public.ref_state st ON (address.state_code=st.st_code) Left JOIN public.ref_district dist ON (address.district_code=dist.dist_code) ");
            secondQuery = new StringBuilder("(select max(survey_year) from standalone_institution where id = '" + aisheCodes + "')");
        }

        StringBuilder whereClause = new StringBuilder(" Where 1=1 ");
        StringBuilder orderClause = new StringBuilder(" ORDER BY address.survey_year DESC  ");
        StringBuilder limitClause = new StringBuilder(" limit 1  ");
        whereClause.append(CONSTANT_SUB_STRING).append("address.survey_year =").append(secondQuery);
        query.append(whereClause).append(orderClause);
        query.append(limitClause);
        return query;
    }

    public StringBuilder activeInactiveNodalFromMaster(String aisheCode, Integer status) {
        return new StringBuilder("select first_name ||' '|| middle_name||' '||last_name as name,email_id ,phone_mobile,phone_landline,'Nodal Officer' as officer_designation  from user_master where aishe_code='" + aisheCode + "' and is_approved= '" + status + "'");
    }

    public StringBuilder officerDetails(String aisheType, String aisheCode, String officerType) {
        return new StringBuilder("select officer_name,officer_email,officer_mobile,officer_telephone, officer_designation from webdcf.person_details_survey where aishe_code='" + aisheCode + "' and inst_type= '" + aisheType + "' and survey_year=(select max(survey_year) from webdcf.person_details_survey where aishe_code='" + aisheCode + "' and inst_type= '" + aisheType + "' ) and officer_type='" + officerType + "'");
    }
}