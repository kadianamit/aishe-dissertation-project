package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.StandaloneInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StandaloneInFoRapo extends JpaRepository<StandaloneInfo,Integer> {

    @Query(value="select * from standalone_institution c  where c.id=:id and c.survey_Year=:surveyYear" ,nativeQuery = true)
    StandaloneInfo getStandaloneBySurveyYearAndId(Integer id,Integer surveyYear);
}
