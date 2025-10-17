package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.UniversityInfoFaculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversityFacultyInfoRapo extends JpaRepository<UniversityInfoFaculty,Integer> {

	@Query(value="select max(surveyYear) from UniversityInfoFaculty where universityId=:universityId and surveyYear<:currentSurveyYear",nativeQuery = false)
	Integer findSecondMaxSurveyYear(String universityId,Integer currentSurveyYear);
}
