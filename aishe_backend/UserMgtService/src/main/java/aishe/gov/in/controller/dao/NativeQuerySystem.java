package aishe.gov.in.dao;

public interface NativeQuerySystem {
    public String COLLEGE_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id, " +
            "ru.name as university_name," +
            "case when user_id is not null then null else null end as form_upload, " +
            "urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by ,clg_type.type   " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) left join ref_university_college_type clg_type on c.type_id=clg_type.id where um.role_id=:roleId and c.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer))  " +
            "and ru.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String COUNT_COLLEGE_WITHOUT_SURVEY = "select count(*) " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) where um.role_id=:roleId and c.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer))  " +
            "and ru.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String COLLEGE_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id, " +
            "ru.name as university_name ," +
            "case when user_id is not null then null else null end as form_upload, " +
            "urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,clg_type.type " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) left join ref_university_college_type clg_type on c.type_id=clg_type.id where um.role_id=:roleId and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String COUNT_COLLEGE_WITH_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) " +
            "where um.role_id=:roleId and um.state_level_body_institute  is not null and um.state_level_body is  not  null ";
    public String FORM_UPLOAD_COLLEGE_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            " c.name as institution_name,aishe_code,status_id, " +
            " ru.name as university_name,fu.form_id, " +
            " urm.level,us.status,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            " ,um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,clg_type.type from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            " left join ref_state s on s.st_code=um.state_code  " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            " left join ref_university ru on ru.id=um.state_level_body  " +
            " left join college c on c.id=cast(um.state_level_body_institute as integer) left join ref_university_college_type clg_type on c.type_id=clg_type.id " +
            " left join form_upload fu on fu.college_institution_id=cast(um.state_level_body_institute as integer) " +
            " where um.role_id=:roleId and fu.institute_type='C' and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String FORM_UPLOAD_COUNT_COLLEGE_WITH_SURVEY = "select count(*)" +
            " from user_master um " +
            " left join ref_user_status us on us.id=um.status_id " +
            " left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            " left join ref_state s on s.st_code=um.state_code  " +
            " left join ref_university ru on ru.id=um.state_level_body  " +
            " left join college c on c.id=cast(um.state_level_body_institute as integer) " +
            " left join form_upload fu on fu.college_institution_id=cast(um.state_level_body_institute as integer) " +
            " where um.role_id=:roleId and fu.institute_type='C' and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String FROM_UPLOAD_COLLEGE_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            " c.name as institution_name,aishe_code,status_id, " +
            " ru.name as university_name,fu.form_id, " +
            " urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            " ,document_pdf,document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,clg_type.type " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) left join ref_university_college_type clg_type on c.type_id=clg_type.id " +
            "left join form_upload fu on fu.college_institution_id=cast(um.state_level_body_institute as integer) " +
            "where um.role_id=:roleId " +
            "and fu.institute_type='C'" +
            "and c.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer))  " +
            "and ru.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) " +
            "and fu.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer) and institute_type='C' ) " +
            "and um.state_level_body_institute  is not null and um.state_level_body is  not  null";
    public String FORM_UPLOAD_COUNT_COLLEGE_WITHOUT_SURVEY = "select count(*) from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university ru on ru.id=um.state_level_body " +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) " +
            "left join form_upload fu on fu.college_institution_id=cast(um.state_level_body_institute as integer) " +
            "where um.role_id=:roleId " +
            "and c.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer))  " +
            "and ru.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) " +
            "and fu.survey_year=(select max(survey_year)from college where id=cast(um.state_level_body_institute as integer) and institute_type='C' )" +
            "and fu.institute_type='C' and um.state_level_body_institute  is not null and um.state_level_body is  not  null";

    public String STANDALONE_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id, " +
            "case when user_id is not null then null else null end as university_name, " +
            "case when user_id is not null then null else null end as form_upload, " +
            "urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            ",um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,sta_type.type from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) left join ref_state_body sta_type on c.statebodyid=sta_type.id  where um.role_id=:roleId and um.state_level_body_institute  is not null  " +
            "and c.survey_year=(select max(survey_year)from ref_standalone_institution where id=cast(um.state_level_body_institute as integer))";
    public String COUNT_STANDALONE_WITHOUT_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) where um.role_id=:roleId and um.state_level_body_institute  is not null  " +
            "and c.survey_year=(select max(survey_year)from ref_standalone_institution where id=cast(um.state_level_body_institute as integer))";
    public String STANDALONE_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id, " +
            "case when user_id is not null then null else null end as university_name, " +
            "case when user_id is not null then null else null end as form_upload " +
            ",urm.level,us.status  ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code,um.document_pdf,um.document_name,um.state_code,um.name ,um.approved_datetime,um.approved_by, sta_type.type    " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) left join ref_state_body sta_type on c.statebodyid=sta_type.id  where um.role_id=:roleId and um.state_level_body_institute  is not null ";
    public String COUNT_STANDALONE_WITH_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) where um.role_id=:roleId and um.state_level_body_institute  is not null ";
    public String FORM_UPLOAD_STANDALONE_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id,  " +
            "CASE WHEN user_id is not null then null else null end as university_name, fu.form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            ",um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,sta_type.type from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code  " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) " +
            " left join ref_state_body sta_type on c.statebodyid=sta_type.id "+
            "left join form_upload fu on fu.standalone_institution_id=cast(um.state_level_body_institute as integer) " +
            "where um.role_id=:roleId and um.state_level_body_institute  is not null  " +
            "and c.survey_year=(select max(survey_year)from ref_standalone_institution rsi where id=cast(um.state_level_body_institute as integer)) " +
            "and fu.survey_year=(select max(survey_year)from ref_standalone_institution  where id=cast(um.state_level_body_institute as integer) and institute_type='S' ) " +
            " and fu.institute_type='S'";
    public String FORM_UPLOAD_COUNT_STANDALONE_WITHOUT_SURVEY = "select count(*) " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code  " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer) " +
            "left join form_upload fu on fu.standalone_institution_id=cast(um.state_level_body_institute as integer) " +
            "where um.role_id=:roleId and um.state_level_body_institute  is not null  " +
            "and c.survey_year=(select max(survey_year)from ref_standalone_institution rsi where id=cast(um.state_level_body_institute as integer)) " +
            "and fu.survey_year=(select max(survey_year)from ref_standalone_institution  where id=cast(um.state_level_body_institute as integer) and institute_type='S' ) " +
            " and fu.institute_type='S'";
    public String FORM_UPLOAD_STANDALONE_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "c.name as institution_name,aishe_code,status_id,  " +
            "CASE WHEN user_id is not null then null else null end as university_name, fu.form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            ",um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,sta_type.type from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            " left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer)" +
            " left join ref_state_body sta_type on c.statebodyid=sta_type.id "+
            "left join form_upload fu on fu.standalone_institution_id=cast(um.state_level_body_institute as integer) " +
            " where um.role_id=:roleId and um.state_level_body_institute  is not null   and fu.institute_type='S'";
    public String FORM_UPLOAD_COUNT_STANDALONE_WITH_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer)" +
            "left join form_upload fu on fu.standalone_institution_id=cast(um.state_level_body_institute as integer) " +
            " where um.role_id=:roleId and um.state_level_body_institute  is not null   and fu.institute_type='S'";

    public String UNIVERSITY_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "CASE WHEN user_id is not null then null else null end as institution_name,aishe_code,status_id,c.name as university_name,CASE WHEN user_id is not null then null else null end as form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code,um.document_pdf,um.document_name,um.state_code,um.name ,um.approved_datetime,um.approved_by, uni_type.type " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body  " +
            "left join ref_university_type uni_type on c.type_id=uni_type.id "+
            "where um.role_id=:roleId and c.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and um.state_level_body_institute  is null and um.state_level_body is not null";
    public String COUNT_UNIVERSITY_WITHOUT_SURVEY = "select count(*) " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university c on c.id=um.state_level_body  where um.role_id=:roleId and c.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and um.state_level_body_institute  is  null and um.state_level_body is not null ";
    public String UNIVERSITY_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "CASE WHEN user_id is not null then null else null end as institution_name,aishe_code,status_id," +
            "c.name as university_name,CASE WHEN user_id is not null then null else null end as form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code       " +
            ",um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by, uni_type.type from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body left join ref_university_type uni_type on c.type_id=uni_type.id where um.role_id=:roleId and um.state_level_body_institute  is  null and um.state_level_body is not null ";
    public String COUNT_UNIVERSITY_WITH_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university c on c.id=um.state_level_body  where um.role_id=:roleId and um.state_level_body_institute  is  null and um.state_level_body is not null";
    public String FORM_UPLOAD_UNIVERSITY_WITHOUT_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "CASE WHEN user_id is not null then null else null end as institution_name,aishe_code,status_id,c.name as university_name,fu.form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code,um.document_pdf,um.document_name ,um.state_code ,um.name ,um.approved_datetime,um.approved_by,uni_type.type    " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body " +
            "left join ref_university_type uni_type on c.type_id=uni_type.id "+
            "left join form_upload fu on fu.university_id=um.state_level_body" +
            " where um.role_id=:roleId and c.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) " +
            "and fu.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and institute_type='U' and um.state_level_body_institute  is  null and um.state_level_body is not null";
    public String FORM_UPLOAD_COUNT_UNIVERSITY_WITHOUT_SURVEY = "select count(*) " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            "left join ref_university c on c.id=um.state_level_body " +
            "left join form_upload fu on fu.university_id=um.state_level_body" +
            " where um.role_id=:roleId and c.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) " +
            " and fu.survey_year=(select max(survey_year)from ref_university  where id=um.state_level_body) and institute_type='U' and um.state_level_body_institute  is  null and um.state_level_body is not null";
    public String FORM_UPLOAD_UNIVERSITY_WITH_SURVEY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "CASE WHEN user_id is not null then null else null end as institution_name,aishe_code,status_id," +
            "c.name as university_name, fu.form_id " +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code,um.document_pdf,um.document_name ,um.state_code ,um.name,um.approved_datetime,um.approved_by, uni_type.type    " +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body left join ref_university_type uni_type on c.type_id=uni_type.id " +
            "left join form_upload fu on fu.university_id=um.state_level_body + where um.role_id=:roleId and fu.institute_type='U' and um.state_level_body_institute  is  null and um.state_level_body is not null";
    public String FORM_UPLOAD_COUNT_UNIVERSITY_WITH_SURVEY = "select count(*)" +
            "from user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body " +
            "left join form_upload fu on fu.university_id=um.state_level_body + where um.role_id=:roleId and fu.institute_type='U' and um.state_level_body_institute  is  null and um.state_level_body is not null";
    public String OTHERS_USER = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved,\n" +
            "CASE \n" +
            "WHEN um.state_level_body_institute  is not null then\n" +
            "     case \n" +
            "           when  um.aishe_code like '%C-%' then c.name\n" +
            "           when  um.aishe_code like '%S-%' then si.name  \n" +
            "     end \n" +
            "end\n" +
            "\n" +
            "as institution_name,um.aishe_code as aishe_code,status_id,ru.name as university_name, null as formupload \n" +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,\n" +
            "um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name ,\n" +
            "rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by " +
            ",case WHEN um.state_level_body_institute  is not null or um.state_level_body is not null then\n" +
            "case when  um.state_level_body_institute  is not null and um.state_level_body is not null and 'C-'|| um.state_level_body_institute=um.aishe_code then clg_type.type\n" +
            "when  um.state_level_body_institute  is not null and um.state_level_body is  null and  'S-'|| um.state_level_body_institute=um.aishe_code then sta_type.type\n" +
            "when  um.state_level_body is not null and um.state_level_body_institute  is null  then   uni_type.type\n" +
            "end  end  as category_type from user_master um  \n" +
            "left join ref_user_status us on us.id=um.status_id \n" +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  \n" +
            "left join ref_state s on s.st_code=um.state_code\n" +
            "left join ref_district rd on rd.dist_code=um.address_district_code\n" +
            "left join ref_university ru on ru.id=um.state_level_body and ru.survey_year=(select max(survey_year) from ref_university where id=um.state_level_body)\n" +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) and c.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_standalone_institution si on si.id= cast(um.state_level_body_institute as integer) and si.survey_year=(select max(survey_year) from ref_standalone_institution where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_university_college_type clg_type on c.type_id=clg_type.id\n" +
            "left join ref_university_type uni_type on ru.type_id=uni_type.id\n" +
            "left join ref_state_body sta_type on si.statebodyid=sta_type.id "+
            "where um.role_id=:roleId";
    public String COUNT_OTHERS_USER = "select count(*)  from user_master um  \n" +
            "left join ref_user_status us on us.id=um.status_id \n" +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  \n" +
            "left join ref_state s on s.st_code=um.state_code\n" +
            "left join ref_district rd on rd.dist_code=um.address_district_code\n" +
            "left join ref_university ru on ru.id=um.state_level_body and ru.survey_year=(select max(survey_year) from ref_university where id=um.state_level_body)\n" +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) and c.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_standalone_institution si on si.id= cast(um.state_level_body_institute as integer) and si.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "where um.role_id=:roleId";


    public String FORM_UPLOAD_UN_FILED_UNIVERSITY = "select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "CASE WHEN user_id is not null then null else null end as institution_name,aishe_code,status_id,c.name as university_name, " +
            "case when user_id is not null then false else false end  as form_id,urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code      " +
            ",um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by,uni_type.type from " +
            " user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body  left join ref_university_type uni_type on c.type_id=uni_type.id " +
            "right join  " +
            "  (select id,survey_year from  ref_university where survey_year=0  " +
            "except " +
            "select  university_id,survey_year from form_upload where survey_year =0 and form_id='form1' ) as t1 " +
            " on t1.id=um.state_level_body where  um.state_level_body_institute is null  " +
            "and  um.role_id=:roleId and um.state_level_body is not null ";


    public String FORM_UPLOAD_UN_FILED_COUNT_UNIVERSITY = "select  COUNT (*)  " +
            "from " +
            " user_master um " +
            "left join ref_user_status us on us.id=um.status_id " +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "left join ref_university c on c.id=um.state_level_body  " +
            "right join  " +
            "  (select id,survey_year from  ref_university where survey_year =0  " +
            "except " +
            "select  university_id,survey_year from form_upload where survey_year =0 and form_id='form1' ) as t1 " +
            " on t1.id=um.state_level_body where um.role_id=:roleId and  um.state_level_body_institute is null  and um.state_level_body is not null ";


    public String FORM_UPLOAD_UN_FILED_COUNT_STANDALONE = "  select count(*)  " +
            " from  " +
            " (select id,survey_year from  ref_standalone_institution where survey_year =0  " +
            "except " +
            "select  standalone_institution_id,survey_year from form_upload where survey_year =0 and form_id='form3' ) as t1 " +
            "  join user_master um on t1.id=cast(um.state_level_body_institute as integer) " +
            "  left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer)  " +
            "  left join ref_user_status us on (us.id=um.status_id ) " +
            "  left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "  left join ref_state s on s.st_code=um.state_code " +
            "   " +
            " where  um.role_id=:roleId and um.state_level_body_institute  is not null ";
    public String FORM_UPLOAD_UN_FILED_STANDALONE = "  select user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            " c.name as institution_name,aishe_code,status_id,  " +
            " CASE WHEN user_id is not null then null else null end as university_name,  " +
            " CASE WHEN user_id is not null then false else null end as form_id,urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code,um.document_pdf,um.document_name,um.state_code,um.name ,um.approved_datetime,um.approved_by,sta_type.type      " +
            " from  " +
            " (select id,survey_year from  ref_standalone_institution where survey_year =0  " +
            "except " +
            "select  standalone_institution_id,survey_year from form_upload where survey_year =0 and form_id='form3' ) as t1 " +
            "  join user_master um on t1.id=cast(um.state_level_body_institute as integer) " +
            "  left join ref_standalone_institution c on c.id=cast(um.state_level_body_institute as integer)  left join ref_state_body sta_type on c.statebodyid=sta_type.id  " +
            "  left join ref_user_status us on (us.id=um.status_id ) " +
            "  left join ref_user_management_role urm on urm.role_id=um.role_id  " +
            "  left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "   " +
            " where  um.role_id=:roleId and um.state_level_body_institute  is not null ";
    public String FORM_UPLOAD_UN_FILED_COLLEGE = "select  user_id, um.role_id, first_name ,middle_name, last_name ,s.name as state_name, is_approved, " +
            "             c.name as institution_name,aishe_code,status_id, " +
            "             ru.name as university_name,CASE WHEN user_id is not null then false else null end as form_id, urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name , rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name,um.approved_datetime,um.approved_by ,clg_type.type  from  " +
            "            (select id,survey_year from college where survey_year =0  " +
            "             except  " +
            "             select  college_institution_id,survey_year from form_upload where survey_year =0 and form_id='form2') as t1 " +
            "             left join user_master um on t1.id=cast(um.state_level_body_institute as integer) " +
            "             left join college c on c.id=cast(um.state_level_body_institute as integer)  left join ref_university_college_type clg_type on c.type_id=clg_type.id "+
            "             left join ref_university ru on ru.id=um.state_level_body " +
            "             left join ref_user_status us on (us.id=um.status_id and um.state_level_body_institute  is not null and um.state_level_body is not null) " +
            "             left join ref_user_management_role urm on urm.role_id=um.role_id   " +
            "             left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "             where  um.role_id=:roleId";
    public String FORM_UPLOAD_UN_FILED_COUNT_COLLEGE = "select count(*) from  " +
            "            (select id,survey_year from college where survey_year =0  " +
            "             except  " +
            "             select  college_institution_id,survey_year from form_upload where survey_year =0 and form_id='form2') as t1 " +
            "             left join user_master um on t1.id=cast(um.state_level_body_institute as integer) " +
            "             left join college c on c.id=cast(um.state_level_body_institute as integer) " +
            "             left join ref_university ru on ru.id=um.state_level_body " +
            "             left join ref_user_status us on (us.id=um.status_id and um.state_level_body_institute  is not null and um.state_level_body is not null) " +
            "             left join ref_user_management_role urm on urm.role_id=um.role_id   " +
            "             left join ref_state s on s.st_code=um.state_code " +
            " left join ref_district rd on rd.dist_code=um.address_district_code "+
            "             where  um.role_id=:roleId";

    public String IS_DCF_APPLICABLE_UNIVERSITY = "select COUNT(*) from public.ref_university ru where   ru.is_dcf_applicable= true ";
    public String IS_DCF_APPLICABLE_COLLEGE = "select COUNT(*) from public.college c where c.is_dcf_applicable =true ";
    public String IS_DCF_APPLICABLE_STANDALONE = "select COUNT(*) from public.ref_standalone_institution si ";
    public String TOTAL_FORM_UPLOAD_UNIVERSITY = "select count(*) from public.form_upload fu left join " +
            "public.ref_university ru on ru.id =fu.university_id " +
            "where ru.is_dcf_applicable= true ";
    public String TOTAL_FORM_UPLOAD_COLLEGE = "select count(*) from public.form_upload fu left join " +
            "public.college c on c.id =fu.college_institution_id " +
            "where c.is_dcf_applicable =true";
    public String TOTAL_FORM_UPLOAD_STANDALONE = "select count(*) from public.form_upload fu left join " +
            "public.ref_standalone_institution si on si.id =fu.standalone_institution_id ";

    public String EXPECTED_AISHE_CODE_AND_NAME_OF_UNIVERSITY = "select 'U-'||id AS id,name from public.ref_university ru where  ru.is_dcf_applicable= true ";
    public String EXPECTED_AISHE_CODE_AND_NAME_OF_COLLEGE = "select 'C-'||id as id ,name  from public.college c where c.is_dcf_applicable =true ";
    public String EXPECTED_AISHE_CODE_AND_NAME_OF_STANDALONE = "select 'S-'||id AS id ,name from public.ref_standalone_institution si ";

    public String SUBMITTED_AISHE_CODE_AND_NAME_OF_UNIVERSITY = "select 'U-'||ru.id as id ,ru.name from public.form_upload fu left join " +
            "public.ref_university ru on ru.id =fu.university_id " +
            "where ru.is_dcf_applicable= true ";
    public String SUBMITTED_AISHE_CODE_AND_NAME_OF_COLLEGE = "select 'C-'||c.id as id ,c.name from public.form_upload fu left join " +
            "public.college c on c.id =fu.college_institution_id " +
            "where c.is_dcf_applicable =true";
    public String SUBMITTED_AISHE_CODE_AND_NAME_OF_STANDALONE = "select 'S-'||si.id as id ,si.name from public.form_upload fu left join " +
            "public.ref_standalone_institution si on si.id =fu.standalone_institution_id ";

    public String BASIC_FILLED_UNIVERSITY = "select COUNT(*) from public.university ru where  ru.id is not null  ";
    public String BASIC_FILLED_COLLEGE = "select COUNT(*) from public.college_institution c where c.id is not null   ";
    public String BASIC_FILLED_STANDALONE = "select COUNT(*) from public.standalone_institution si   ";


    public String FORM_UPLOAD_UNIVERSITY = "select count(fu.id) , cast(fu.upload_date as date)  from public.form_upload fu left join" +
            " public.ref_university ru on ru.id =fu.university_id" +
            " where ru.is_dcf_applicable= true ";
    public String FORM_UPLOAD_COLLEGE = "select count(fu.id) , cast(fu.upload_date as date)  from public.form_upload fu left join " +
            " public.college c on c.id =fu.college_institution_id" +
            "  where c.is_dcf_applicable =true ";
    public String FORM_UPLOAD_STANDALONE = "select count(fu.id) , cast(fu.upload_date as date) from public.form_upload fu left join" +
            " public.ref_standalone_institution si on si.id =fu.standalone_institution_id ";

    public String IS_DCF_APPLICABLE_UNIVERSITY_NAME = "select name from public.ref_university ru where survey_year = (select max(survey_year)from public.ref_university where id='";
    public String IS_DCF_APPLICABLE_COLLEGE_NAME = "select NAME from public.college c where survey_year=(select max(survey_year)from college where id= '";
    public String IS_DCF_APPLICABLE_STANDALONE_NAME = "select NAME from public.ref_standalone_institution si where  survey_year= (select max(survey_year)from public.ref_standalone_institution where id= '";
	public String OTHERS_USER_MASTER_REQUEST =  "select user_id, um.role_id, '' as fn ,'' as mn, '' as ln ,s.name as state_name, is_approved,\n" +
            "CASE \n" +
            "WHEN um.state_level_body_institute  is not null or um.request_id is not null then\n" +
            "     case \n" +
            "            when  um.aishe_code is null and  sird.name is not null then sird.name\r\n"
            + "           when  um.aishe_code is null and  cird.name is not null then cird.name\r\n"
            + "           when  um.aishe_code like '%C-%' then c.name \r\n"
            + "           when  um.aishe_code like '%S-%' then si.name end \n" +
            "end\n" +
            "\n" +
            "as institution_name,\n" +
            " um.aishe_code as  aishe_code,status_id,ru.name as university_name, null as formupload \n" +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,\n" +
            "um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name ,\n" +
            "rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name " +
            ",case WHEN um.state_level_body_institute  is not null or um.state_level_body is not null then\n" +
            " case when  um.state_level_body_institute  is not null and um.state_level_body is not null and 'C-'|| um.state_level_body_institute=um.aishe_code then clg_type.type\n" +
            " when  um.state_level_body_institute  is not null and um.state_level_body is  null and  'S-'|| um.state_level_body_institute=um.aishe_code then sta_type.type\n" +
            " when  um.state_level_body is not null and um.state_level_body_institute  is null  then   uni_type.type\n" +
            " end  end as category_type from user_master_request um  \n" +
            "left join ref_user_status us on us.id=um.status_id \n" +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  \n" +
            "left join ref_state s on s.st_code=um.state_code"
            + " left join request_details red on red.id = um.request_id left join college_institution_request_details cird ON cird.request_id = red.id \r\n"
            + " left join standalone_institution_request_details sird ON sird.request_id = red.id \n" +
            "left join ref_district rd on rd.dist_code=um.address_district_code\n" +
            "left join ref_university ru on ru.id=um.state_level_body and ru.survey_year=(select max(survey_year) from ref_university where id=um.state_level_body)\n" +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) and c.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_standalone_institution si on si.id= cast(um.state_level_body_institute as integer) and si.survey_year=(select max(survey_year) from ref_standalone_institution where id=cast(um.state_level_body_institute as integer))\n" +
            " left join ref_university_college_type clg_type on c.type_id=clg_type.id\n" +
            "left join ref_university_type uni_type on ru.type_id=uni_type.id\n" +
            "left join ref_state_body sta_type on si.statebodyid=sta_type.id  "+
            "where um.role_id=:roleId and aishe_code is not null ";
	public String OTHERS_USER_MASTER_LOGS =  "select user_id, um.role_id, '' as fn ,'' as mn, '' as ln ,s.name as state_name, is_approved,\n" +
            "CASE \n" +
            "WHEN um.state_level_body_institute  is not null then\n" +
            "     case \n" +
            "           when  um.aishe_code like '%C-%' then c.name\n" +
            "           when  um.aishe_code like '%S-%' then si.name  \n" +
            "     end \n" +
            "end\n" +
            "\n" +
            "as institution_name, um.aishe_code as aishe_code,status_id,ru.name as university_name, null as formupload \n" +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,\n" +
            "um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name ,\n" +
            "rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name " +
            ",case WHEN um.state_level_body_institute  is not null or um.state_level_body is not null then\n" +
            "case when  um.state_level_body_institute  is not null and um.state_level_body is not null and 'C-'|| um.state_level_body_institute=um.aishe_code then clg_type.type\n" +
            "when  um.state_level_body_institute  is not null and um.state_level_body is  null and  'S-'|| um.state_level_body_institute=um.aishe_code then sta_type.type\n" +
            "when  um.state_level_body is not null and um.state_level_body_institute  is null  then   uni_type.type\n" +
            "end  end    as category_type from user_master_log um  \n" +
            "left join ref_user_status us on us.id=um.status_id \n" +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  \n" +
            "left join ref_state s on s.st_code=um.state_code\n" +
            "left join ref_district rd on rd.dist_code=um.address_district_code\n" +
            "left join ref_university ru on ru.id=um.state_level_body and ru.survey_year=(select max(survey_year) from ref_university where id=um.state_level_body)\n" +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) and c.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_standalone_institution si on si.id= cast(um.state_level_body_institute as integer) and si.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_university_college_type clg_type on c.type_id=clg_type.id\n" +
            "left join ref_university_type uni_type on ru.type_id=uni_type.id\n" +
            "left join ref_state_body sta_type on si.statebodyid=sta_type.id "+
            " where um.role_id=:roleId";
	
	public String OTHERS_USER_MASTER_LOGS_ROLEID_1 =  "select user_id, um.role_id, '' as fn ,'' as mn, '' as ln ,s.name as state_name, is_approved,\n" +
            "CASE \n" +
            "WHEN um.state_level_body_institute  is not null then\n" +
            "     case \n" +
            "           when  um.aishe_code like '%C-%' then c.name\n" +
            "           when  um.aishe_code like '%S-%' then si.name  \n" +
            "     end \n" +
            "end\n" +
            "\n" +
            "as institution_name, um.aishe_code as aishe_code,status_id,ru.name as university_name, null as formupload \n" +
            ",urm.level,us.status ,um.address_line1,um.address_line2,um.city,um.phone_landline,um.phone_mobile,um.email_id,\n" +
            "um.state_level_body,um.state_level_body_institute,um.body_type,um.std_code,um.alt_email_id,um.gender ,urm.role_name ,\n" +
            "rd.name as districtname ,rd.dist_code ,um.document_pdf,um.document_name,um.state_code,um.name," +
            ",case WHEN um.state_level_body_institute  is not null or um.state_level_body is not null then\n" +
            "case when  um.state_level_body_institute  is not null and um.state_level_body is not null and 'C-'|| um.state_level_body_institute=um.aishe_code then clg_type.type\n" +
            "when  um.state_level_body_institute  is not null and um.state_level_body is  null and  'S-'|| um.state_level_body_institute=um.aishe_code then sta_type.type\n" +
            "when  um.state_level_body is not null and um.state_level_body_institute  is null  then   uni_type.type\n" +
            "end  end    as category_type   from user_master_log um  \n" +
            "left join ref_user_status us on us.id=um.status_id \n" +
            "left join ref_user_management_role urm on urm.role_id=um.role_id  \n" +
            "left join ref_state s on s.st_code=um.state_code\n" +
            "left join ref_district rd on rd.dist_code=um.address_district_code\n" +
            "left join ref_university ru on ru.id=um.state_level_body and ru.survey_year=(select max(survey_year) from ref_university where id=um.state_level_body)\n" +
            "left join college c on c.id=cast(um.state_level_body_institute as integer) and c.survey_year=(select max(survey_year) from college where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_standalone_institution si on si.id= cast(um.state_level_body_institute as integer) and si.survey_year=(select max(survey_year) from ref_standalone_institution where id=cast(um.state_level_body_institute as integer))\n" +
            "left join ref_university_college_type clg_type on c.type_id=clg_type.id\n" +
            "left join ref_university_type uni_type on ru.type_id=uni_type.id\n" +
            "left join ref_state_body sta_type on si.statebodyid=sta_type.id " +
            "where um.role_id=:roleId";


    String collegeQuery="INSERT INTO public.college(\n" +
            "\tid, name, university_id, district_code, state_code, type_id, survey_year, is_dcf_applicable, user_by, special_permission, permission_on_date, management_id, location)\n" +
            "\tSELECT id, name, university_id, district_code, state_code, type_id, :surveyYear, is_dcf_applicable, user_by, special_permission, permission_on_date, management_id, location\n" +
            "\tFROM public.college WHERE survey_year=:maxSurveyYear;";

    String standaloneQuery="INSERT INTO public.ref_standalone_institution(\n" +
            "\tstatecode, name, id, statebodyid, survey_year, district_code, user_by, special_permission, permission_on_date, management_id, location)\n" +
            "\tSELECT statecode, name, id, statebodyid, :surveyYear, district_code, user_by, special_permission, permission_on_date, management_id, location\n" +
            "\tFROM public.ref_standalone_institution WHERE survey_year=:maxSurveyYear;";

    String universityQuery="INSERT INTO public.ref_university(\n" +
            "\tid, name, statecode, survey_year, is_dcf_applicable, type_id, district_code, user_by, special_permission, permission_on_date, management_id, location, sub_type_id)\n" +
            "\tSELECT id, name, statecode, :surveyYear, is_dcf_applicable, type_id, district_code, user_by, special_permission, permission_on_date, management_id, location, sub_type_id\n" +
            "\tFROM public.ref_university WHERE survey_year=:maxSurveyYear;";

    
}
