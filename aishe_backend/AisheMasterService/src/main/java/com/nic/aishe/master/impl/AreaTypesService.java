package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.AreaType;
import com.nic.aishe.master.repo.AreaTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AreaTypesService {

	
	@Autowired(required=false)
	private AreaTypeRepo AreaTypeRepo;

	public AreaType addType(AreaType Type) {
		return AreaTypeRepo.save(Type);
	}
	
	public List<AreaType> getAllType(){
        return AreaTypeRepo.findAll();
	}
	
	public AreaType getOneType(Integer id){
	return AreaTypeRepo.findById(id).get();
	}
	
	public Boolean deleteOneType(Integer id) {
		AreaType types = AreaTypeRepo.findById(id).get();
		if(types!=null) {
			AreaTypeRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllType() {
		AreaTypeRepo.deleteAll();
	}


	
}
