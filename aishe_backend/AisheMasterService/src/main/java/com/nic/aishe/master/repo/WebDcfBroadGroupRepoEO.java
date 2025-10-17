package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.WebDcfBroadDisciplineEO;
@Repository
public interface WebDcfBroadGroupRepoEO extends JpaRepository<WebDcfBroadDisciplineEO, String> {

	@Query(value = "select * from webdcf.programme_bdgc where group_category_id=?1",nativeQuery = true)
	List<WebDcfBroadDisciplineEO> findAllGroupByCategoryId(String id);

}