package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.CollegeInstitution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CollegeInstitutionRepo extends JpaRepository<CollegeInstitution, Integer> {

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id", nativeQuery = true)
    List<CollegeInstitution> findAllById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id and c.survey_year=:surveyYear", nativeQuery = true)
    CollegeInstitution findBySurveyYearAndId(Integer surveyYear, Integer id);

    CollegeInstitution findByStateCode(String stateCode);
}