package com.nic.aishe.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.aishe.master.entity.StaffTypes;
import com.nic.aishe.master.repo.StaffTypesRepo;
@Service
public class StaffTypesService {

	
	@Autowired(required=false)
	private StaffTypesRepo staffTypesRepo;

	public StaffTypes addType(StaffTypes Type) {
		return staffTypesRepo.save(Type);
	}
	
	public List<StaffTypes> getAllType(){
        return staffTypesRepo.findAll();
	}
	
	public StaffTypes getOneType(Integer id){
	return staffTypesRepo.findById(id).get();
	}
	
	public Boolean deleteOneType(Integer id) {
		StaffTypes types = staffTypesRepo.findById(id).get();
		if(types!=null) {
			staffTypesRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllType() {
		staffTypesRepo.deleteAll();
	}


	
}
