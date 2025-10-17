package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nic.aishe.master.entity.Management;

public interface ManagementRepo extends JpaRepository<Management, Integer> {
	
	@Query(value = "SELECT * FROM course c WHERE c.id = :id",  nativeQuery = true)
	List<Management> findAllById(@Param("id") Integer id);
	
}
