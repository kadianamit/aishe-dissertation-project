package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefFormRemunerationNormType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefFormRemunerationNormTypeRepo extends JpaRepository<RefFormRemunerationNormType, String> {
}
