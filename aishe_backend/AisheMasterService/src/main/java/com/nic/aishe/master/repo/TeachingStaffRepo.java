package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.TeachingStaff;
@Repository
public interface TeachingStaffRepo extends JpaRepository<TeachingStaff, Integer> {
	@Query(value="select max(id) from teaching_staff",nativeQuery = true)
	Integer maxId();
}