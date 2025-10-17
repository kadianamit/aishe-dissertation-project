package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefMappingDesignationNatureAppointment;
@Repository
public interface RefMappingDesignationNatureAppointmentRepo extends JpaRepository<RefMappingDesignationNatureAppointment, Integer> {

	@Query(value = "select * from ref_mapping_designation_natureappointment c  where c.teaching_staff_designation_id =:designationId", nativeQuery = true)
	List<RefMappingDesignationNatureAppointment> findByDesignationId(String designationId);

}