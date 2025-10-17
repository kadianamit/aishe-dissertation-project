package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.CollegeInfo;

@Repository
public interface CollegeRepo extends JpaRepository<CollegeInfo, Integer> {

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id", nativeQuery = true)
    List<CollegeInfo> findAllById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id and c.survey_year=:surveyYear", nativeQuery = true)
    CollegeInfo findBySurveyYearAndId(Integer surveyYear, Integer id);

    CollegeInfo findByStateCode(String stateCode);

}
