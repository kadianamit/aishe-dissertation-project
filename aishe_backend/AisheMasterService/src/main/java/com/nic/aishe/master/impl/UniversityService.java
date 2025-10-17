package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.University;
import com.nic.aishe.master.entity.UniversityApplicableDCF;
import com.nic.aishe.master.repo.UniversityApplicableDCFRepo;
import com.nic.aishe.master.repo.UniversityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UniversityService {

	
	@Autowired(required=false)
	private UniversityRepo UniversityRepo;

	@Autowired(required=false)
	private UniversityApplicableDCFRepo applicableDCFRepo;

	public University addUniversity(University University) {
		return UniversityRepo.save(University);
	}
	
	public List<University> getAllUniversity(Integer year){
        return UniversityRepo.findBySurveyYear(year);
	}

	public List<UniversityApplicableDCF> getAllApplicableUniversity(Integer year){
		return applicableDCFRepo.findBySurveyYear(year);
	}
	
	public List<University> getOneUniversity(String id){
	return UniversityRepo.findAllById(id);
	}
	
	public Boolean deleteOneUniversity(String id) {
		University Universitys = UniversityRepo.findById(id).get();
		if(Universitys!=null) {
			UniversityRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllUniversity() {
		UniversityRepo.deleteAll();
	}

	public List<UniversityApplicableDCF> getAllApplicableUniversityByState(Integer year, String stateCode) {
		return applicableDCFRepo.getAllApplicableUniversityByState(year,stateCode);
	}

	public List<UniversityApplicableDCF> getAllApplicableUniversityByStateDistrict(Integer year, String stateCode,
			String districtCode) {
		return applicableDCFRepo.getAllApplicableUniversityByStateDistrict(year,stateCode,districtCode);
	}
	
}
