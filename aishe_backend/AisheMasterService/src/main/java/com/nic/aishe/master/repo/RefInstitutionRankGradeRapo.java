package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefInstitutionRankGrade;
import com.nic.aishe.master.entity.RefMinistryOnosEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefInstitutionRankGradeRapo extends JpaRepository<RefInstitutionRankGrade, Integer> {
}
