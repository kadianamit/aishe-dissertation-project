package com.nic.aishe.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.aishe.master.entity.UniversityType;
import com.nic.aishe.master.repo.UniversityTypeRepo;
@Service
public class UniversityTypeService {

	
	@Autowired(required=false)
	private UniversityTypeRepo UniversityTypeRepo;

	public UniversityType addUniversityType(UniversityType university) {
		return UniversityTypeRepo.save(university);
	}
	
	public List<UniversityType> getAllUniversityType(){
        return UniversityTypeRepo.findAll();
	}
	
	public UniversityType getOneUniversityType(Integer id){
	return UniversityTypeRepo.findById(id).get();
	}
	
	public Boolean deleteOneUniversityType(Integer id) {
		UniversityType universitys = UniversityTypeRepo.findById(id).get();
		if(universitys!=null) {
			UniversityTypeRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllUniversityType() {
		UniversityTypeRepo.deleteAll();
	}


	
}
