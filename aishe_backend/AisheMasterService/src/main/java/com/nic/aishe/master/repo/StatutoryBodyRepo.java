package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nic.aishe.master.entity.StatutoryBody;

public interface StatutoryBodyRepo extends JpaRepository<StatutoryBody, Integer> {
	
	@Query(value = "SELECT * FROM course c WHERE c.id = :id",  nativeQuery = true)
	List<StatutoryBody> findAllById(@Param("id") Integer id);

	
	@Query(value = "SELECT  * from public.ref_college_institution_statutory_body where (state_code=:stateCode or state_code ='00') ",  nativeQuery = true)
	List<StatutoryBody> getAllStatutoryBodyForState(String stateCode);

//	@Query(value = "SELECT  * from public.ref_college_institution_statutory_body where (state_code=:stateCode or state_code ='00') AND "
//			+ " (standalone_body_type_id=:standaloneBodyTypeId or standalone_body_type_id is null) ",  nativeQuery = true)
	//@Query(value = "SELECT  * from public.ref_college_institution_statutory_body where (state_code=:stateCode or state_code ='00')",  nativeQuery = true)
	//List<StatutoryBody> getAllStatutoryBodyForStateAndStandaloneBodyType(String stateCode, Integer standaloneBodyTypeId);
	
}
