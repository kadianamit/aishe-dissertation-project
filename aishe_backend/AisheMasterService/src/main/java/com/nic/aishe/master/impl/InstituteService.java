package com.nic.aishe.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.aishe.master.entity.InstituteBasicInfo;
import com.nic.aishe.master.repo.InstituteRepo;

@Service
public class InstituteService {
	
	@Autowired(required=false)
	private InstituteRepo InstituteRepo;
	
	public InstituteBasicInfo addInstitute(InstituteBasicInfo Institute) {
		
		return InstituteRepo.save(Institute);
	}
	
	public List<InstituteBasicInfo> getAllInstitute(){
        return InstituteRepo.findAll();
	}
	
	public InstituteBasicInfo getOneInstitute(Integer id){
	return InstituteRepo.findById(id).get();
	}
		
}
