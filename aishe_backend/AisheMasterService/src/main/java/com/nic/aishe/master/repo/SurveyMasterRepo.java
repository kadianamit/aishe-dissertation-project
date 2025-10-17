package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.SurveyMaster;

public interface SurveyMasterRepo extends JpaRepository<SurveyMaster, Integer> {
	
	@Query(value = "select * from survey_master order by survey_year desc",nativeQuery = true)
	List<SurveyMaster> findAllSurvey();
	@Query(value = "select MAX(survey_year) from survey_master",nativeQuery = true)
	Integer maxSurvey();
}
