package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.FinanceIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FinanceIncomeRepo extends JpaRepository<FinanceIncome, Integer> {

    FinanceIncome findBySurveyYearAndId(Integer surveyYear, Integer aisheCode);

    @Query(value="select max(id) from financial_income",nativeQuery = true)
	Integer maxId();

    @Query(value="select id from financial_income where survey_year=:surveyYear and aishe_code=:aisheCode and institute_type='C' ",nativeQuery = true)
   	Integer findFinanceIncomeId(@Param("surveyYear")Integer year, @Param("aisheCode")String aishecode);
}
