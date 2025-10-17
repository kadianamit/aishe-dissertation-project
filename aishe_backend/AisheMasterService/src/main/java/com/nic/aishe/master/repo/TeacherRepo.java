package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.Teacher;

public interface TeacherRepo extends JpaRepository<Teacher, Integer> {
	//List<Teacher> findByTeachingStaff(TeachingStaff Teacher);
	List<Teacher> findByTeachingStaffId(Integer teacherId);
	//List<Teacher> findByTeacherId(Integer teacherId);
	
	@Query(value="select max(id) from teacher",nativeQuery = true)
	Integer maxId(); 
	
}
