package com.nic.aishe.master.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.nic.aishe.master.entity.CollegeInstitutionAccreditation;

@Repository
public interface CollegeInstituteAccreditationRapo extends JpaRepository<CollegeInstitutionAccreditation, Integer> {

    @Query(value="select max(accreditation_id) from college_institution_accreditation",nativeQuery = true)
    Integer maxId();
}
