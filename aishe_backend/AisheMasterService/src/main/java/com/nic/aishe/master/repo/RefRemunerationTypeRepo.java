package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefRemunerationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRemunerationTypeRepo extends JpaRepository<RefRemunerationType, Integer> {
}
