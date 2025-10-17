package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.StaffCount;
@Repository
public interface StaffCountRepo extends JpaRepository<StaffCount, Integer> {
	
	@Query(value="select max(id) from non_teaching_staff_count",nativeQuery = true)
	Integer maxId();
}