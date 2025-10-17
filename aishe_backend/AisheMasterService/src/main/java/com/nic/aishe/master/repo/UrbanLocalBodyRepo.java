package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefUrbanLocalBodyEO;
@Repository
public interface UrbanLocalBodyRepo extends JpaRepository<RefUrbanLocalBodyEO, Integer> {

	@Query(value = "SELECT  * from public.ref_urban_local_body where district_code=:districtCode ",  nativeQuery = true)
	List<RefUrbanLocalBodyEO> fetchAllUrbanLocalBody(String districtCode);

}