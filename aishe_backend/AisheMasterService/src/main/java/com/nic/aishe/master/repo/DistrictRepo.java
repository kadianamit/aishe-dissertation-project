package com.nic.aishe.master.repo;

import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.District;
@Repository
public interface DistrictRepo extends JpaRepository<District, Integer> {
	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	List<District> findByStateCode(String stateCode);

	@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	@Query(value = "SELECT  * from public.ref_district ",  nativeQuery = true)
	List<District> findAllDistrict();
}