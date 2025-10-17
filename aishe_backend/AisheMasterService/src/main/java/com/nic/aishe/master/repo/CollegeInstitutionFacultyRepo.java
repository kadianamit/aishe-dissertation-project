package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.CollegeInstitutionFacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollegeInstitutionFacultyRepo extends JpaRepository<CollegeInstitutionFacultyInfo, Integer> {
    @Query(value="select max(faculty_id) from college_institution_faculty",nativeQuery = true)
    Integer maxId();

    @Query(value="select * from college_institution_faculty c  where c.college_institution_id=:id and c.survey_Year=:surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC" ,nativeQuery = true)
    List<CollegeInstitutionFacultyInfo> findByCollegeInstitutionFacultyInfoSurveyYear(Integer id, Integer surveyYear);

    @Query(value = "SELECT * FROM college_institution_faculty c WHERE c.college_institution_id = :id  and c.survey_Year <> :surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC",  nativeQuery = true)
    List<CollegeInstitutionFacultyInfo> findAllById(Integer id ,Integer surveyYear);
}
