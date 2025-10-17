package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.ProgramGroupCategory;
@Repository
public interface ProgramGroupCategoryRepo extends JpaRepository<ProgramGroupCategory, String> {
	        
			List<ProgramGroupCategory> findAllById(@Param("id") String id);

			@Query(value = "SELECT  distinct broad_discipline_group_id from public.ref_programme_broad_discipline_group_and_category where broad_discipline_group_category_id=:categoryId \r\n"
					+ " and is_applicable is true "
					+ " and programme_id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') "
					+ "order by broad_discipline_group_id",  nativeQuery = true)
			List<String> findByCategoryId(String categoryId);

			@Query(value = "SELECT  distinct broad_discipline_group_id from public.ref_programme_broad_discipline_group_and_category where broad_discipline_group_category_id=:categoryId \r\n"
					+ " and programme_id=:programmeId and is_applicable is true "
					+ " and programme_id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') order by broad_discipline_group_id",  nativeQuery = true)
			List<String> findByCategoryAndProgrammeId(String categoryId, String programmeId);

			@Query(value = "SELECT  * from public.ref_programme_broad_discipline_group_and_category where programme_id=:programmeId and is_applicable is true"
					+ " and programme_id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') order by broad_discipline_group_id",  nativeQuery = true)
			List<ProgramGroupCategory> findAllDataById(String programmeId);
}