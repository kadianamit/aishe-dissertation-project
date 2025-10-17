package com.nic.aishe.master.service;

import com.nic.aishe.master.entity.CollegeInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CollegeService {

	CollegeInfo findCollegeBySurveyYearAndAisheCode(Integer year, Integer aisheCde);

	Page<CollegeInfo> findAllCollege(int page,int size);
	
	CollegeInfo addCollege(CollegeInfo collegeInfo);

	CollegeInfo updateCollege(Integer aishecode,CollegeInfo collegeInfo ,CollegeInfo college);

	CollegeInfo updateCollegeAddress(CollegeInfo collegeInfo);

	List<CollegeInfo> findCollegeByAisheCode(Integer id);

}
