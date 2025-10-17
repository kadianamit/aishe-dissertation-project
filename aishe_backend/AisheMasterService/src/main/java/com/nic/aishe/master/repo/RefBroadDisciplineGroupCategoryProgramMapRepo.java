package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefBlockEO;
import com.nic.aishe.master.entity.RefBroadDisciplineGroupCategoryProgramMapEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefBroadDisciplineGroupCategoryProgramMapRepo extends JpaRepository<RefBroadDisciplineGroupCategoryProgramMapEO, String> {
    @Query(value = "SELECT  * from public.ref_programme_broad_discipline_group_and_category where programme_id=:programmeId and is_applicable=true",  nativeQuery = true)
    List<RefBroadDisciplineGroupCategoryProgramMapEO> findAllByProgramIdApplicable(String programmeId);
    @Query(value = "SELECT  * from public.ref_programme_broad_discipline_group_and_category where is_applicable=true",  nativeQuery = true)
    List<RefBroadDisciplineGroupCategoryProgramMapEO> findAllApplicable();

}
