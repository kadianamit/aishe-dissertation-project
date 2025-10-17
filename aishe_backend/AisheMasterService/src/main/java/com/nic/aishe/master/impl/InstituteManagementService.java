package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.InstituteManagement;
import com.nic.aishe.master.repo.InstituteManagementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstituteManagementService {

	@Autowired(required=false)
	private InstituteManagementRepo instituteManagementRepo;

	public InstituteManagement addManagement(InstituteManagement Management) {
		return instituteManagementRepo.save(Management);
	}
	
	public List<InstituteManagement> getAllManagement(){
        return instituteManagementRepo.findAll();
	}
	
	public InstituteManagement getOneManagement(Integer id){
	return instituteManagementRepo.findById(id).get();
	}
	
	public Boolean deleteOneManagement(Integer id) {
		InstituteManagement Managements = instituteManagementRepo.findById(id).get();
		if(Managements!=null) {
			instituteManagementRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllManagement() {
		instituteManagementRepo.deleteAll();
	}


	
}
