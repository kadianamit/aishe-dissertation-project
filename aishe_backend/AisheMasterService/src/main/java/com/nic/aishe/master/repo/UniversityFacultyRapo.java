package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.UniversityFacultyInfo;

@Repository
public interface UniversityFacultyRapo  extends JpaRepository<UniversityFacultyInfo,Integer> {
    @Query(value="select max(faculty_id) from university_faculty",nativeQuery = true)
    Integer maxId();

    @Query(value="select * from university_faculty c  where c.university_id=:id and c.survey_Year=:surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC" ,nativeQuery = true)
    List<UniversityFacultyInfo> findByUniversityFacultyInfoSurveyYear(String id, Integer surveyYear);

    @Query(value = "SELECT * FROM university_faculty c WHERE c.university_id = :id and c.survey_Year <> :surveyYear and is_updated=false or is_updated=null ORDER BY faculty_id DESC" ,  nativeQuery = true)
    List<UniversityFacultyInfo> findAllById(String id,Integer surveyYear);
}
