package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.StandaloneInstitutionFacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StandaloneInstitutionFacultyRapo extends JpaRepository<StandaloneInstitutionFacultyInfo,Integer> {
    @Query(value="select max(faculty_id) from standalone_institution_faculty",nativeQuery = true)
    Integer maxId();

    @Query(value="select * from standalone_institution_faculty c  where c.standalone_institution_id=:id and c.survey_Year=:surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC" ,nativeQuery = true)
    List<StandaloneInstitutionFacultyInfo> findByStandaloneInstitutionFacultyInfoSurveyYear(Integer id, Integer surveyYear);

    @Query(value = "SELECT * FROM standalone_institution_faculty c WHERE c.standalone_institution_id = :id  and c.survey_Year <> :surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC ",  nativeQuery = true)
    List<StandaloneInstitutionFacultyInfo> findAllById(Integer id ,Integer surveyYear);
}
