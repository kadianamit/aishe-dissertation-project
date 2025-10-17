package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.Infrastructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InfrastructureRepo extends JpaRepository<Infrastructure, Integer> {

    Infrastructure findBySurveyYearAndId(Integer surveyYear, Integer aisheCode);

    @Query(value="select id from infrastructure where survey_year=:surveyYear and aishe_code=:aisheCode and institute_type='C'",nativeQuery = true)
   	Integer findInfrastructureId(@Param("surveyYear")Integer year, @Param("aisheCode")String aishecode);
}
