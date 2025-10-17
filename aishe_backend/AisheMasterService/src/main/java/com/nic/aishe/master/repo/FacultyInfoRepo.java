package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.Faculty;
import com.nic.aishe.master.entity.FacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyInfoRepo extends JpaRepository<FacultyInfo, Integer> {

    List<Faculty> findByName(String name);

    @Query(value="select max(id) from faculty",nativeQuery = true)
    Integer maxId();
    //List<Faculty> findByCollegeInfoIdAndCollegeInfoSurveyYear(Integer id,Integer surveyYear);

    List<Faculty> findAllById (Integer id);
    
    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join college_institution_faculty uf on uf.faculty_id= fac.id  where college_institution_id = :aisheCode \r\n" + 
    		"and survey_year=:surveyYear and upper(fac.name)=:name and is_updated=false or college_institution_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false",nativeQuery = true)
    List<String> getNameByParams(@Param("name")String upperCase, @Param("surveyYear")Integer surveyYear, @Param("aisheCode")Integer collegeId,
    		@Param("LSY")Integer lsysurveyYear);

    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join standalone_institution_faculty uf on uf.faculty_id= fac.id  where standalone_institution_id = :aisheCode \r\n" + 
    		"and survey_year=:surveyYear and upper(fac.name)=:name and is_updated=false or standalone_institution_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false",nativeQuery = true)
    List<String> getStandaloneNameByParams(@Param("name")String upperCase, @Param("surveyYear")Integer surveyYear, @Param("aisheCode")Integer collegeId, 
    		@Param("LSY")Integer lsysurveyYear);

    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join university_faculty uf on uf.faculty_id= fac.id  where university_id = :aisheCode and survey_year=:surveyYear\r\n" + 
    		" and upper(fac.name)=:name and is_updated=false or university_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false",nativeQuery = true)
    List<String> getUniversityNameByParams(@Param("name")String upperCase, @Param("surveyYear")Integer surveyYear, @Param("aisheCode")String collegeId,
    		@Param("LSY")Integer lsysurveyYear);

    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join standalone_institution_faculty uf on uf.faculty_id= fac.id  where standalone_institution_id = :aisheCode \r\n" + 
    		"and survey_year=:surveyYear and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId or standalone_institution_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId",nativeQuery = true)
    List<String> getStandaloneNameByParamsId(@Param("facultyId")Integer facultyId,@Param("name")String upperCase, @Param("surveyYear")Integer surveyYear,
    		@Param("aisheCode")Integer collegeId,@Param("LSY") Integer lsysurveyYear
    		);

    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join college_institution_faculty uf on uf.faculty_id= fac.id  where college_institution_id = :aisheCode \r\n" + 
    		"and survey_year=:surveyYear and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId or college_institution_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId",nativeQuery = true)
    List<String> getNameByParamsId(@Param("facultyId")Integer facultyId,@Param("name")String upperCase, @Param("surveyYear")Integer surveyYear, @Param("aisheCode")Integer collegeId, 
    		@Param("LSY")Integer lsysurveyYear);

    @Query(value="select upper(name) from public.faculty fac\r\n" + 
    		"left join university_faculty uf on uf.faculty_id= fac.id  where university_id = :aisheCode \r\n" + 
    		"and survey_year=:surveyYear and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId or university_id = :aisheCode and survey_year=:LSY and upper(fac.name)=:name and is_updated=false and fac.id!=:facultyId",nativeQuery = true)
    List<String> getUniversityNameByParamsId(@Param("facultyId")Integer facultyId,@Param("name")String upperCase, @Param("surveyYear")Integer
    		surveyYear, @Param("aisheCode")String universityId, @Param("LSY")Integer lsysurveyYear);
    }