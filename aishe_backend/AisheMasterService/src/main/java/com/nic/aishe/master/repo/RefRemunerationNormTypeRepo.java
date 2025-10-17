package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefRemunerationNormType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRemunerationNormTypeRepo extends JpaRepository<RefRemunerationNormType, Integer> {
}
