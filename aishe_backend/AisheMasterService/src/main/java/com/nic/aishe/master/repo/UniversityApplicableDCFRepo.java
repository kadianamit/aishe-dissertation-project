package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.UniversityApplicableDCF;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityApplicableDCFRepo  extends JpaRepository<UniversityApplicableDCF,Integer> {

    @Query(value="select * from ref_university c  where c.survey_Year=:surveyYear AND is_dcf_applicable=true " ,nativeQuery = true)
    List<UniversityApplicableDCF> findBySurveyYear(Integer surveyYear);

    @Query(value = "SELECT * FROM ref_university c WHERE c.id = :id",  nativeQuery = true)
    List<UniversityApplicableDCF> findAllById(@Param("id") String id);
    @Query(value="select * from ref_university c  where c.survey_Year=:surveyYear AND c.statecode =:stateCode AND is_dcf_applicable=true " ,nativeQuery = true)
    List<UniversityApplicableDCF> getAllApplicableUniversityByState(Integer surveyYear, String stateCode);

    @Query(value="select * from ref_university c  where c.survey_Year=:surveyYear AND c.statecode =:stateCode and c.district_code =:districtCode"
    		+ " AND is_dcf_applicable=true " ,nativeQuery = true)
	List<UniversityApplicableDCF> getAllApplicableUniversityByStateDistrict(Integer surveyYear, String stateCode,
			String districtCode);
}
