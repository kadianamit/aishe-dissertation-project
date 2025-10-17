package aishe.gov.in.dao;

import aishe.gov.in.enums.InstitutionTypeNew;
import aishe.gov.in.enums.InstitutionUserType;
import aishe.gov.in.enums.SurveyParticipatedStatus;
import aishe.gov.in.masterseo.UserContactDetail;
import aishe.gov.in.utility.CommanObjectOperation;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
@Log4j2
public class UserContactDetailDaoImpl implements UserContactDetailDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<UserContactDetail> userContactDetail(String stateCode, InstitutionTypeNew institutionType, String subCategory, Integer surveyYear, String districtCode, InstitutionUserType userType, SurveyParticipatedStatus participatedStatus, String universityId) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder builder = new StringBuilder();
            if (institutionType.getType().equals(InstitutionTypeNew.ALL.getType())) {
                builder.append(queryBuilder(InstitutionTypeNew.COLLEGE, subCategory, districtCode, userType, participatedStatus, universityId));
                builder.append(" UNION ALL " + queryBuilder(InstitutionTypeNew.STANDALONE, subCategory, districtCode, userType, participatedStatus, universityId));
                builder.append(" UNION ALL " + queryBuilder(InstitutionTypeNew.UNIVERSITY, subCategory, districtCode, userType, participatedStatus, universityId));
            } else {
                builder = queryBuilder(institutionType, subCategory, districtCode, userType, participatedStatus, universityId);
            }


               builder.append(" order by aishe_code,name");

            Query query = session.createNativeQuery(builder.toString(), UserContactDetail.class);
            query.setParameter("surveyYear", surveyYear);
            query.setParameter("stateCode", stateCode);
            if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(subCategory)))
                query.setParameter("subCategory", subCategory);
            if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(districtCode)))
                query.setParameter("districtCode", districtCode);
            if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(universityId)))
                query.setParameter("universityId", universityId);

            return query.getResultList();
        } catch (Exception e) {
            log.error("error occurred in userContactDetail method due to this exception {} ", e.getMessage());
        }
        return null;
    }

    private StringBuilder queryBuilder(InstitutionTypeNew institutionType, String subCategory, String districtCode, InstitutionUserType userType, SurveyParticipatedStatus participatedStatus, String universityId) {
        StringBuilder builder = new StringBuilder();
        StringBuilder selectResult = new StringBuilder();


        if (null == userType || userType.getType().equals(InstitutionUserType.ALL.getType()))
            selectResult.append("insd.nodal_Officer_Name,insd.nodal_Officer_Mobile,insd.nodal_Officer_Email," +
                    "head_officer_name as institution_Head_Name,head_officer_mobile as institution_Head_Mobile," +
                    "head_officer_email as institution_Head_Email ");
        else if (userType.getType().equals(InstitutionUserType.NODAL_OFFICER.getType()))
            selectResult.append("insd.nodal_Officer_Name,insd.nodal_Officer_Mobile,insd.nodal_Officer_Email," +
                    "null as institution_Head_Name,null as institution_Head_Mobile,null as institution_Head_Email ");
        else if (userType.getType().equals(InstitutionUserType.INSTITUTION_HEAD.getType()))
            selectResult.append("null as nodal_Officer_Name,null as nodal_Officer_Mobile,null as nodal_Officer_Email," +
                    "head_officer_name as institution_Head_Name,head_officer_mobile as institution_Head_Mobile," +
                    "head_officer_email as institution_Head_Email ");


        switch (institutionType.getType()) {
            case "U": {
                builder.append("select 'U-'||ru.id as aishe_code, ru.name,").append(selectResult).append("from " +
                                "ref_university ru \n").append("left join institute_detail insd on insd.aishe_code='U-'|| ru.id\n")
                        .append("left join form_upload fu on (fu.university_id=ru.id and fu.survey_year=ru.survey_year)\n")
                        .append("where 1=1 and ru.survey_year=:surveyYear and (ru.statecode=:stateCode OR 'ALL'=:stateCode) ");
                if (Boolean.TRUE.equals(CommanObjectOperation.objectValidate(participatedStatus))) {
                    if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PARTICIPATED.getStatus())) {
                        builder.append(" and fu.university_id is not null and fu.survey_year is not null ");
                    } else if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PENDING.getStatus())) {
                        builder.append(" and fu.university_id is null and fu.survey_year is null ");
                    }
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(districtCode))) {
                    builder.append(" and ru.district_code=:districtCode ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(subCategory))) {
                    builder.append(" and ru.type_id=:subCategory ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(universityId)))
                    builder.append(" and insd.university_id=:universityId ");

                break;
            }
            case "C": {
                builder.append("select 'C-'||ru.id as aishe_code, ru.name,").append(selectResult).append("from college ru \n")
                        .append("left join institute_detail insd on insd.aishe_code='C-'|| ru.id\n")
                        .append("left join form_upload fu on (fu.college_institution_id=ru.id and fu.survey_year=ru.survey_year)\n")
                        .append("where 1=1 and ru.survey_year=:surveyYear and (ru.state_code=:stateCode or 'ALL'=:stateCode) ");
                if (Boolean.TRUE.equals(CommanObjectOperation.objectValidate(participatedStatus))) {
                    if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PARTICIPATED.getStatus())) {
                        builder.append(" and fu.college_institution_id is not null and fu.survey_year is not null ");
                    } else if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PENDING.getStatus())) {
                        builder.append(" and fu.college_institution_id is null and fu.survey_year is null ");
                    }
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(districtCode))) {
                    builder.append(" and ru.district_code=:districtCode ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(subCategory))) {
                    builder.append(" and ru.type_id=:subCategory ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(universityId)))
                    builder.append(" and insd.university_id=:universityId ");
                break;
            }
            case "S": {
                builder.append("select 'S-'||ru.id as aishe_code, ru.name,")
                        .append(selectResult).append("from ref_standalone_institution ru \n")
                        .append("left join institute_detail insd on insd.aishe_code='S-'|| ru.id\n")
                        .append("left join form_upload fu on (fu.standalone_institution_id=ru.id and fu.survey_year=ru.survey_year)\n")
                        .append("where 1=1 and ru.survey_year=:surveyYear and (ru.statecode=:stateCode  OR 'ALL'=:stateCode) ");
                if (Boolean.TRUE.equals(CommanObjectOperation.objectValidate(participatedStatus))) {
                    if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PARTICIPATED.getStatus())) {
                        builder.append(" and fu.standalone_institution_id is not null and fu.survey_year is not null ");
                    } else if (participatedStatus.getStatus().equals(SurveyParticipatedStatus.PENDING.getStatus())) {
                        builder.append(" and fu.standalone_institution_id is null and fu.survey_year is null ");
                    }
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(districtCode))) {
                    builder.append(" and ru.district_code=:districtCode ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(subCategory))) {
                    builder.append(" and cast(ru.statebodyid as text)=:subCategory ");
                }
                if (Boolean.TRUE.equals(CommanObjectOperation.stringValidate(universityId)))
                    builder.append(" and insd.university_id=:universityId ");
                break;
            }

        }

        return builder;
    }


    @Override
    public Integer maxSurveyYear() {
        try (Session session = sessionFactory.openSession()) {
            return (Integer) session.createNativeQuery("select max(survey_year) from public.survey_master").getSingleResult();
        } catch (Exception e) {
            log.error("error occurred in maxSurveyYear method due to this exception {} ", e.getMessage());
        }

        return null;
    }


}
