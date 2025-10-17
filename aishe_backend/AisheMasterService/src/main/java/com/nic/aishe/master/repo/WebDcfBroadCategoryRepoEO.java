package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.WebDcfBroadCategoryEO;
@Repository
public interface WebDcfBroadCategoryRepoEO extends JpaRepository<WebDcfBroadCategoryEO, String> {

	@Query(value = "select * from webdcf.programme_bdc where programme_id=?1",nativeQuery = true)
	List<WebDcfBroadCategoryEO> findAllCategoryByProgramId(String id);


}