package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Department;
@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {
	
	List<Department> findByName(String name);
	
	List<Department> findByCollegeInfoIdAndCollegeInfoSurveyYear(Integer id,Integer surveyYear);

	@Query(value="select max(id) from department",nativeQuery = true)
	Integer maxId();
}