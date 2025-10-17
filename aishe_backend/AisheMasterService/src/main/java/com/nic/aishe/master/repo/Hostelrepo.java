package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.StudentHostel;
@Repository
public interface Hostelrepo extends JpaRepository<StudentHostel, Integer> {

	@Query(value="select max(id) from student_hostel",nativeQuery = true)
	Integer maxid();
}