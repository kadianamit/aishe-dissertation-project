package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nic.aishe.master.entity.University;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityRepo extends JpaRepository<University, String> {

    @Query(value = "SELECT * FROM university c WHERE c.id = :id", nativeQuery = true)
    List<University> findAllById(@Param("id") String id);

    List<University> findBySurveyYear(Integer year);

    @Query(value = "SELECT * FROM university c WHERE c.id = :id and c.survey_year =:surveyYear", nativeQuery = true)
    University findAllByIdAndSurveyYear(String id, Integer surveyYear);

}
