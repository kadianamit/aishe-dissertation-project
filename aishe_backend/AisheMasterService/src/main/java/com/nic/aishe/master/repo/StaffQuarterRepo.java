package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.StaffQuarter;


public interface StaffQuarterRepo extends JpaRepository<StaffQuarter, Integer> {
	

	@Query(value="select max(id) from staff_quarter",nativeQuery = true)
	Integer maxvalue();
	


}
