package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Accreditation;

@Repository
public interface AccreditationRepo extends JpaRepository<Accreditation, Integer> {

 //   List<Accreditation> findBySurveyYearAndId(Integer surveyYear, Integer aisheCode);
}