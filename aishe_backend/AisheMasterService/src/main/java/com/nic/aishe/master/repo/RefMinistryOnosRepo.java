package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.RefMinistryOnosEO;

@Repository
public interface RefMinistryOnosRepo extends JpaRepository<RefMinistryOnosEO, Integer> {

	@Query(value = "select * from ref_ministry_onos c  where c.status is true", nativeQuery = true)
	List<RefMinistryOnosEO> findAllActive();

}