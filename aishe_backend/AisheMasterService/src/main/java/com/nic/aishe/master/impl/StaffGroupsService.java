package com.nic.aishe.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.aishe.master.entity.StaffGroups;
import com.nic.aishe.master.repo.StaffGroupsRepo;

@Service
public class StaffGroupsService {
	
	@Autowired(required=false)
	private StaffGroupsRepo staffGroupsRepo;
	
	public StaffGroups addGroup(StaffGroups group) {
		
		return staffGroupsRepo.save(group);
	}
	
	public List<StaffGroups> getAllGroup(){
        return staffGroupsRepo.findAll();
	}
	
	public StaffGroups getOneGroup(Integer id){
	return staffGroupsRepo.findById(id).get();
	}
		
}
