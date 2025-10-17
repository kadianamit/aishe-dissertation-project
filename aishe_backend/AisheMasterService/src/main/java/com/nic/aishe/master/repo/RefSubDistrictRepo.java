package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nic.aishe.master.entity.RefSubDistrictEO;

public interface RefSubDistrictRepo extends JpaRepository<RefSubDistrictEO, Integer> {

	@Query(value = "select * from ref_sub_district c  where c.lgd_state_code =:lgdStateCode ", nativeQuery = true)
	List<RefSubDistrictEO> findByLgdStateCode(Integer lgdStateCode);

	@Query(value = "select * from ref_sub_district c  where c.lgd_state_code =:lgdStateCode and c.lgd_district_code =:lgdDistrictCode ", nativeQuery = true)
	List<RefSubDistrictEO> findByLgdStateDistrictCode(Integer lgdStateCode, Integer lgdDistrictCode);

	@Query(value = "select * from ref_sub_district c  where c.lgd_district_code =:lgdDistrictCode ", nativeQuery = true)
	List<RefSubDistrictEO> findByLgdDistrictCode(Integer lgdDistrictCode);
}
