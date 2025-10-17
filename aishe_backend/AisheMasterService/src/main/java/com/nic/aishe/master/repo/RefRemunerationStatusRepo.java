package com.nic.aishe.master.repo;

import com.nic.aishe.master.entity.RefRemunerationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefRemunerationStatusRepo extends JpaRepository<RefRemunerationStatus, Integer> {
}
