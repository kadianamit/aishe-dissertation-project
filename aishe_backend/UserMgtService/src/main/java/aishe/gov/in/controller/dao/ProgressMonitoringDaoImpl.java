package aishe.gov.in.dao;

import aishe.gov.in.enums.FormType;
import aishe.gov.in.enums.InstitutionType;
import aishe.gov.in.enums.ListType;
import aishe.gov.in.masterseo.RefStateBody;
import aishe.gov.in.masterseo.RefUniversityCollegeType;
import aishe.gov.in.masterseo.RefUniversityType;
import aishe.gov.in.mastersvo.FormUploadDateWiseDTO;
import aishe.gov.in.mastersvo.InstituteDetailDTO;
import aishe.gov.in.mastersvo.ProgressMoniteringDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringBasicDTO;
import aishe.gov.in.mastersvo.ProgressMonitoringDTO;
import aishe.gov.in.mastersvo.ProgressMontioringDetailDTO;
import aishe.gov.in.mastersvo.ProgressMontioringListDTO;
import aishe.gov.in.utility.CommanObjectOperation;
import aishe.gov.in.utility.DateUtils;
import aishe.gov.in.utility.QueryParameterResolver;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
@Slf4j
public class ProgressMonitoringDaoImpl implements ProgressMonitoringDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RefMasterDao refMasterDao;

    @Autowired
    private QueryParameterResolver parameterResolver;

    @Override
    public List<ProgressMoniteringDTO> getProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType, Integer roleId) {
        Session session = sessionFactory.openSession();
        List<RefUniversityType> universityTypes = null;
        List<RefUniversityCollegeType> collegeTypes = null;
        List<RefStateBody> stateBodies = null;
        List<ProgressMoniteringDTO> dtoList = new ArrayList<>();
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 4, 5, 18, 22, 8, 9, 10, 11));
        if (!list.contains(roleId)) {
            collegeTypes = refMasterDao.getRefUniversityCollegeType();
            if (null == universityId || universityId.equalsIgnoreCase("null")) {
                universityTypes = refMasterDao.getRefUniversityType();
                stateBodies = refMasterDao.getRefStateBody();
            }
        } else {
            switch (roleId) {
                case 3:
                case 8: {
                    stateBodies = refMasterDao.getRefStateBody(2, 7);
                    break;
                }
                case 4:
                case 9: {
                    stateBodies = refMasterDao.getRefStateBody(3, null);
                    break;
                }
                case 5:
                case 10: {
                    stateBodies = refMasterDao.getRefStateBody(4, null);
                    break;
                }
                case 11:
                case 18: {
                    stateBodies = refMasterDao.getRefStateBody(1, null);
                    break;
                }
                case 22: {
                    stateBodies = refMasterDao.getRefStateBody(6, 0);
                    break;
                }
            }
        }
        try {
            switch (institutionType.getType()) {
                case "C": {
                    if (null != collegeTypes)
                        for (RefUniversityCollegeType universityType : collegeTypes) {
                            dtoList.add(collegeObjectBind(session, universityType, surveyYear, stateCode, universityId, fromDate, toDate, null));
                        }
                    break;
                }
                case "U": {
                    if (null != universityTypes)
                        for (RefUniversityType universityType : universityTypes) {
                            dtoList.add(universityObjectBind(session, universityType, surveyYear, stateCode, fromDate, toDate));
                        }
                    break;

                }
                case "S": {
                    if (null != stateBodies)
                        for (RefStateBody universityType : stateBodies) {
                            dtoList.add(standaloneObjectBind(session, universityType, surveyYear, stateCode, fromDate, toDate));
                        }
                    break;
                }
                default: {
                    if (null != universityTypes)
                        for (RefUniversityType universityType : universityTypes) {
                            dtoList.add(universityObjectBind(session, universityType, surveyYear, stateCode, fromDate, toDate));
                        }
                    if (null != collegeTypes)
                        for (RefUniversityCollegeType universityType : collegeTypes) {
                            dtoList.add(collegeObjectBind(session, universityType, surveyYear, stateCode, universityId, fromDate, toDate, null));
                        }
                    if (null != stateBodies)
                        for (RefStateBody universityType : stateBodies) {
                            dtoList.add(standaloneObjectBind(session, universityType, surveyYear, stateCode, fromDate, toDate));
                        }
                    break;
                }
            }

            return dtoList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<InstituteDetailDTO> getInstituteDetailOfProgressMonitoring(Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, InstitutionType institutionType, String type, FormType formType/*, int page, int pageSize, String searchText*/) {

        Session session = sessionFactory.openSession();
        try {
            switch (institutionType.getType()) {
                case "C": {
                    if (null != type)
                        return collegeExpectedSubmittedBind(session, type, surveyYear, stateCode, universityId, fromDate, toDate, formType, institutionType/*, page, pageSize*/);
                }
                case "S": {
                    if (null != type)
                        return standaloneExpectedSubmittedBind(session, type, surveyYear, stateCode, null, fromDate, toDate, formType, institutionType/*, page, pageSize*/);
                }
                case "U": {
                    if (null != type)
                        return universityExpectedSubmittedBind(session, type, surveyYear, stateCode, null, fromDate, toDate, formType, institutionType/*, page, pageSize*/);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public ProgressMonitoringDTO getProgressMonitoringByBasicCount(Integer surveyYear, String fromDate, String toDate, InstitutionType institutionType) {
        Session session = sessionFactory.openSession();
        ProgressMonitoringDTO dto = new ProgressMonitoringDTO();
        try {
            switch (institutionType.getType()) {
                case "C": {
                    dto.setCollege(collegeObjectBind(session, surveyYear, fromDate, toDate));
                    break;
                }
                case "S": {
                    dto.setStandalone(standaloneObjectBind(session, surveyYear, fromDate, toDate));
                    break;
                }
                case "U": {
                    dto.setUniversity(universityObjectBind(session, surveyYear, fromDate, toDate));
                    break;
                }
                case "ALL": {
                    dto.setUniversity(universityObjectBind(session, surveyYear, fromDate, toDate));
                    dto.setCollege(collegeObjectBind(session, surveyYear, fromDate, toDate));
                    dto.setStandalone(standaloneObjectBind(session, surveyYear, fromDate, toDate));
                    return dto;
                }
            }
            return dto;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public List<ProgressMontioringDetailDTO> progressMonitoringStateWise(Integer surveyYear) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("WITH university_counts AS (SELECT STATECODE AS STATE_CODE,  COUNT(*) AS universities_count FROM public.ref_university WHERE is_dcf_applicable = true  AND survey_year = :surveyYear GROUP BY STATECODE\n" +
                    "),\n" +
                    "college_counts AS ( SELECT STATE_CODE, COUNT(*) AS colleges_count FROM public.college WHERE is_dcf_applicable = true AND survey_year = :surveyYear GROUP BY STATE_CODE\n" +
                    "),\n" +
                    "standalone_institution_counts AS (SELECT STATECODE AS STATE_CODE,  COUNT(*) AS standalone_institutions_count FROM public.ref_standalone_institution WHERE survey_year = :surveyYear GROUP BY STATECODE\n" +
                    "),\n" +
                    "university_form_counts AS (SELECT ru.STATECODE AS STATE_CODE, COUNT(*) AS university_forms_count FROM public.form_upload fu LEFT JOIN public.ref_university ru ON ru.id = fu.university_id AND ru.survey_year = fu.survey_year WHERE fu.university_id IS NOT NULL AND ru.is_dcf_applicable = true AND fu.survey_year = :surveyYear GROUP BY ru.STATECODE\n" +
                    "),\n" +
                    "college_form_counts AS (SELECT c.STATE_CODE, COUNT(*) AS college_forms_count FROM public.form_upload fu LEFT JOIN public.college c ON c.id = fu.college_institution_id AND c.survey_year = fu.survey_year WHERE fu.college_institution_id IS NOT NULL AND c.is_dcf_applicable = true AND fu.survey_year = :surveyYear  GROUP BY c.STATE_CODE\n" +
                    "),\n" +
                    "standalone_institution_form_counts AS ( SELECT si.STATECODE AS STATE_CODE, COUNT(*) AS standalone_institution_forms_count FROM public.form_upload fu LEFT JOIN public.ref_standalone_institution si ON si.id = fu.standalone_institution_id AND si.survey_year = fu.survey_year WHERE fu.standalone_institution_id IS NOT NULL AND fu.survey_year = :surveyYear GROUP BY si.STATECODE\n" +
                    ")\n" +
                    "SELECT rs.name as state_name,rs.st_code AS STATE_CODE,\n" +
                    "    COALESCE(u.universities_count, 0) AS university_expected_form,\n" +
                    "    COALESCE(c.colleges_count, 0) AS college_expected_form,\n" +
                    "    COALESCE(si.standalone_institutions_count, 0) AS standalone_expected_form,\n" +
                    "    COALESCE(uf.university_forms_count, 0) AS university_submitted_form,\n" +
                    "    COALESCE(cf.college_forms_count, 0) AS college_submitted_form,\n" +
                    "    COALESCE(sif.standalone_institution_forms_count, 0) AS standalone_submitted_form\n" +
                    "FROM ref_state rs  left join university_counts u on rs.st_code = u.STATE_CODE\n" +
                    "    left join college_counts c ON rs.st_code = c.STATE_CODE\n" +
                    "    left join standalone_institution_counts si ON rs.st_code = si.STATE_CODE\n" +
                    "    left join university_form_counts uf ON rs.st_code = uf.STATE_CODE\n" +
                    "    left join college_form_counts cf ON rs.st_code = cf.STATE_CODE\n" +
                    "    left join standalone_institution_form_counts sif ON rs.st_code = sif.STATE_CODE\n" +
                    "ORDER BY state_name;");
            List<Object[]> list = session.createNativeQuery(builder.toString())
                    .setParameter("surveyYear", surveyYear).getResultList();
            List<ProgressMontioringDetailDTO> dtos = new ArrayList<>();
            for (Object[] objects : list) {
                ProgressMontioringDetailDTO dto =ProgressMontioringDetailDTO
                        .builder()
                        .stateName(CommanObjectOperation.getStringValue(objects[0]))
                        .stateCode(CommanObjectOperation.getStringValue(objects[1]))
                        .universityExpectedForm(new BigInteger(objects[2].toString()))
                        .collegeExpectedForm(new BigInteger(objects[3].toString()))
                        .standaloneExpectedForm(new BigInteger(objects[4].toString()))
                        .universitySubmitForm(new BigInteger(objects[5].toString()))
                        .collegeSubmitForm(new BigInteger(objects[6].toString()))
                        .standaloneSubmitForm(new BigInteger(objects[7].toString()))
                        .build();
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<?> progressMonitoringStateWise(Integer surveyYear, ListType type, String stateCode) {
        List<ProgressMontioringListDTO> dtos = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder("WITH institution_data AS (SELECT 'U-' || ru.id AS aishe_code,  rs.name AS state_name,  rd.name AS district,  ru.name,  ruct.type AS type,  ru.survey_year,  'Expected' AS status, 'university' AS institution_type\n" +
                    "    FROM public.ref_university ru LEFT JOIN ref_state rs ON rs.st_code = ru.statecode\n" +
                    "    LEFT JOIN ref_district rd ON rd.dist_code = ru.district_code LEFT JOIN ref_university_type ruct ON ru.type_id = ruct.id WHERE ru.is_dcf_applicable = true and ru.statecode =:stateCode and ru.survey_year = :surveyYear\n" +
                    "    UNION ALL\n" +
                    "    SELECT 'C-' || ru.id AS aishe_code,  rs.name AS state_name,rd.name AS district,  ru.name,  ruct.type AS type, ru.survey_year,  'Expected' AS status,  'college' AS institution_type\n" +
                    "    FROM public.college ru  LEFT JOIN ref_state rs ON rs.st_code = ru.state_code\n" +
                    "    LEFT JOIN ref_district rd ON rd.dist_code = ru.district_code LEFT JOIN ref_university_college_type ruct ON ru.type_id = ruct.id WHERE ru.is_dcf_applicable = true and ru.state_code =:stateCode and ru.survey_year = :surveyYear\n" +
                    "    UNION ALL\n" +
                    "    SELECT 'S-' || ru.id AS aishe_code,  rs.name AS state_name,rd.name AS district,  ru.name,  ruct.type AS type, ru.survey_year,  'Expected' AS status, 'standalone' AS institution_type\n" +
                    "    FROM public.ref_standalone_institution ru LEFT JOIN ref_state rs ON rs.st_code = ru.statecode\n" +
                    "    LEFT JOIN ref_district rd ON rd.dist_code = ru.district_code LEFT JOIN ref_state_body ruct ON ru.statebodyid = ruct.id WHERE ru.survey_year = :surveyYear and ru.statecode =:stateCode\n" +
                    "),\n" +
                    "uploaded_institutions AS (\n" +
                    "    SELECT 'U-' || ru.id AS aishe_code, fu.survey_year, 'university' AS institution_type FROM public.form_upload fu\n" +
                    "    LEFT JOIN public.ref_university ru ON ru.id = fu.university_id AND fu.survey_year = ru.survey_year WHERE fu.university_id IS NOT NULL and ru.statecode =:stateCode  and fu.survey_year=:surveyYear\n" +
                    "    UNION ALL\n" +
                    "    SELECT 'C-' || ru.id AS aishe_code, fu.survey_year, 'college' AS institution_type\n" +
                    "    FROM public.form_upload fu  LEFT JOIN public.college ru ON ru.id = fu.college_institution_id AND fu.survey_year = ru.survey_year WHERE fu.college_institution_id IS NOT NULL and ru.state_code =:stateCode and fu.survey_year=:surveyYear\n" +
                    "    UNION ALL\n" +
                    "    SELECT 'S-' || ru.id AS aishe_code, fu.survey_year, 'standalone' AS institution_type\n" +
                    "    FROM public.form_upload fu LEFT JOIN public.ref_standalone_institution ru ON ru.id = fu.standalone_institution_id AND fu.survey_year = ru.survey_year WHERE fu.standalone_institution_id IS NOT NULL and ru.statecode =:stateCode and fu.survey_year=:surveyYear\n" +
                    "),\n" +
                    "\n" +
                    "pending_institution_data AS (SELECT  aishe_code,  state_name,  district,  name,  type,  survey_year, 'Pending' AS status, institution_type   \n" +
                    "    FROM institution_data iid WHERE iid.survey_year = :surveyYear\n" +
                    "    AND NOT EXISTS (SELECT 1 FROM uploaded_institutions ui WHERE ui.aishe_code = iid.aishe_code AND ui.institution_type = iid.institution_type)\n" +
                    "),\n" +
                    "\n" +
                    "submitted_institution_data AS (SELECT  aishe_code,  state_name,  district,  name,  type,  survey_year, 'Submitted' AS status,  institution_type\n" +
                    "    FROM institution_data iid WHERE iid.survey_year = :surveyYear\n" +
                    "    AND EXISTS (SELECT 1 FROM uploaded_institutions ui WHERE ui.aishe_code = iid.aishe_code AND ui.institution_type = iid.institution_type)\n" +
                    ")\n" +
                    "\n" +
                    "SELECT  state_name,  district,  aishe_code,  name,  type, status FROM (\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM institution_data WHERE :listType = '1' AND institution_type = 'university'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM submitted_institution_data WHERE :listType = '2' AND institution_type = 'university'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM pending_institution_data WHERE :listType = '3'  AND institution_type = 'university'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM institution_data WHERE :listType = '4' AND institution_type = 'college'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM submitted_institution_data WHERE :listType = '5' AND institution_type = 'college'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM pending_institution_data WHERE :listType = '6' AND institution_type = 'college'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM institution_data WHERE :listType = '7' AND institution_type = 'standalone'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM submitted_institution_data WHERE :listType = '8' AND institution_type = 'standalone'\n" +
                    "    UNION ALL\n" +
                    "    SELECT aishe_code, state_name, district, name, type, status FROM pending_institution_data WHERE :listType = '9'  AND institution_type = 'standalone'     \n" +
                    ") AS result ORDER BY state_name, district, name, aishe_code;\n");
            List<Object[]> list = session.createNativeQuery(builder.toString())
                    .setParameter("surveyYear", surveyYear)
                    .setParameter("stateCode", stateCode)
                    .setParameter("listType", type.getType())
                    .getResultList();

            for (Object[] objects : list) {
                ProgressMontioringListDTO dto = ProgressMontioringListDTO
                        .builder()
                        .state(CommanObjectOperation.getStringValue(objects[0]))
                        .district(CommanObjectOperation.getStringValue(objects[1]))
                        .aisheCode(CommanObjectOperation.getStringValue(objects[2]))
                        .name(CommanObjectOperation.getStringValue(objects[3]))
                        .type(CommanObjectOperation.getStringValue(objects[4]))
                        .status(CommanObjectOperation.getStringValue(objects[5]))
                        .build();
                dtos.add(dto);
            }
            return dtos;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dtos;
    }

    private ProgressMoniteringDTO standaloneObjectBind(Session session, RefStateBody universityType, Integer surveyYear, String stateCode, String fromDate, String toDate) {
        ProgressMoniteringDTO dto = new ProgressMoniteringDTO();
        dto.setFormType("Standalone");
        dto.setTypeId(universityType.getId().toString());
        dto.setTypeName(universityType.getType());
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.standaloneParameterBinder(NativeQuerySystem.IS_DCF_APPLICABLE_STANDALONE, surveyYear, stateCode, Integer.valueOf(universityType.getId())/**/));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalFormExpected(result);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.standaloneParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_STANDALONE, surveyYear, stateCode, Integer.valueOf(universityType.getId())/**/), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
        BigInteger totalResult = (BigInteger) totalQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        return dto;
    }

    private List<FormUploadDateWiseDTO> bindObjectInTODto(List<Object[]> userListData, String fromDate, String toDate) {
        List<FormUploadDateWiseDTO> list = new ArrayList<>();
        for (Object[] object : userListData) {
            FormUploadDateWiseDTO registeredUserDTO = new FormUploadDateWiseDTO();
            if (object[0] != null) {
                registeredUserDTO.setTotalCount((BigInteger) object[0]);
            }
            if (object[1] != null) {
                registeredUserDTO.setDate(DateUtils.convertDBSlashDateToString(LocalDate.parse(object[1].toString())));
            }
            list.add(registeredUserDTO);
        }
        return list;
    }

    private String dayWise(String query, String fromDate, String toDate) {
        StringBuilder builder = new StringBuilder();
        builder.append(query);
        if (null != fromDate && null != toDate) {
            if ((fromDate != "null" && toDate != "null")) {
                if (!fromDate.equals(toDate)) {
                    LocalDate fromDateFrom = DateUtils.convertStringSlashDateToDBDate(fromDate);
                    LocalDate fromDateTO = DateUtils.convertStringSlashDateToDBDate(toDate);

                    builder.append(" and cast(fu.upload_date as date) >='" + fromDateFrom + "' and cast(fu.upload_date as date) <='" + fromDateTO + "'");
                    // builder.append(" and  fu.upload_date BETWEEN  '" + fromDateFrom + "' and '" + fromDateTO + "'");
                } else {
                    LocalDate fromDateTO = DateUtils.convertStringSlashDateToDBDate(toDate);
                    builder.append(" and  cast(fu.upload_date as text) like '" + fromDateTO + "%'");
                }
            }
        }

        builder = builder.append(" group by DATE(fu.upload_date)  order by upload_date asc ");
        return builder.toString();
    }

    private ProgressMonitoringBasicDTO collegeObjectBind(Session session, Integer surveyYear, String fromDate, String toDate) {
        ProgressMonitoringBasicDTO dto = new ProgressMonitoringBasicDTO();
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        StringBuilder totalBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.collegeParameterBinder(NativeQuerySystem.BASIC_FILLED_COLLEGE, surveyYear, null, null, null));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalBasicFilled(result);

        totalBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.collegeParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_COLLEGE, surveyYear, null, null, null), fromDate, toDate, surveyYear));
        Query totalFormQuery = session.createNativeQuery(totalBuilder.toString());
        BigInteger totalResult = (BigInteger) totalFormQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.collegeParameterBinder(NativeQuerySystem.FORM_UPLOAD_COLLEGE, surveyYear, null, null, null), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(dayWise(totalQueryBuilder.toString(), fromDate, toDate));
        List<Object[]> userListData = totalQuery.getResultList();
        dto.setDayWise(bindObjectInTODto(userListData, toDate, toDate));
        return dto;
    }

    private ProgressMonitoringBasicDTO universityObjectBind(Session session, Integer surveyYear, String fromDate, String toDate) {
        ProgressMonitoringBasicDTO dto = new ProgressMonitoringBasicDTO();
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        StringBuilder totalBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.universityParameterBinder(NativeQuerySystem.BASIC_FILLED_UNIVERSITY, surveyYear, null, null));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalBasicFilled(result);
        totalBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.universityParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_UNIVERSITY, surveyYear, null, null), fromDate, toDate, surveyYear));
        Query totalFormQuery = session.createNativeQuery(totalBuilder.toString());
        BigInteger totalResult = (BigInteger) totalFormQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.universityParameterBinder(NativeQuerySystem.FORM_UPLOAD_UNIVERSITY, surveyYear, null, null), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(dayWise(totalQueryBuilder.toString(), fromDate, toDate));
        List<Object[]> userListData = totalQuery.getResultList();
        dto.setDayWise(bindObjectInTODto(userListData, fromDate, toDate));
        return dto;
    }

    private ProgressMonitoringBasicDTO standaloneObjectBind(Session session, Integer surveyYear, String fromDate, String toDate) {
        ProgressMonitoringBasicDTO dto = new ProgressMonitoringBasicDTO();
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        StringBuilder totalBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.standaloneParameterBinder(NativeQuerySystem.BASIC_FILLED_STANDALONE, surveyYear, null, null));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalBasicFilled(result);
        totalBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.standaloneParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_STANDALONE, surveyYear, null, null), fromDate, toDate, surveyYear));
        Query totalFormQuery = session.createNativeQuery(totalBuilder.toString());
        BigInteger totalResult = (BigInteger) totalFormQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.standaloneParameterBinder(NativeQuerySystem.FORM_UPLOAD_STANDALONE, surveyYear, null, null), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(dayWise(totalQueryBuilder.toString(), fromDate, toDate));
        List<Object[]> userListData = totalQuery.getResultList();
        dto.setDayWise(bindObjectInTODto(userListData, fromDate, toDate));
        return dto;
    }

    private ProgressMoniteringDTO collegeObjectBind(Session session, RefUniversityCollegeType universityType, Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, String searchText) {
        ProgressMoniteringDTO dto = new ProgressMoniteringDTO();
        dto.setFormType("College");
        dto.setTypeId(universityType.getId());
        dto.setTypeName(universityType.getType());
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.collegeParameterBinder(NativeQuerySystem.IS_DCF_APPLICABLE_COLLEGE, surveyYear, stateCode, universityType.getId(), universityId));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalFormExpected(result);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.collegeParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_COLLEGE, surveyYear, stateCode, universityType.getId(), universityId), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
        BigInteger totalResult = (BigInteger) totalQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        return dto;
    }


    private List<InstituteDetailDTO> collegeExpectedSubmittedBind(Session session, String universityType, Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, FormType formType, InstitutionType institutionType/*, int page, int pageSize, String searchText*/) {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        List<Object[]> userListData = null;
        if (formType.getType() == 1) {
            expectedQueryBuilder.append(parameterResolver.collegeParameterBinder(NativeQuerySystem.EXPECTED_AISHE_CODE_AND_NAME_OF_COLLEGE, surveyYear, stateCode, universityType, universityId));
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
            /*expectedQuery.setFirstResult((page - 1) * pageSize);
            expectedQuery.setMaxResults(pageSize);*/
            userListData = (List<Object[]>) expectedQuery.getResultList();
        } else if (formType.getType() == 3) {
            expectedQueryBuilder.append(parameterResolver.collegeParameterBinder("select 'C-'||c.id as aishe_code,c.name from college c where 1=1 ", surveyYear, stateCode, universityType, universityId));
            expectedQueryBuilder.append(" and c.id not in (select college_institution_id from form_upload as fu where form_id='form2' and survey_year='" + surveyYear + "')");
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
            userListData = (List<Object[]>) expectedQuery.getResultList();

        } else {
            totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.collegeParameterBinder(NativeQuerySystem.SUBMITTED_AISHE_CODE_AND_NAME_OF_COLLEGE, surveyYear, stateCode, universityType, universityId), fromDate, toDate, surveyYear));
            Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
           /* totalQuery.setFirstResult((page - 1) * pageSize);
            totalQuery.setMaxResults(pageSize);*/
            userListData = (List<Object[]>) totalQuery.getResultList();
        }
        if (null != userListData) {
            return setObject(userListData, institutionType.getType());
        }
        return null;
    }

    private List<InstituteDetailDTO> universityExpectedSubmittedBind(Session session, String universityType, Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, FormType formType, InstitutionType institutionType) {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        List<Object[]> userListData = null;
        if (formType.getType() == 1) {
            expectedQueryBuilder.append(parameterResolver.universityParameterBinder(NativeQuerySystem.EXPECTED_AISHE_CODE_AND_NAME_OF_UNIVERSITY, surveyYear, stateCode, universityType));
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
            userListData = (List<Object[]>) expectedQuery.getResultList();
        } else if (formType.getType() == 3) {
            expectedQueryBuilder.append(parameterResolver.universityParameterBinder("select 'U-'||ru.id as aishe_code,ru.name from ref_university ru where 1=1 ", surveyYear, stateCode, universityType));
            expectedQueryBuilder.append(" and ru.id not in (select university_id from form_upload as fu where form_id='form1' and survey_year='" + surveyYear + "')");
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
            userListData = (List<Object[]>) expectedQuery.getResultList();
        } else {
            totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.universityParameterBinder(NativeQuerySystem.SUBMITTED_AISHE_CODE_AND_NAME_OF_UNIVERSITY, surveyYear, stateCode, universityType), fromDate, toDate, surveyYear));
            Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
            /*totalQuery.setFirstResult((page - 1) * pageSize);
            totalQuery.setMaxResults(pageSize);*/
            userListData = (List<Object[]>) totalQuery.getResultList();
        }
        if (null != userListData) {
            return setObject(userListData, institutionType.getType());
        }
        return null;
    }

    private List<InstituteDetailDTO> standaloneExpectedSubmittedBind(Session session, String universityType, Integer surveyYear, String stateCode, String universityId, String fromDate, String toDate, FormType formType, InstitutionType institutionType/*, int page, int pageSize, String searchText*/) {
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        List<Object[]> userListData = null;
        if (formType.getType() == 1) {
            expectedQueryBuilder.append(parameterResolver.standaloneParameterBinder(NativeQuerySystem.EXPECTED_AISHE_CODE_AND_NAME_OF_STANDALONE, surveyYear, stateCode, Integer.valueOf(universityType)/**/));
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
           /* expectedQuery.setFirstResult((page - 1) * pageSize);
            expectedQuery.setMaxResults(pageSize);*/
            userListData = (List<Object[]>) expectedQuery.getResultList();
        } else if (formType.getType() == 3) {
            expectedQueryBuilder.append(parameterResolver.standaloneParameterBinder("select 'S-'||si.id as aishe_code,si.name from ref_standalone_institution si ", surveyYear, stateCode, Integer.valueOf(universityType)/**/));
            expectedQueryBuilder.append(" and si.id not in (select standalone_institution_id from form_upload as fu where form_id='form3' and survey_year='" + surveyYear + "')");
            Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
            userListData = (List<Object[]>) expectedQuery.getResultList();
        } else {
            totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.standaloneParameterBinder(NativeQuerySystem.SUBMITTED_AISHE_CODE_AND_NAME_OF_STANDALONE, surveyYear, stateCode, Integer.valueOf(universityType)/**/), fromDate, toDate, surveyYear));
            Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
            /*totalQuery.setFirstResult((page - 1) * pageSize);
            totalQuery.setMaxResults(pageSize);*/
            userListData = (List<Object[]>) totalQuery.getResultList();
        }
        if (null != userListData) {
            return setObject(userListData, institutionType.getType());
        }
        return null;
    }

    private List<InstituteDetailDTO> setObject(List<Object[]> userListData, String institutionType) {
        List<InstituteDetailDTO> dtoList = new ArrayList<>();
        for (Object[] object : userListData) {

            InstituteDetailDTO registeredUserDTO = new InstituteDetailDTO();
            registeredUserDTO.setFormType(institutionType);
            if (object[0] != null) {
                registeredUserDTO.setAisheCode(object[0].toString());

            }
            if (object[1] != null) {
                registeredUserDTO.setInstituteName(object[1].toString());
            }
            dtoList.add(registeredUserDTO);
        }
        return dtoList;
    }

    private ProgressMoniteringDTO universityObjectBind(Session session, RefUniversityType universityType, Integer surveyYear, String stateCode, String fromDate, String toDate) {
        ProgressMoniteringDTO dto = new ProgressMoniteringDTO();
        dto.setFormType("University");
        dto.setTypeId(universityType.getId());
        dto.setTypeName(universityType.getType());
        StringBuilder expectedQueryBuilder = new StringBuilder();
        StringBuilder totalQueryBuilder = new StringBuilder();
        expectedQueryBuilder.append(parameterResolver.universityParameterBinder(NativeQuerySystem.IS_DCF_APPLICABLE_UNIVERSITY, surveyYear, stateCode, universityType.getId()));
        Query expectedQuery = session.createNativeQuery(expectedQueryBuilder.toString());
        BigInteger result = (BigInteger) expectedQuery.getSingleResult();
        dto.setTotalFormExpected(result);
        totalQueryBuilder.append(parameterResolver.fromUploadParameterBinder(parameterResolver.universityParameterBinder(NativeQuerySystem.TOTAL_FORM_UPLOAD_UNIVERSITY, surveyYear, stateCode, universityType.getId()), fromDate, toDate, surveyYear));
        Query totalQuery = session.createNativeQuery(totalQueryBuilder.toString());
        BigInteger totalResult = (BigInteger) totalQuery.getSingleResult();
        dto.setTotalFormUploaded(totalResult);
        return dto;
    }

}
