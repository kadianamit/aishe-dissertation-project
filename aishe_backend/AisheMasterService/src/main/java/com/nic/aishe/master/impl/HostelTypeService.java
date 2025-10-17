package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.HostelType;
import com.nic.aishe.master.repo.HostelTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class HostelTypeService {

	@Autowired(required=false)
	private HostelTypeRepo HostelTypeRepo;

	public HostelType addType(HostelType Type) {
		return HostelTypeRepo.save(Type);
	}
	
	public List<HostelType> getAllType(){
        return HostelTypeRepo.findAll();
	}
	
	public HostelType getOneType(Integer id){
	return HostelTypeRepo.findById(id).get();
	}
	
	public Boolean deleteOneType(Integer id) {
		HostelType types = HostelTypeRepo.findById(id).get();
		if(types!=null) {
			HostelTypeRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllType() {
		HostelTypeRepo.deleteAll();
	}


	
}
