package com.nic.aishe.master.impl;

import com.nic.aishe.master.entity.DiplomaType;
import com.nic.aishe.master.repo.DiplomaCourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DiplomaCourseService {

	@Autowired(required=false)
	private DiplomaCourseRepo DiplomaCourseRepo;

	public DiplomaType adddiplomaCourse(DiplomaType diplomaCourse) {
		return DiplomaCourseRepo.save(diplomaCourse);
	}
	
	public List<DiplomaType> getAlldiplomaCourse(){
        return DiplomaCourseRepo.findAll();
	}	
	
	public DiplomaType getOnediplomaCourse(Integer id){
	return DiplomaCourseRepo.findById(id).get();
	}
	
	public Boolean deleteOnediplomaCourse(Integer id) {
		DiplomaType diplomaCourses = DiplomaCourseRepo.findById(id).get();
		if(diplomaCourses!=null) {
			DiplomaCourseRepo.deleteById(id);
			return true;
		}
		return false;	}
	
	public void deleteAlldiplomaCourse() {
		DiplomaCourseRepo.deleteAll();
	}


	
}
