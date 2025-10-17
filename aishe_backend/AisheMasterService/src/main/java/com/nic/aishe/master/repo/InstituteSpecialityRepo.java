package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.InstituteSpeciality;
@Repository
public interface InstituteSpecialityRepo extends JpaRepository<InstituteSpeciality, Integer> {

	@Query(value="select * from ref_speciality where status is true order by speciality asc",nativeQuery = true)
	List<InstituteSpeciality> findAllData();

}