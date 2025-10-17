package com.nic.aishe.master.impl;

import com.nic.aishe.master.repo.CollegeInstituteStaffCountRepo;
import com.nic.aishe.master.service.StaffCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class StaffCountServiceImpl implements StaffCountService{

	@Autowired
	private CollegeInstituteStaffCountRepo instituteStaffCountRepo;

	@Override
	public Integer maxId() {
		return instituteStaffCountRepo.maxId();
	}

}
