package com.nic.aishe.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nic.aishe.master.entity.ProgramName;
@Repository
public interface ProgramNameRepo extends JpaRepository<ProgramName, String> {

//	List<ProgramName> findByCourseLevelId(String id);

	@Query(value = "select * from ref_programme c  where c.is_active is true and c.course_level_id =:courseLevelId and"
			+ " id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') ", nativeQuery = true)
	List<ProgramName> findByLevelId(String courseLevelId);
	
	@Query(value = "select * from ref_programme c  where c.is_active is true and "
			+ " id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') ", nativeQuery = true)
	List<ProgramName> findAllProgramme();
	
	@Query(value = "select * from ref_programme c  where c.is_active is true and c.teacher_highest_qualification_id =:teacherHighestQualificationId and"
			+ " id not in('238','239','240','241','242','243','244','245','246','247','248','249','250','251','252') ", nativeQuery = true)
	List<ProgramName> findByTeacherHighestQualificationId(Integer teacherHighestQualificationId);

}