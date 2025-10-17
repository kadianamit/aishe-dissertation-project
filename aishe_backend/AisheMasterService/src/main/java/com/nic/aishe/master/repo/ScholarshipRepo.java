package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.ScholarshipInfo;
@Repository
public interface ScholarshipRepo extends JpaRepository<ScholarshipInfo, Integer> {

	@Query(value="select max(id) from scholarship",nativeQuery = true)
	Integer maxid();
}