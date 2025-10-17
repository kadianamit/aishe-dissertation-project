package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.Ministry;
@Repository
public interface MinistryRepo extends JpaRepository<Ministry, Integer> {

	@Query(value = "SELECT  * from public.ref_ministry where status is true ",  nativeQuery = true)
	List<Ministry> findAllMinistry();

}