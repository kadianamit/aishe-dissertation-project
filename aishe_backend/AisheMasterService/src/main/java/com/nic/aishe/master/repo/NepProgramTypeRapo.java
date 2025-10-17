package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.NepProgramType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NepProgramTypeRapo extends JpaRepository<NepProgramType, Integer> {
}
