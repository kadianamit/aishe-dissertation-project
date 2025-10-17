package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefScholarshipSchemeEO;
@Repository
public interface RefScholarshipSchemeRepo extends JpaRepository<RefScholarshipSchemeEO, Integer> {

	@Query(value="select * from ref_scholarship_scheme where ((state_id =:stateCode and survey_year =:surveyYear) or id=99999 or id=999999) and status is true",nativeQuery = true)
	List<RefScholarshipSchemeEO> findAllRefScholarshipSchemeByStateAndSurveyYear(@Param("stateCode")String stateCode, @Param("surveyYear")Integer surveyYear);

	@Query(value="select * from ref_scholarship_scheme where status is true",nativeQuery = true)
	List<RefScholarshipSchemeEO> findAllRefScholarshipScheme();

	@Query(value="select * from ref_scholarship_scheme where survey_year =:surveyYear and status is true",nativeQuery = true)
	List<RefScholarshipSchemeEO> findAllRefScholarshipSchemeBySurveyYear(Integer surveyYear);

	@Query(value="select * from ref_scholarship_scheme where ((state_id =:stateCode) or id=99999 or id=999999) and status is true",nativeQuery = true)
	List<RefScholarshipSchemeEO> findAllRefScholarshipSchemeByState(String stateCode);

}