package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.BroadCategory;
@Repository
public interface BroadCategoryRepo extends JpaRepository<BroadCategory, String> {
	
	@Query(value = "select * from ref_broad_discipline_group_category where id=?1",nativeQuery = true)
	List<BroadCategory> getByid(@Param("id") String id);

	//List<BroadCategory> findByProgramName(ProgramName programName);

}