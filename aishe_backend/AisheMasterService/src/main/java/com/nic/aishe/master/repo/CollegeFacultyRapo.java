package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.CollegeFacultyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeFacultyRapo extends JpaRepository<CollegeFacultyInfo ,Integer> {

	@Query(value="select max(surveyYear) from CollegeFacultyInfo where collegeId=:collegeId and surveyYear<:currentSurveyYear",nativeQuery = false)
	Integer findSecondMaxSurveyYear(Integer collegeId,Integer currentSurveyYear);
}
