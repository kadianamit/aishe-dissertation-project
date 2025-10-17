package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.NodalOfficer;

public interface NodalOfficerRepo extends JpaRepository<NodalOfficer, Integer> {
	

	@Query(value="select max(id) from nodal_officer",nativeQuery = true)
	Integer maxvalue();
	


}
