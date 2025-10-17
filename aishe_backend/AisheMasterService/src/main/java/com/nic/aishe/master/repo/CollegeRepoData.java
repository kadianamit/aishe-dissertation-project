package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.CollegeInfoData;

@Repository
public interface CollegeRepoData extends JpaRepository<CollegeInfoData, Integer> {

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id", nativeQuery = true)
    List<CollegeInfoData> findAllById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM college_institution c WHERE c.id = :id and c.survey_year=:surveyYear", nativeQuery = true)
    CollegeInfoData findBySurveyYearAndId(Integer surveyYear, Integer id);

    CollegeInfoData findByStateCode(String stateCode);

}
