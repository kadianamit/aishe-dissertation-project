package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nic.aishe.master.entity.Remarks;

public interface RemarksRepo extends JpaRepository<Remarks, Integer> {
	
	
}
