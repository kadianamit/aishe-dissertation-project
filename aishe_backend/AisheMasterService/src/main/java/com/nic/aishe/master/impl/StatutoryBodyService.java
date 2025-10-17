package com.nic.aishe.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nic.aishe.master.entity.StatutoryBody;
import com.nic.aishe.master.repo.StatutoryBodyRepo;
@Service
public class StatutoryBodyService {

	
	@Autowired(required=false)
	private StatutoryBodyRepo statutoryBodyRepo;

	public StatutoryBody addStatutoryBody(StatutoryBody StatutoryBody) {
		return statutoryBodyRepo.save(StatutoryBody);
	}
	
	public List<StatutoryBody> getAllStatutoryBody(){
        return statutoryBodyRepo.findAll();
	}
	
	public StatutoryBody getOneStatutoryBody(Integer id){
	return statutoryBodyRepo.findById(id).get();
	}
	
	public Boolean deleteOneStatutoryBody(Integer id) {
		StatutoryBody StatutoryBodys = statutoryBodyRepo.findById(id).get();
		if(StatutoryBodys!=null) {
			statutoryBodyRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllStatutoryBody() {
		statutoryBodyRepo.deleteAll();
	}

	public List<StatutoryBody> getAllStatutoryBodyForState(String stateCode) {
		return statutoryBodyRepo.getAllStatutoryBodyForState(stateCode);
	}

	/*
	 * public List<StatutoryBody>
	 * getAllStatutoryBodyForStateAndStandaloneBodyType(String stateCode, Integer
	 * standaloneBodyTypeId) { return
	 * statutoryBodyRepo.getAllStatutoryBodyForStateAndStandaloneBodyType(stateCode,
	 * standaloneBodyTypeId); }
	 */


	
}
