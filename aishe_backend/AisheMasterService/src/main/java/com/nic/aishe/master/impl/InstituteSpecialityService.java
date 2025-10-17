package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.InstituteSpeciality;
import com.nic.aishe.master.repo.InstituteSpecialityRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstituteSpecialityService {

	
	@Autowired(required=false)
	private InstituteSpecialityRepo instituteSpecialityRepo;

	public InstituteSpeciality addSpeciality(InstituteSpeciality Speciality) {
		return instituteSpecialityRepo.save(Speciality);
	}
	
	public List<InstituteSpeciality> getAllSpeciality(){
        return instituteSpecialityRepo.findAllData();
	}
	
	public InstituteSpeciality getOneSpeciality(Integer id){
	return instituteSpecialityRepo.findById(id).get();
	}
	
	public Boolean deleteOneSpeciality(Integer id) {
		InstituteSpeciality Specialitys = instituteSpecialityRepo.findById(id).get();
		if(Specialitys!=null) {
			instituteSpecialityRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllSpeciality() {
		instituteSpecialityRepo.deleteAll();
	}


	
}
