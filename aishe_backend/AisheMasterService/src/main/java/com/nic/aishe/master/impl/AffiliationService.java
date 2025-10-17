package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.InstituteAffiliation;
import com.nic.aishe.master.repo.AffiliationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AffiliationService {

	
	@Autowired(required=false)
	private AffiliationRepo affiliationRepo;

	public InstituteAffiliation addAffiliation(InstituteAffiliation Affiliation) {
		return affiliationRepo.save(Affiliation);
	}
	
	public List<InstituteAffiliation> getAllAffiliation(){
        return affiliationRepo.findAll();
	}
	
	public InstituteAffiliation getOneAffiliation(Integer id){
	return affiliationRepo.findById(id).get();
	}
	
	public Boolean deleteOneAffiliation(Integer id) {
		InstituteAffiliation Affiliations = affiliationRepo.findById(id).get();
		if(Affiliations!=null) {
			affiliationRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAllAffiliation() {
		affiliationRepo.deleteAll();
	}


	
}
