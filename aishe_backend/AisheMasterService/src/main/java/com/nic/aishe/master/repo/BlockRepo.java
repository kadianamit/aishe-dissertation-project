package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefBlockEO;
@Repository
public interface BlockRepo extends JpaRepository<RefBlockEO, Integer> {

	@Query(value = "SELECT  * from public.ref_block where district_code=:districtCode ",  nativeQuery = true)
	List<RefBlockEO> findAllBlock(String districtCode);

}