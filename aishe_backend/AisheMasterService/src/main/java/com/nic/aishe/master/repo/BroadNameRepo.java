package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.BroadCategory;
import com.nic.aishe.master.entity.BroadName;
@Repository
public interface BroadNameRepo extends JpaRepository<BroadName, String> {
	//@QueryHints({@QueryHint(name = "org.hibernate.cacheable", value = "true")})
	@Query(value = "SELECT  * from public.ref_broad_discipline_group ",  nativeQuery = true)
	List<BroadName> findAllType();
	 
	List<BroadName> findByCategoryId(BroadCategory categoryId);

	List<BroadName> findAllByCategoryIdId(String categoryId);

	@Query(value = "SELECT  * from public.ref_broad_discipline_group where id=:broadId ",  nativeQuery = true)
	BroadName findByCategoryId(String broadId);

//	List<BroadName> findByProgramName(ProgramName programName);
}