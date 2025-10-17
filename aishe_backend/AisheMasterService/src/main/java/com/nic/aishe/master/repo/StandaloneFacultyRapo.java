package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.StandaloneFacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StandaloneFacultyRapo extends JpaRepository<StandaloneFacultyInfo,Integer> {

	@Query(value="select max(surveyYear) from StandaloneFacultyInfo where standaloneId=:collegeId and surveyYear<:currentSurveyYear",nativeQuery = false)
	Integer findSecondMaxSurveyYear(Integer collegeId,Integer currentSurveyYear);
}
