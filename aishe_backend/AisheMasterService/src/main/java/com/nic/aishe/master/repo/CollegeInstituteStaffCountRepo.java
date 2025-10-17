package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.CollegeInstitutionNonTeachingStaffCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeInstituteStaffCountRepo extends JpaRepository<CollegeInstitutionNonTeachingStaffCount,Integer> {
    @Query(value="select max(non_teaching_staff_count_id) from college_institution_non_teaching_staff_count",nativeQuery = true)
    Integer maxId();
}
