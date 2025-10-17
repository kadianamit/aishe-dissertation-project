package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Expenditure;
@Repository
public interface ExpenditureRepo extends JpaRepository<Expenditure, Integer> {

    Expenditure findBySurveyYearAndId(Integer surveyYear,Integer aisheCode);
   
    @Query(value="select max(id) from financial_expenditure",nativeQuery = true)
	Integer maxId();

    @Query(value="select id from financial_expenditure where survey_year=:surveyYear and aishe_code=:aisheCode and institute_type='C'",nativeQuery = true)
	Integer findFinanceExpenditureId(@Param("surveyYear")Integer year, @Param("aisheCode")String aishecode);

}